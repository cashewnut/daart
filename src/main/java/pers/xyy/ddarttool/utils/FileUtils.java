package pers.xyy.ddarttool.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<File> getJavaFiles(File file) {
        List<File> javaFiles = new ArrayList<>();
        if (!file.exists())
            return new ArrayList<>();
        if (!file.isDirectory()) {
            javaFiles.add(file);
        } else {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        if (!f.getName().equals("test"))
                            javaFiles.addAll(getJavaFiles(f));
                    } else if (f.getName().length() > 5 && f.getName().endsWith(".java"))
                        javaFiles.add(f);
                }
            }
        }
        return javaFiles;
    }

    public static List<String> getJavaFilePath(File file) {
        List<String> javaFiles = new ArrayList<>();
        if (!file.exists())
            return new ArrayList<>();
        if (!file.isDirectory()) {
            if (file.getName().endsWith(".java"))
                javaFiles.add(file.getAbsolutePath());
        } else {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    javaFiles.addAll(getJavaFilePath(f));
                }
            }
        }
        return javaFiles;
    }


    public static void write(String filePath, String str) {
        File target = new File(filePath);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(target, true));
            bw.write(str);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> read(String filePath) {
        File target = new File(filePath);
        List<String> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(target));
            String str;
            while ((str = br.readLine()) != null)
                list.add(str);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String readFile(String filePath) {
        File target = new File(filePath);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(target));
            String str;
            while ((str = br.readLine()) != null)
                sb.append(str).append("\n");
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String readLineFromFile(int line, String filePath) {
        File target = new File(filePath);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(target));
            String str;
            while ((str = br.readLine()) != null) {
                if (--line == 0) {
                    sb.append(str);
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String readLine(int line, String content) {
        return content.split("\\n")[line - 1];
    }


}
