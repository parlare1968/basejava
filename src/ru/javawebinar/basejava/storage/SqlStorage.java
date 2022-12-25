package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbLogin, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbLogin, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", (ps) -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute((conn) -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES(?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            addContacts(conn, r);
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute((conn) -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact c WHERE resume_uuid = ?")) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }
            addContacts(conn, r);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c on r.uuid = c.resume_uuid WHERE r.uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, getFullNameColumn(rs));
            do {
                addContact(resume, rs);
            } while (rs.next());
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c on r.uuid = c.resume_uuid ORDER BY full_name,uuid", (ps) -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> resumeMap = new HashMap<>();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                String uuid = getUuidColumn(rs);
                Resume resume = resumeMap.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, getFullNameColumn(rs));
                    resumeMap.put(uuid, resume);
                    list.add(resume);
                }
                addContact(resume, rs);
            }
            return list;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) AS total FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("total") : 0;
        });
    }

    private String getUuidColumn(ResultSet rs) throws SQLException {
        return rs.getString("uuid");
    }

    private String getFullNameColumn(ResultSet rs) throws SQLException {
        return rs.getString("full_name");
    }

    private String getContactValueColumn(ResultSet rs) throws SQLException {
        return rs.getString("value");
    }

    private ContactType getContactTypeColumn(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        return type != null ? ContactType.valueOf(type) : null;
    }

    private void addContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES(?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContact(Resume resume, ResultSet rs) throws SQLException {
        ContactType contactType = getContactTypeColumn(rs);
        String contactValue = getContactValueColumn(rs);
        if (contactType != null && contactValue != null) {
            resume.addContact(contactType, contactValue);
        }
    }
}
