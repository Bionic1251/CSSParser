package com.denis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Denis Kotkov
 */
public class SelectorCleaner {
    private String path;

    public SelectorCleaner(String path) {
        this.path = path;
    }

    public void cleanCSSList(List<String> css) {
        List<String> contents = readTree(path);
        for (String element : contents) {
            cleanCSSByContent(element, css);
        }
    }

    private void cleanCSSByContent(String content, List<String> css) {
        List<String> deletionItems = new ArrayList<String>();
        for (String selector : css) {
            Pattern p = Pattern.compile("^(.*?(\\b" + selector + "\\b)[^$]*)$");
            Matcher m = p.matcher(content);
            if (m.find()) {
                deletionItems.add(selector);
            }
        }

        for (String item : deletionItems) {
            System.out.println(item);
            css.remove(item);
        }
    }

    private List<String> readTree(String path) {
        final List<String> fileContentList = new ArrayList<String>();
        File file = new File(path);
        file.listFiles(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    fileContentList.addAll(readTree(f.getPath()));
                    return true;
                }
                fileContentList.add(readFile(f));
                return false;
            }
        });
        return fileContentList;
    }

    private String readFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(" ");
                content.append(line);
            }
            br.close();
            return content.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Selector tells that there's no file " +
                    file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Selector tells that it can't read file " +
                    file.getAbsolutePath());
        }
        return "";
    }
}
