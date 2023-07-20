package leet;

public class ReversePairs {
    static int count = 0;

    public static void main(String[] args) {
        int res = reversePairs(new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647});
        System.out.println(res);
    }

    public static int[] sortArray(int[] nums) {
        int[] res = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, res);
        return nums;
    }

    public static int reversePairs(int[] nums) {
        int[] res = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, res);
        return count;
    }

    public static void mergeSort(int[] arr, int low, int high, int[] res) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        mergeSort(arr, low, mid, res);  // left half
        mergeSort(arr, mid + 1, high, res); // right half
        countPairs(arr, low, mid, high);
        merge(arr, low, mid, high, res);  // merging sorted halves
    }

    public static void merge(int[] a, int low, int mid, int high, int[] res) {
        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high) {
            if (a[i] <= a[j]) {
                res[k] = a[i];
                i++;
            } else {
                res[k] = a[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            res[k] = a[i];
            i++;
            k++;
        }

        while (j <= high) {
            res[k] = a[j];
            j++;
            k++;
        }

        for (int z = low; z <= high; z++) {
            a[z] = res[z];
        }
    }




    public static void countPairs(int[] arr, int left, int mid, int high) {
        int right = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (right <= high && arr[i] > 2 * (long)arr[right]) {
                right++;
            }
            count = count + right - mid - 1;
        }
    }

}
