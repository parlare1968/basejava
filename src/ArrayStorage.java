import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("ERROR: array storage is completely filled");
            return;
        }
        for (var i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("ERROR: resume with uuid = " + r.getUuid() + " is present in the database");
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    public void update(Resume r) {
        for (var i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                storage[i] = r;
                return;
            }
        }
        System.out.println("ERROR: resume with uuid = " + r.getUuid() + " is missing in the database");
    }

    public Resume get(String uuid) {
        for (var i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("ERROR: resume with uuid = " + uuid + " is missing in the database");
        return null;
    }

    public void delete(String uuid) {
        for (var i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                size--;
                storage[size] = null;
                return;
            }
        }
        System.out.println("ERROR: resume with uuid = " + uuid + " is missing in the database");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}