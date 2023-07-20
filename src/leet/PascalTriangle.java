package leet;

//118

import java.util.ArrayList;
import java.util.List;

//1. Value at row/collumn: (r-1)C(col-1) is value: nCr= loop-> 0->r-1 -> result= result *n-i, result= result/i+1
//2. Values at a rows: loop-> 0->r-1 -> res= res*(r-i) and res=res/i
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 1; row <= numRows; row++) {
            result.add(generateRow(row));
        }
        return result;
    }

    public List<Integer> generateRow(int row) {
        int result = 1;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int col = 1; col < row; col++) {
            result = result * (row - col);
            result = result / col;
            list.add(result);
        }
        return list;
    }
}
