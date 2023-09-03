package leet;

import java.util.Arrays;

public class CountNumberOfPlatforms {

    static int countPlatforms(int n, int arr[], int dep[]) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int first = 0;
        int second = 0;
        int platforms = 0;
        int maximumPlat = 0;
        while (first < n && second < n) {
            if (arr[first] <= dep[second]) {
                platforms++;
                first++;
            } else if (arr[first] > dep[second]) {
                platforms--;
                second++;
            }

            maximumPlat = Math.max(platforms, maximumPlat);
        }
        return maximumPlat;
    }
}
