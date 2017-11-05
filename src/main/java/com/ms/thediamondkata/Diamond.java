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
            final int size = (2 * (character - 'A')) + 1;
            List<String> result = new ArrayList<>();
            for (char nextChar = 'A'; nextChar <= character; nextChar++) {
                StringBuilder row = new StringBuilder();
                while (row.length() < size) {
                    row.append(nextChar);
                }
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