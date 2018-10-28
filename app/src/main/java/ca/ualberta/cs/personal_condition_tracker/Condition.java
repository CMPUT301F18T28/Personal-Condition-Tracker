package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;
import java.util.Date;

public class Condition {

    private String title;
    private Date date;
    private String description;
    private RecordList record_list;
    private ArrayList<String> comment_list;
    private static final Integer MAX_CHARACTERS = 100;

}
