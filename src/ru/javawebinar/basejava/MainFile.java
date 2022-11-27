package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        File filePath = new File(".\\.gitignore");
        try {
            System.out.println(filePath.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File(".\\src\\ru\\javawebinar\\basejava");
        System.out.println(dir.isDirectory());

        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // рекурсивный обход и вывод имени файлов в каталогах и подкаталогах
        File root = new File(".");
        System.out.println("------------------");
        recursiveTraversal(root, "");
    }

    public static void recursiveTraversal(File current, String offset) {
        System.out.println(offset + current.getAbsoluteFile());
        if (!current.isDirectory()) {
            return;
        }
        File[] innerFileList = current.listFiles();
        if (innerFileList != null) {
            for (File file : innerFileList) {
                recursiveTraversal(file, offset + " ");
            }
        }
    }
}
