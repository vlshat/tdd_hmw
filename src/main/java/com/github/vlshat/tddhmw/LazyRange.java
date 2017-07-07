package com.github.vlshat.tddhmw;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LazyRange implements Range {

    private final long lowerBound;
    private final long upperBound;
    private List<Long> contents = null;

    public LazyRange(long lowerBound, long upperBound) {
        if (lowerBound > upperBound)
            throw new InvalidParameterException();
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean isBefore(Range range) {
        return this.getLowerBound() < range.getLowerBound()
                && this.getUpperBound() < range.getUpperBound();

    }

    @Override
    public boolean isAfter(Range range) {
        return this.getLowerBound() > range.getLowerBound()
                && this.getUpperBound() > range.getUpperBound();

    }

    @Override
    public boolean isConcurrent(Range otherRange) {
        return contains(otherRange.getLowerBound())
                || contains(otherRange.getUpperBound())
                || otherRange.contains(lowerBound)
                || otherRange.contains(upperBound);
    }

    @Override
    public long getLowerBound() {
        return lowerBound;
    }

    @Override
    public long getUpperBound() {
        return upperBound;
    }

    @Override
    public boolean contains(long value) {
        return value >= lowerBound && value <= upperBound;
    }

    @Override
    public List<Long> asList() {

        if (contents == null) {
            contents = new ArrayList<>();
            for (long i = lowerBound; i <= upperBound; i++) {
                contents.add(i);
            }
        }

        return contents;
    }

    @Override
    public Iterator<Long> asIterator() {
        if (contents == null) {
            asList();
        }

        return contents.iterator();
    }
}
