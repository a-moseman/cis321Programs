package FileIndexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
    public void load(String path) {
        try {
            String raw = readToRaw(path);
            System.out.println(raw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DEPRECATED
    private String readToRaw(String path) throws Exception {
        StringBuilder raw = new StringBuilder();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            raw.append(line).append('\n');
        }
        br.close();
        return raw.toString();
    }

    private ArrayList<String> readToList(String path) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        return list;
    }

    private void printList(ArrayList<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
    }
}