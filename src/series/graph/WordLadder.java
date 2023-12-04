package series.graph;

import series.graph.dataStructures.StringPair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

    // first word in set
    public int ladderLength(String startWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<StringPair> queue = new ArrayDeque<>();
        queue.add(new StringPair(startWord, 1));
        set.remove(startWord);
        while (!queue.isEmpty()) {
            StringPair pair = queue.remove();
            String value = pair.x;
            int level = pair.y;
            if (value.equals(endWord)) {
                return level;
            }
            int length = value.length();
            for (int i = 0; i < length; i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    String temp = value.substring(0, i) + (ch) + value.substring(i + 1, length);
                    if (set.contains(temp)) {
                        queue.add(new StringPair(temp, pair.y + 1));
                        set.remove(temp);
                    }
                }
            }
        }
        return 0;
    }


    public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList) {
        Set<String> set = new HashSet<>();
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for (int i = 0; i < wordList.length; i++) {
            set.add(wordList[i]);
        }
        Queue<ArrayList<String>> queue = new ArrayDeque<>();
        ArrayList<String> first = new ArrayList<>();
        first.add(startWord);
        queue.add(first);
        int level = 0;
        ArrayList<String> removable = new ArrayList<>();

        while (!queue.isEmpty()) {
            // for level wise

            ArrayList<String> list = queue.remove();
            String value = list.get(list.size() - 1);
            if (list.size() > level) {
                level++;
                for (String remove : removable) {
                    set.remove(remove);
                }
            }
            if (value.equals(targetWord) && (res.size() == 0 || res.get(0).size() == list.size())) {
                // the first sequence where we reached the end.
                // select smallest size only
                res.add(list);
                continue;
            }
            int length = value.length();
            for (int i = 0; i < length; i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    String temp = value.substring(0, i) + (ch) + value.substring(i + 1, length);
                    if (set.contains(temp)) {
                        ArrayList<String> copy = new ArrayList<>(list);
                        copy.add(temp);
                        queue.add(copy);
                        removable.add(temp);
                    }
                }
            }
        }
        return res;
    }
}
