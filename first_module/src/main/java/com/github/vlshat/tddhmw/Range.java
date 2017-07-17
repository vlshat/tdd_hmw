package com.github.vlshat.tddhmw;

import java.util.Iterator;
import java.util.List;

public interface Range {

    boolean isBefore(Range range);
    boolean isAfter(Range range);
    boolean isConcurrent(Range otherRange);
    long getLowerBound();
    long getUpperBound();
    boolean contains(long value);
    List<Long> asList();
    Iterator<Long> asIterator();
}
