package de.thelooter.iosteinpolls.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtils {

    public static List<String> pad(List<String> stringsToPad) {

        int spaceWidth = MinecraftFontWidthCalculator.getStringWidth(" ");
        int longestStringLength = MinecraftFontWidthCalculator.getStringWidth(StringUtils.getLongestString(stringsToPad));

        if (longestStringLength == 0) {
            throw new IllegalArgumentException("Cannot pad an empty list");
        }

        return stringsToPad.stream().map(string -> {
            int stringLength = MinecraftFontWidthCalculator.getStringWidth(string);
            int paddingLength = longestStringLength - stringLength;
            int padding = (paddingLength / spaceWidth) / 2;


            return " ".repeat(padding) + string;

        }).toList();

    }


    private static String getLongestString(List<String> stringList) {
        return stringList.stream().max(Comparator.comparingInt(MinecraftFontWidthCalculator::getStringWidth)).orElse("");
    }


    public static class MinecraftFontWidthCalculator {

        private static final int DEFAULT_WIDTH = 5;

        // Special characters not 5 dots wide
        private static final Map<Character, Integer> SPECIAL_WIDTHS = new HashMap<>();
        static {
            SPECIAL_WIDTHS.put(' ', 3);
            SPECIAL_WIDTHS.put('!', 1);
            SPECIAL_WIDTHS.put('"', 3);
            SPECIAL_WIDTHS.put('\'', 1);
            SPECIAL_WIDTHS.put('(', 3);
            SPECIAL_WIDTHS.put(')', 3);
            SPECIAL_WIDTHS.put('*', 3);
            SPECIAL_WIDTHS.put(',', 1);
            SPECIAL_WIDTHS.put('.', 1);
            SPECIAL_WIDTHS.put(':', 1);
            SPECIAL_WIDTHS.put(';', 1);
            SPECIAL_WIDTHS.put('<', 4);
            SPECIAL_WIDTHS.put('>', 4);
            SPECIAL_WIDTHS.put('@', 6);
            SPECIAL_WIDTHS.put('I', 3);
            SPECIAL_WIDTHS.put('[', 3);
            SPECIAL_WIDTHS.put(']', 3);
            SPECIAL_WIDTHS.put('`', 2);
            SPECIAL_WIDTHS.put('f', 4);
            SPECIAL_WIDTHS.put('i', 1);
            SPECIAL_WIDTHS.put('k', 4);
            SPECIAL_WIDTHS.put('l', 2);
            SPECIAL_WIDTHS.put('t', 3);
            SPECIAL_WIDTHS.put('{', 3);
            SPECIAL_WIDTHS.put('|', 1);
            SPECIAL_WIDTHS.put('}', 3);
            SPECIAL_WIDTHS.put('~', 6);
        }



        // taken directly from notchcode. enjoy
        public static int getStringWidth(String s) {
            if (s == null) {
                return 0;
            }
            int i = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '\247') {
                    j++;
                    continue;
                }
                i += SPECIAL_WIDTHS.getOrDefault(s.charAt(j), DEFAULT_WIDTH);
            }

            return i;
        }
    }

}
