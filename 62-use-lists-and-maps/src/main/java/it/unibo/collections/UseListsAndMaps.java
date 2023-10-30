package it.unibo.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int LOWER = 1000;
    private static final int UPPER = 2000 - 1; // 2000 excluded, so 1999
    private static final int ELEMS = 1000;
    private static final int PERFORMANCE_ELEMS = 100_000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < ELEMS; i++) {
            list.add(randomInRange(UPPER, LOWER));
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final LinkedList<Integer> linked = new LinkedList<>(list);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int temp = list.get(0);
        list.set(0, list.get(ELEMS - 1));
        list.set(ELEMS - 1, temp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (final int i : list) {
            System.out.println(i);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();
        for (int i = 0; i < PERFORMANCE_ELEMS; i++) {
            list.add(0, randomInRange(UPPER, LOWER));
            linked.add(0, randomInRange(UPPER, LOWER));
        }
        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "Adding " 
            + PERFORMANCE_ELEMS
            + " as the first element of the collection ArrayList and LinkedList took " 
            + millis
            + "ms"
        );
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            /* Middle element */
            list.get(ELEMS / 2);
            linked.get(ELEMS / 2);
        }
        
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "Reading the element in the middle of the ArrayList and LinkedList 1000 times took " 
            + millis
            + "ms"
        );

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> cMap = new HashMap<>();
        cMap.put("Africa", 1_110_635_000L);
        cMap.put("Americas", 972_005_000L);
        cMap.put("Antartica", 0L);
        cMap.put("Asia" , 4_298_723_000L);
        cMap.put("Europe" , 742_452_000L);
        cMap.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the world
         */
        long worldPopulation = 0L;
        for(final long value : cMap.values()) {
            worldPopulation = value + worldPopulation;
        }
        System.out.println("World population " + worldPopulation);
    }

    private static int randomInRange(int upper, int lower) {
        return (int)(Math.random() * (upper - lower)) + lower;
    }
}
