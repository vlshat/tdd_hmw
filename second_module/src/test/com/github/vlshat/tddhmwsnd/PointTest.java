package com.github.vlshat.tddhmwsnd;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PointTest {
    @Test
    public void distanceTo() throws Exception {

        final Point point1 = new Point(0, 0);
        final Point point2 = new Point(5, 5);

        double distance = point1.distanceTo(point2);

        final double expected = Math.sqrt(50);

        assertTrue(Math.abs(distance - expected) <= 0.01);
    }

}
