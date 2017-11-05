package com.ms.thediamondkata;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Diamond {
    private final static Logger logger = Logger.getLogger("Diamond");

    public List<String> apply(Character character) {
        if (character < 'A' || character > 'Z') {
            throw new IllegalArgumentException(character + " is outside the valid range A-Z");
        } else {
            final int midpoint = character - 'A';
            List<String> result = new ArrayList<>();
            for (char nextChar = 'A'; nextChar <= character; nextChar++) {
                final int x = midpoint - result.size();
                StringBuilder row = new StringBuilder();
                while (row.length() < x) {
                    row.append('-');
                }
                row.append(nextChar);
                while (row.length() <= midpoint) {
                    row.append('-');
                }
                row.append(new StringBuilder(row.substring(0, midpoint)).reverse());
                result.add(row.toString());
            }
            final int index = result.size();
            for (int i = 0; i < (index - 1); i++) {
                result.add(index, result.get(i));
            }
            for (String row : result) {
                logger.info(row);
            }
            return result;
        }
    }
}