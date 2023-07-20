package leet;

// 91
public class FloodFill {

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        arr = floodFill(arr, 1, 1, 2);
        arr.toString();
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        floodFill(image, sr, sc, image[sr][sc], color);
        return image;
    }

    public static void floodFill(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != oldColor) {
            return;
        }
        image[sr][sc] = newColor;
        floodFill(image, sr - 1, sc, oldColor, newColor);
        floodFill(image, sr + 1, sc, oldColor, newColor);
        floodFill(image, sr, sc, oldColor, newColor);
        floodFill(image, sc + 1, sc, oldColor, newColor);
    }
}
