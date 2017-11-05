package com.ms.thediamondkata;

import java.util.ArrayList;
import java.util.List;

public class Diamond {

    public List<String> apply(Character c) {
        if (c < 'A' || c > 'Z') {
            throw new IllegalArgumentException(c + " is outside the valid range A-Z");
        } else {
            final int size = (2 * (c - 'A')) + 1;
            List<String> result = new ArrayList<>();
            while (result.size() < size) {
                StringBuilder row = new StringBuilder();
                while (row.length() < size) {
                    row.append('A');
                }
                result.add(row.toString());
            }
            return result;
        }
    }
}