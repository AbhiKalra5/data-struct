package leet;

//268. Missing Number
//1.N2 iteration
//2. Hashing and check frequency of number
//3. array sum- 1-n sum && array sq sum - 1-n sq sum. solve equations
public class MissingNumber {

    public long[] missingNumber(int[] nums) {
        long[] res = new long[2];
        long n = nums.length;
        long sumArray = 0;
        long sumArraySquare = 0;
        long sumNumbers = (n * (n + 1)) / 2;
        long sumNumbersSquare = (n * (n + 1) * (2 * n + 1)) / 6;

        for (int i = 0; i < n; i++) {
            sumArray += nums[i];
            sumArraySquare += nums[i] * nums[i];
        }
        long actualDiffBoth = sumArray - sumNumbers;
        long actualSumBoth = (sumArraySquare - sumNumbersSquare) / actualDiffBoth;
        res[0] = (actualDiffBoth + actualSumBoth) / 2;
        res[1] = actualSumBoth - res[0];
        return res;
    }
}
