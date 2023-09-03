package leet;


import java.util.Arrays;

public class JobSchedueling {
    int[] JobScheduling(Job arr[], int n) {
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(arr[i].deadline, max);
        }

        int[] freq = new int[max + 1];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = -1;
        }

        int countJobs = 0, jobProfit = 0;

        for (int i = 0; i < n; i++) {
            Job temp = arr[i];
            for (int j = temp.deadline; j > 0; j--) {
                if (freq[j] != -1) {
                    freq[j] = temp.id;
                    countJobs++;
                    jobProfit += temp.profit;
                    break;
                }
            }
        }
        int ans[] = new int[2];
        ans[0] = countJobs;
        ans[1] = jobProfit;
        return ans;
    }
}

class Job {
    int id, profit, deadline;

    Job(int x, int y, int z) {
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }
}