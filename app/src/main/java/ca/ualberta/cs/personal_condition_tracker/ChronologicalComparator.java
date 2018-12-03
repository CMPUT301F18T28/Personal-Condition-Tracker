package ca.ualberta.cs.personal_condition_tracker;

import java.util.Comparator;

import ca.ualberta.cs.personal_condition_tracker.Model.Record;

public class ChronologicalComparator implements Comparator<Record>{
    @Override
    public int compare(Record r1, Record r2) {
        return r2.getDate().compareTo(r1.getDate());
    }
}
