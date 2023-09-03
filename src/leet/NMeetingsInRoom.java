package leet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class meeting {
    int start;
    int end;
    int pos;

    meeting(int start, int end, int pos) {
        this.start = start;
        this.end = end;
        this.pos = pos;
    }
}

public class NMeetingsInRoom {

    void maxMeetings(int start[], int end[], int n) {

        ArrayList<meeting> meet = new ArrayList<>();

        for (int i = 0; i < start.length; i++) {
            meet.add(new meeting(start[i], end[i], i + 1));
        }

        Collections.sort(meet, (o1, o2) -> o1.end != o2.end ? o1.end - o2.end : (o1.pos != o2.pos ? o1.pos - o2.pos : 0));
        int limit = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < meet.size(); i++) {
            if (limit < meet.get(i).start) {
                limit = meet.get(i).start;
                answer.add(meet.get(i).pos);
            }
        }
    }

}
