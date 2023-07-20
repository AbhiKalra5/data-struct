package leet;

import java.util.*;

//56. Merge Intervals
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int lastIndexOfResult = 0;
        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            int[] lastPair = ans.get(lastIndexOfResult);
            if (lastPair[1] > start) {
                lastPair[1] = Math.max(end, lastPair[1]);
            } else if (lastPair[1] < start) {
                ans.add(intervals[i]);
                lastIndexOfResult++;
            }
        }
        return ans.stream().toArray(int[][]::new);
    }
}
