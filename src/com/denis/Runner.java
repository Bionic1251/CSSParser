package com.denis;


import java.util.List;

public class Runner {
    public static void main(String[] args) {
        TimeManager timeManager = new TimeManager();
        timeManager.start();
        String cssPath = ""; //here is the path to the css file, like "D:\workspace\MyProject\assets\default.css"
        CSSFileParser parser = new CSSFileParser(cssPath);
        parser.read();
		System.out.println("Selectors that were found in css file");
        List<String> s = parser.matchSelectors();
        for (String el : s) {
            System.out.println(el);
        }

        System.out.println("Selectors that were found in source files");
        String filesPath = ""; //here is the path to source folder, like "D:\workspace\MyProject\src"
        SelectorCleaner selectorCleaner = new SelectorCleaner(filesPath);
        selectorCleaner.cleanCSSList(s);
        System.out.println("Redundant selectors");
        for (String el : s) {
            System.out.println(el);
        }
        timeManager.end();
        System.out.println("It took " + timeManager.getMillisecondsTimePeriod() + " milliseconds.");
    }
}
