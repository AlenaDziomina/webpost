package com.grouk.webpost.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Alena on 02.04.2017.
 */
public class ResponseParser {
    private static final String TABLE_START = "<table class=";
    private static final String TABLE_END = "</table>";
    private static final String ROW_START = "<tr";
    private static final String ROW_END = "</tr>";
    private static final String CELL_START = "<font";
    private static final String CELL_END = "</font>";

    public static List<List<String>> parseResponse(String body) {
        String table = getTable(body);
        List<String> rows = getRows(table);
        List<List<String>> cellsList = getCells(rows);
        List<List<String>> matchedCells = matchCellValues(cellsList);
        System.out.println(matchedCells);
        return matchedCells;
    }

    private static List<List<String>> matchCellValues(List<List<String>> cellsList) {
        List<List<String>> matchedCells = new ArrayList();
        cellsList.forEach(list -> {
            List<String> cells = new ArrayList();
            list.forEach(cell -> {
                Matcher m = Pattern.compile("\\>([^<>()]+)\\<?").matcher(cell);
                while(m.find()) {
                    String str = m.group(1).trim();
                    if (!str.isEmpty() && !str.startsWith("https")) {
                        cells.add(str);
                    }
                }
            });
            matchedCells.add(cells);
        });
        return matchedCells;
    }

    private static List<List<String>> getCells(List<String> rows) {
        return rows.stream().map(row -> getSubStrings(row, CELL_START, CELL_END)).collect(Collectors.toList());
    }

    private static List<String> getRows(String table) {
        return getSubStrings(table, ROW_START, ROW_END);
    }

    private static String getTable(String body) {
        int start = body.indexOf(TABLE_START);
        return getSubString(body, start, TABLE_END);
    }

    private static String getSubString(String str, int start, String endStr) {
        String subString = str.substring(start);
        int end = subString.indexOf(endStr);
        return subString.substring(0, end);
    }

    private static List<String> getSubStrings(String str, String startStr, String endStr) {
        List<String> subStrings = new ArrayList();
        for (int i = -1; (i = str.indexOf(startStr, i + 1)) != -1; ) {
            subStrings.add(getSubString(str, i, endStr));
        }
        return subStrings;
    }
}
