package com.grouk.webpost.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Response from belpost parser
 * Created by Alena on 02.04.2017.
 */
public class ResponseParser {
    private static final String TABLE_START = "<table class=";
    private static final String TABLE_END = "</table>";
    private static final String ROW_START = "<tr";
    private static final String ROW_END = "</tr>";
    private static final String CELL_START = "<font";
    private static final String CELL_END = "</font>";
    private static final String IS_IN_BELURUS = "Label49";
    private static final String HAS_INFO = "Panel2";
    private static final String MATCHER = "\\>([^<>()]+)\\<?";
    private static final String BEL_MATCHER = "\\>\\)*([^<>(]+)\\(*\\<?";

    public static List<List<String>> parseResponse(String body) {
        List<List<String>> matchedCells;
        if (hasInfo(body)) {
            String table = getTable(body);
            List<String> rows = getRows(table);
            List<List<String>> cellsList = getCells(rows);
            matchedCells = matchCellValues(cellsList, MATCHER);
            System.out.println(matchedCells);
        } else {
            return Collections.EMPTY_LIST;
        }

        if (inBelarus(body)) {
            String belTable = getBelTable(body);
            List<String> rows = getRows(belTable);
            List<List<String>> cellsList = getCells(rows);
            List<List<String>> matchedBelCells = matchCellValues(cellsList, BEL_MATCHER);
            System.out.println(matchedBelCells);
            matchedBelCells.stream().skip(1).map(list -> {
                List<String> newList = new ArrayList<>(3);
                int size = list.size();
                newList.add(list.get(0));
                String str = Arrays.toString(list.subList(1, size - 1).toArray()).replace("[", "").replace("]", "")
                        .replace(",", "");
                newList.add(str);
                newList.add(list.get(size - 1));
                return newList;
            }).forEach(matchedCells::add);
        }
        return matchedCells;
    }

    private static String getBelTable(String body) {
        int label = body.indexOf(IS_IN_BELURUS);
        String newBody = body.substring(label);
        int start = newBody.indexOf(TABLE_START);
        return getSubString(newBody, start, TABLE_END);
    }

    private static boolean hasInfo(String body) {
        return body.contains(HAS_INFO);
    }

    private static boolean inBelarus(String body) {
        return body.contains(IS_IN_BELURUS);
    }

    private static List<List<String>> matchCellValues(List<List<String>> cellsList, String matcher) {
        List<List<String>> matchedCells = new ArrayList<>();
        cellsList.forEach(list -> {
            List<String> cells = new ArrayList();
            list.forEach(cell -> {
                Matcher m = Pattern.compile(matcher).matcher(cell);
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
