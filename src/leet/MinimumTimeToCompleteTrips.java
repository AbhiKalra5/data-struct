package leet;

public class MinimumTimeToCompleteTrips {
    public static void main(String[] args) {
        int[] time = {10000};
        long totalTrips = 10000000;
        int min = Integer.MAX_VALUE;
        for (int i : time) {
            min = Math.min(min, i);
        }
        long low = 1, high = min * totalTrips;

        while (low <= high) {
            long mid = (low + high) / 2;

            if (countTripsInTimeFrame(time, mid) < totalTrips) {
                low = mid + 1;
            } else {
                high = mid-1;

            }
        }
        System.out.println(low);

    }

    private static long countTripsInTimeFrame(int[] times, long trips) {
        long timeChecked = 0L;
        for (int time : times) {
            timeChecked += ((long)trips / time);
        }
        return timeChecked;
    }
}