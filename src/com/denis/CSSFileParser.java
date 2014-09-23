package com.denis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to match css file
 *
 * @author: Denis Kotkov
 */
public class CSSFileParser {
    private String path;
    private String content;

    public CSSFileParser(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public List<String> matchSelectors() {
        Pattern p = Pattern.compile("\\.[a-zA-Z0-9]+\\s*\\{");
        Matcher m = p.matcher(content);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group().replaceAll("\\.|\\s+|\\{", ""));
        }
        return list;
    }

    public void read() {
        try {
            File f = new File(path);
            FileReader fileReader = new FileReader(f);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = "";
            StringBuilder contentBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
            content = contentBuilder.toString();
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File " + path + " doesn't exist.");
        } catch (IOException e) {
            System.out.println("Can't read file " + path);
        }
    }
}
