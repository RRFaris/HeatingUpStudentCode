import java.util.ArrayList;
/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Ryan
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        // Declare adjacency list
        ArrayList<Integer>[] adjacencyList = new ArrayList[temperatures.length];

        // Initialize every ArrayList
        for (int i = 0; i < temperatures.length; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Initialize adjacency list with all the connections
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = 0; j < i; j++) {
                if (temperatures[j] < temperatures[i]) {
                    adjacencyList[i].add(j);
                }
            }
        }

        // Create array to store longest paths to each node (memoization)
        int[] longestPaths = new int[temperatures.length];

        // For every temperature, find the longest path to it
        int max = 0;
        for (int i = 0; i < temperatures.length; i++) {
            int current = longestPath(adjacencyList, longestPaths, i);
            max = Math.max(current, max);
        }

        return max;
    }

    public static int longestPath(ArrayList<Integer>[] adjacencyList, int[] longestPaths, int index) {
        // Only recurse through nodes if you don't already have the longest path to it
        if (longestPaths[index] == 0) {
            int len = 1;
            for (int vertex : adjacencyList[index]) {
                // Recursive call
                len = Math.max(len, longestPath(adjacencyList, longestPaths, vertex) + 1);

                // Store length in memoization array to decrease run time
                longestPaths[index] = len;
            }
            return len;
        }
        // If you already have the longest path to a given index, return it
        else {
            return longestPaths[index];
        }
    }
}
