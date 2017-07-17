package com.github.vlshat.tddhmw;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class RangeTest {

    private Range range;
    private Range leftRange;
    private Range rightRange;
    private Range intersectingRange;

    @Before
    public void init() {
        range = new LazyRange(5L, 20L);
        leftRange = new LazyRange(0L, 4L);
        rightRange = new LazyRange(30L, 50L);
        intersectingRange = new LazyRange(5L, 15L);
    }

    @Test(expected = InvalidParameterException.class)
    public void wrongLowerAndUpperBound() {
        new LazyRange(20L, 3L);
    }

    @Test
    public void isBeforeCorrect() throws Exception {
        assertThat(range.isBefore(rightRange), is(true));
    }

    @Test
    public void isBeforeFalse() throws Exception {
        assertThat(range.isBefore(leftRange), is(false));
    }

    @Test
    public void isBeforeFalseWhenConcurrent() throws Exception {
        assertThat(range.isBefore(intersectingRange), is(false));
    }

    @Test
    public void isAfterCorrect() throws Exception {
        assertThat(range.isAfter(leftRange), is(true));
    }

    @Test
    public void isAfterFalse() throws Exception {
        assertThat(range.isAfter(rightRange), is(false));
    }

    @Test
    public void isAfterFalseWhenConcurrent() throws Exception {
        assertThat(range.isAfter(intersectingRange), is(false));
    }

    @Test
    public void isConcurrentTrue() throws Exception {
        assertThat(range.isConcurrent(intersectingRange), is(true));
    }

    @Test
    public void isConcurrentFalse() throws Exception {
        assertThat(range.isConcurrent(leftRange), is(false));
    }

    @Test
    public void containsTrue() throws Exception {
        assertThat(range.contains(5L), is(true));
        assertThat(range.contains(10L), is(true));
        assertThat(range.contains(20L), is(true));
    }

    @Test
    public void containsFalse() throws Exception {
        assertThat(range.contains(Long.MAX_VALUE), is(false));
    }

    @Test
    public void asList() throws Exception {
        List<Long> expected = new ArrayList<>();

        for (long i = range.getLowerBound(); i <= range.getUpperBound(); i++) {
            expected.add(i);
        }

        assertThat(range.asList(), is(expected));
    }

    @Test
    public void asIterator() throws Exception {
        List<Long> expected = new ArrayList<>();

        for (long i = range.getLowerBound(); i <= range.getUpperBound(); i++) {
            expected.add(i);
        }

        Iterator<Long> iterator = range.asIterator();

        while (iterator.hasNext()) {
            assertThat(range.contains(iterator.next()), is(true));
        }
    }
}