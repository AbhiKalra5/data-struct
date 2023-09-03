import tree.BinaryNode;

import java.io.File;

public class Ignore {
    public static void main(String[] args) {
        String path =
                "/Users/abhishekkalra/Projects/myFolder/validation";

        File[] files = new File(path).listFiles();
        displayFiles(files);
        System.out.println(1 % 2);

    }

    public static void displayFiles(File[] files) {
        // Traversing through the files array
        for (File filename : files) {
            // If a sub directory is found,
            // print the name of the sub directory
            if (filename.isDirectory()) {

                // and call the displayFiles function
                // recursively to list files present
                // in sub directory
                displayFiles(filename.listFiles());
            }

            // Printing the file name present in given path
            else {
                filename.delete();

                // check if the directory can be created
                // using the specified path nam
            }
        }
    }

}
