package leet;

public class RemoveDuplicatesInArray {

    public void remove(int arr[]) {
        int first = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[first] != arr[i]) {
                first++;
                arr[first] = arr[i];
            }
        }
    }
}
