package com.tate.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class testsort {

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.sort(Comparator.comparingInt(x -> x));
        Map<String, Boolean> map = new HashMap<>();
    }

}
