package com.abtesttool.core;

import java.util.Comparator;

public class ProbabilityComparator implements Comparator<Alternative> {
    @Override
    public int compare(Alternative o1, Alternative o2) {
        if (o1.getProbability() > o2.getProbability()) {
            return -1;
        } else if (o1.getProbability() < o2.getProbability()) {
            return 1;
        }
        return 0;
    }
}
