package series;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BinarySearch {


    public int upperBound(int[] arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        int answer = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > x) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    public int lowerBound(int[] arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        int answer = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    // lower bound same as ciel
    public int floor(int[] arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        int answer = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= x) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;

            }
        }
        return answer;
    }

    // FIRST AND LAST OCCURRENCE
    // n loop by keeping variables checking

    public int[] firstAndLast(int[] arr, int n, int x) {

        int first = lowerBound(arr, n, x);
        int last = upperBound(arr, n, x);
        if (arr.length == 0 && first >= n || arr[first] != x) {
            return new int[] {-1, -1};
        }

        return new int[] {first, last - 1};
    }

    public int[] firstAndLast_b(int[] arr, int n, int x) {
        int first = firstOccurrence(arr, n, x);
        int last = lastOccurrence(arr, n, x);
        if (arr.length == 0 && first >= n || arr[first] != x) {
            return new int[] {-1, -1};
        }
        return new int[] {first, last};
    }

    public int firstOccurrence(int[] arr, int n, int k) {
        int low = 0, high = n - 1;
        int first = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] == k) {
                first = mid;
                // look for smaller index on the left
                high = mid - 1;
            } else if (arr[mid] < k) {
                low = mid + 1; // look on the right
            } else {
                high = mid - 1; // look on the left
            }
        }
        return first;
    }

    public int lastOccurrence(int[] arr, int n, int k) {
        int low = 0, high = n - 1;
        int last = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] == k) {
                last = mid;
                // look for larger index on the right
                low = mid + 1;
            } else if (arr[mid] < k) {
                low = mid + 1; // look on the right
            } else {
                high = mid - 1; // look on the left
            }
        }
        return last;
    }

    public int numberOfRepeated(int[] arr, int n, int x) {
        int first = firstOccurrence(arr, n, x);
        int last = lastOccurrence(arr, n, x);
        return last - first + 1;
    }

    // idea is to search in sorted . if not found shrink unsorted part by operating same on it.
    public int searchInRotatedArray(int[] arr, int n, int k) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) {
                return mid;
            }
            // case when 3 are equal : 333333123
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low = low + 1;
                high = high - 1;
            } else if (arr[low] <= arr[mid]) {
                if (arr[low] <= k && k <= arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (arr[mid] <= k && k <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    // we are basically trimming down unsorted part , marking min in sorted side
    public int searchMinimumInRotatedArray(int[] arr, int n, int k) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {

            int mid = (low + high) / 2;
            // 3 1 3
            if (arr[low] == arr[high]) {
                ans = Math.min(ans, arr[low]);
                low++;
                high--;
            } else if (arr[low] < arr[high]) {
                ans = Math.min(ans, arr[low]);
                break;
            } else if (arr[low] <= arr[mid]) {
                ans = Math.min(ans, arr[low]);
                low = mid + 1;
            } else {
                ans = Math.min(ans, arr[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }

    //n: at single element location : n-1 and n+1 would be different
    // idea is , pair are equal in following : left pairs(even, odd) + single element+ right pairs (odd,even)
    public int findSingleElementInSortedArray(int[] arr, int n) {
        if (n == 1) {
            return arr[0];
        } else if (arr[0] != arr[1]) {
            return arr[0];
        } else if (arr[n - 1] != arr[n - 2]) {
            return arr[n - 1];
        }

        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            int element = arr[mid];

            if (element != arr[mid - 1] && element != arr[mid + 1]) {
                return mid;
            } else if ((mid % 2 == 1 && arr[mid - 1] == element) || (mid % 2 == 0 && arr[mid + 1] == element)) {
                // if you are on left: there are 2 cases as pair is (even, odd) from start:
                // even mid should be equal to next element
                // odd mid should be equal to previous element
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        return -1;
    }


    // n: search location where a[i-1]<a[i]>a[i+1]
    public int findPeakElement(int[] arr, int n) {
        if (n == 1) {
            return arr[0];
        } else if (arr[0] > arr[1]) {
            return arr[0];
        } else if (arr[n - 1] > arr[n - 2]) {
            return arr[n - 1];
        }
        int low = 1;
        int high = n - 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                return arr[mid];
            } else if (arr[mid - 1] < arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int findSquareRoot(int m) {
        int low = 1;
        int high = m;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (mid * mid <= m) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public int findNRoot(int n, int m) {
        int low = 1;
        int high = m;
        while (low <= high) {
            int mid = (low + high) / 2;
            int res = findPow(mid, n, m);
            if (res == 0) {
                return mid;
            } else if (res < 0) {
                low = mid + 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private int findPow(int x, int p, int target) {
        long res = 1;
        while (p > 0) {
            if (p % 2 == 1) {
                res = x * res;
                p--;
            } else {
                x = x * x;
                p = p / 2;
            }
        }
        return (int) (res - target);
    }


    //koko eating
    public int minimumRateToEatBananas(int[] v, int h) {
        int low = 1, high = findMinMax(v)[1];
        int answer = Integer.MAX_VALUE;
        //apply binary search:
        while (low <= high) {
            int mid = (low + high) / 2;
            int totalH = calculateTotalHours(v, mid);

            if (totalH <= h) {
                answer = Math.min(answer, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    public int[] findMinMax(int[] v) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int n = v.length;
        //find the maximum:
        for (int i = 0; i < n; i++) {
            max = Math.max(max, v[i]);
            min = Math.min(min, v[i]);
        }
        return new int[] {min, max};
    }

    public int calculateTotalHours(int[] v, int hourly) {
        int totalH = 0;
        int n = v.length;
        //find total hours:
        for (int i = 0; i < n; i++) {
            totalH += Math.ceil((double) (v[i]) / (double) (hourly));
        }
        return totalH;
    }

    //bouquet

    public int minimumDaysToMakeBouquet(int[] v, int totalPairs, int cons) {
        int[] minMax = findMinMax(v);
        int low = minMax[0];
        int high = minMax[1];
        while (low <= high) {
            int mid = (low + high) / 2;
            int ans = findPairs(v, mid, cons);
            if (ans >= totalPairs) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public int findPairs(int[] v, int day, int cons) {
        int count = 0;
        int answer = -1;
        for (int i = 0; i < v.length; i++) {
            if (v[i] <= day) {
                count++;
            } else {
                answer = answer + (count / cons);
                count = 0;
            }
        }
        return answer + (count / cons);
    }

    public int minimumDivisor(int[] v, int threshold) {
        int[] minMax = findMinMax(v);
        int low = minMax[0];
        int high = minMax[1];
        while (low <= high) {
            int mid = (low + high) / 2;
            int ans = findCurrentDivisionSum(v, mid);
            if (ans <= threshold) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public int findCurrentDivisionSum(int[] v, int divisor) {
        int answer = 0;
        for (int i = 0; i < v.length; i++) {
            answer = (int) (answer + Math.ceil(v[i] / divisor));
        }
        return answer;
    }

    public int minimumCapacityForShip(int[] weights, int daysThreshold) {
        int[] minMax = findSumAndMax(weights);
        int low = minMax[1];
        int high = minMax[0];
        while (low <= high) {
            int mid = (low + high) / 2;
            int ans = findCounterForCapacity(weights, mid);
            if (ans <= daysThreshold) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int findCounterForCapacity(int[] array, int limit) {
        int load = 0;
        int counter = 1;
        for (int i = 0; i < array.length; i++) {
            if (load + array[i] > limit) {
                counter++;
                load = array[i];
            } else {
                load += array[i];
            }
        }
        return counter;
    }

    private int[] findSumAndMax(int[] v) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        int n = v.length;
        //find the maximum:
        for (int i = 0; i < n; i++) {
            max = Math.max(max, v[i]);
            sum += v[i];
        }
        return new int[] {sum, max};
    }

    // k missing

    public int findKMissingN(int[] array, int k) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }

    public static int findKMissing(int[] vec, int k) {
        int low = 0, high = vec.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (vec[mid] <= k) {
                // find numbers from lower bound  k+number till range
                k += (mid - low + 1);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return k;
    }



    public int minMaxDistance(int[] cows, int cowNumber) {
        Arrays.sort(cows);
        int min = cows[0];
        int max = cows[cows.length - 1];
        int i;
        for (i = 1; i < max - min; i++) {
            if (canPlaceCow(cows, i, cowNumber)) {
                return i;
            }
        }
        return -1;
    }

    public int minMaxDistance_binary(int[] cows, int cowNumber) {
        Arrays.sort(cows);
        int low = 1;
        int high = cows[cows.length - 1] - cows[0];
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canPlaceCow(cows, mid, cowNumber)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    private boolean canPlaceCow(int[] cows, int distance, int cowNumber) {
        int last = cows[0];
        int countCows = 1;
        for (int i = 1; i < cows.length; i++) {
            if (cows[i] - last >= distance) {
                countCows++;
                last = cows[i];
            }
            if (countCows >= cowNumber) {
                return true;
            }
        }
        return false;
    }

    public int maxMinPages(int[] pages, int students) {
        int[] minMax = findSumAndMax(pages);
        int low = minMax[1];
        int high = minMax[0];
        if (pages.length < students) {
            return -1;
        }
        for (int i = low; i < high; i++) {
            if (findCounterForCapacity(pages, i) == students) {
                return i;
            }
        }
        return -1;
    }

    // divide area in painters, Min of max area
    public int maxMinPages_binary(int[] pages, int students) {
        int[] minMax = findSumAndMax(pages);
        int low = minMax[1];
        int high = minMax[0];
        if (pages.length < students) {
            return -1;
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (findCounterForCapacity(pages, mid) <= students) {
                high = mid - 1;
            } else {
                low = mid + 1;

            }
        }
        return low;
    }

    // place gas station
    public int minMaxGasStation(int[] coordinates, int numberOfStations) {
        int[] filled = new int[coordinates.length - 1];
        for (int i = 1; i <= numberOfStations; i++) {
            int maximum = -1;
            int maximumLocation = -1;
            for (int j = 0; j < coordinates.length; j++) {
                int diff = coordinates[j + 1] - coordinates[j];
                int sectionLength = diff / (filled[j] + 1);
                if (maximum < sectionLength) {
                    maximum = sectionLength;
                    maximumLocation = j;
                }
            }
            filled[maximumLocation] += 1;
        }

        int max = Integer.MIN_VALUE;
        for (int j = 0; j < coordinates.length; j++) {
            int diff = coordinates[j + 1] - coordinates[j];
            int sectionLength = diff / (filled[j] + 1);
            max = Math.max(max, sectionLength);
        }
        return max;
    }

    class Pair {
        BigDecimal perSectionLength;
        int location;

        public Pair(BigDecimal perSectionLength, int location) {
            this.perSectionLength = perSectionLength;
            this.location = location;
        }
    }

    public BigDecimal minMaxGasStation_heap(int[] coordinates, int numberOfStations) {
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        int[] filled = new int[coordinates.length - 1];
        for (int j = 0; j < coordinates.length - 1; j++) {
            queue.add(new Pair(new BigDecimal(coordinates[j + 1] - coordinates[j]), j));
        }
        for (int i = 1; i <= numberOfStations; i++) {
            Pair pair = queue.poll();
            int location = pair.location;
            filled[location] += 1;
            pair.perSectionLength = new BigDecimal((coordinates[location + 1] - coordinates[location]) / (filled[location] + 1));
            queue.add(pair);
        }

        return queue.peek().perSectionLength;
    }

    public double minMaxGasStation_binary(int[] coordinates, int numberOfStations) {

        double low = 0;
        double high = maxConsDistance(coordinates);
        double range = (Math.pow(10, -6));
        while (high - low > range) {
            double mid = (low + low) / 2.0;
            int number = numberRequired(coordinates, mid);
            if (number > numberOfStations) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int maxConsDistance(int[] coordinates) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < coordinates.length - 1; i++) {
            max = Math.max(max, coordinates[i + 1] - coordinates[i]);
        }
        return max;
    }

    private int numberRequired(int[] coordinates, double distance) {
        int count = 0;
        for (int i = 0; i < coordinates.length - 1; i++) {
            double numberInBetween = (coordinates[i + 1] - coordinates[i]) / distance;
            if (numberInBetween % 1 == 0) {
                numberInBetween--;
            }
            count += numberInBetween;
        }
        return count;
    }

    public int median(int[] nums1, int[] nums2) {
        int size_a = nums1.length;
        int size_b = nums2.length;
        int size_result = size_a + size_b;
        int index_a = size_result / 2;
        int index_b = index_a - 1;
        int count = 0;
        int i = 0;
        int j = 0;
        int res_a = -1;
        int res_b = -1;
        while (i < size_a && j < size_b) {
            if (nums1[i] >= nums2[j]) {
                if (count == index_a) {
                    res_a = nums2[j];
                }
                if (count == index_b) {
                    res_b = nums2[j];
                }
                count++;
                j++;
            } else {
                if (count == index_a) {
                    res_a = nums1[i];
                }
                if (count == index_b) {
                    res_b = nums1[i];
                }
                count++;
                i++;
            }
        }

        while (j < size_b) {
            if (count == index_a) {
                res_a = nums1[i];
            }
            if (count == index_b) {
                res_b = nums1[i];
            }
            count++;
            i++;
        }

        while (i < size_a) {
            if (count == index_a) {
                res_a = nums1[i];
            }
            if (count == index_b) {
                res_b = nums1[i];
            }
            count++;
            i++;
        }
        return size_result % 2 == 0 ? (res_a + res_b) / 2 : res_b;
    }

    public static int median_binary(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int resultArrayLen = len1 + len2;
        if (len2 < len1) {
            return median_binary(nums2, nums1);
        }
        int portion_left = (resultArrayLen + 1) / 2;
        int low = 0;
        int high = len1;
        while (low <= high) {

            int cutPos_a = (low + high) >> 1;
            int cutPos_b = portion_left - cutPos_a; // 10/2 -2 = 3

            int left_a = cutPos_a == 0 ? Integer.MIN_VALUE : nums1[cutPos_a - 1];
            int left_b = cutPos_b == 0 ? Integer.MIN_VALUE : nums2[cutPos_b - 1];
            int right_a = cutPos_a == len1 ? Integer.MAX_VALUE : nums1[cutPos_a];
            int right_b = cutPos_b == len2 ? Integer.MAX_VALUE : nums2[cutPos_b];

            if (left_a <= right_b && left_b <= right_a) {
                if (resultArrayLen % 2 == 0) {
                    return (Math.max(left_a, left_b) + Math.min(right_a, right_b)) / 2;
                } else {
                    return (Math.max(left_a, left_b));
                }

            } else if (left_a > right_b) {
                high = cutPos_a - 1;
            } else {
                low = cutPos_a + 1;
            }
        }
        return 0;
    }

    public int findKthNumberInMultiplicationMatrix(int[] nums1, int[] nums2, int k) {
        int len_a = nums1.length;
        int len_b = nums2.length;
        if (len_b < len_a) {
            return findKthNumberInMultiplicationMatrix(nums2, nums1, k);
        }
        int low = Math.max(0, k - len_b);
        int high = Math.min(k, len_a);

        while (low <= high) {
            int cut_a = (low + high) >> 1;
            int cut_b = k - cut_a;

            int left_a = cut_a == 0 ? Integer.MIN_VALUE : nums1[cut_a - 1];
            int left_b = cut_b == 0 ? Integer.MIN_VALUE : nums1[cut_b - 1];
            int right_a = cut_a == len_a ? Integer.MAX_VALUE : nums1[cut_a];
            int right_b = cut_b == len_b ? Integer.MAX_VALUE : nums1[cut_b];
            if (left_a <= right_b && left_b <= right_a) {
                return (Math.max(left_a, left_b));
            } else if (left_a > right_b) {
                high = cut_a - 1;
            } else {
                low = cut_a + 1;
            }
        }
        return -1;
    }

    public int rowWithMax1s(int[][] matrix, int n, int m) {
        int count_max = 0;
        int index = -1;

        for (int i = 0; i < n; i++) {
            int count_now = n - lowerBound(matrix[i], m, 1);
            if (count_now > count_max) {
                count_max = count_now;
                index = i;
            }
        }
        return index;
    }

    // 5/4=1, 5%4=1
    public boolean binarySearch_2d(int[][] matrix, int n, int m, int k) {
        int low = 0;
        int high = m * n - 1;
        boolean res = false;
        while (low <= high) {
            int mid = (low + high) / 2;
            int row = mid / m;
            int col = mid % m;
            if (matrix[row][col] == k) {
                res = true;
                break;
            } else if (matrix[row][col] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public boolean binarySearch_2d_sortedRows(int[][] array, int n, int m, int k) {
        int i = 0, j = m - 1;

        while (i < array.length && j >= 0) {
            int temp = array[i][j];
            if (temp == k) {
                return true;
            } else if (temp < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public int[] findPeakElement_2d(int[][] arr, int n, int m) {

        int low = 0;
        int high = m - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int max = findMax(arr, n, mid);
            int left = mid - 1 >= 0 ? arr[mid - 1][max] : -1;
            int right = mid + 1 < m ? arr[mid + 1][max] : -1;
            if (left < arr[mid][max] && right < arr[mid][max]) {
                return new int[] {mid, max};
            } else if (left > arr[mid][max]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[] {-1, -1};
    }

    private int findMax(int[][] arr, int n, int col) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i][col] > max) {
                max = arr[i][col];
                index = i;
            }
        }
        return index;
    }


    // number of elements less  than equal to median > n/2
    public int median_2d(int[][] arr, int n, int m) {
        int[] lowHigh = findLowAndHigh(arr, n, m);
        int low = lowHigh[0];
        int high = lowHigh[1];
        int target = (m * n) / 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            int lessOrEqual = findLessOrEqualElements(arr, mid, n, m);
            // answer is first element more than target
            if (lessOrEqual <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // return low;
        return low;

    }

    private int[] findLowAndHigh(int[][] arr, int n, int m) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(arr[i][0], min);
            max = Math.max(arr[i][m - 1], max);
        }
        return new int[] {min, max};
    }

    private int findLessOrEqualElements(int[][] arr, int mid, int n, int m) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += upperBound(arr[i], m, mid);
        }
        return count;
    }

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int low = 1;
        int high = position[position.length - 1] - low;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canPlace(position, mid, m)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    private boolean canPlace(int[] position, int distance, int max) {
        int last = position[0];
        int count = 1;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - last >= distance) {
                count++;
                last = position[i];
            }
            if (count >= max) {
                return true;
            }
        }
        return false;
    }

    public int splitArray(int[] nums, int k) {
        int[] maxSum = findSumAndMax(nums);
        int low = maxSum[1];
        int high = maxSum[0];
        while (low <= high) {
            int mid = (low + high) / 2;
            if (getDays(nums, mid) <= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int getDays(int[] weights, int weightLimit) {
        int days = 1;
        int load = 0;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] + load <= weightLimit) {
                load += weights[i];
            } else {
                load = weights[i];
                days++;
            }
        }
        return days;
    }

    static public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int resultArrayLen = n + m;
        int lengthOfFirstHalf = (resultArrayLen + 1) / 2;
        int low = 0;
        int high = n;
        while (low <= high) {
            int cut_a = (low + high) / 2;
            int cut_b = lengthOfFirstHalf - cut_a;

            int left_a = cut_a == 0 ? Integer.MIN_VALUE : nums1[cut_a - 1];
            int left_b = cut_b == 0 ? Integer.MIN_VALUE : nums2[cut_b - 1];
            int right_a = cut_a == n ? Integer.MAX_VALUE : nums1[cut_a];
            int right_b = cut_b == m ? Integer.MAX_VALUE : nums2[cut_b];
            if (left_a <= right_b && left_b <= right_a) {
                if (resultArrayLen % 2 == 0) {
                    return (double) (Math.max(left_a, left_b) + Math.min(right_a, right_b)) / 2;
                } else {
                    return (Math.max(left_a, left_b));
                }
            } else if (left_a > right_b) {
                high = cut_a - 1;
            } else {
                low = cut_a + 1;
            }
        }
        return 0;
    }

    public int findKthNumberInMultiplicationMatrix(int m, int n, int k) {
        int low = 1;
        int high = m * n;
        int answer = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            int lessThanOrEqual = findLessOrEqualElements(mid, m, n);
            if (lessThanOrEqual < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private int findLessOrEqualElements(int mid, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(mid / i, n);
        }
        return count;
    }

}
