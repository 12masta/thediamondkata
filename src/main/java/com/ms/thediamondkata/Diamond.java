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
            char nextChar = 'A';
            boolean pastHalfWay = false;
            List<String> result = new ArrayList<>();
            while (result.size() < size) {
                StringBuilder row = new StringBuilder();
                while (row.length() < size) {
                    row.append(nextChar);
                }
                result.add(row.toString());
                if (pastHalfWay) {
                    nextChar--;
                } else {
                    nextChar++;
                    if (nextChar == character) {
                        pastHalfWay = true;
                    }
                }
            }

            for (String row : result) {
                logger.info(row);
            }
            return result;
        }
    }
}