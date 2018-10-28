package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;
import java.util.Date;

public class Record {
    private String title;
    private Date date;
    private String description;
    private String geo_location;
    private String body_location;
    private ArrayList<Photographs> photos;
    private static final Integer MAX_CHARACTERS = 100;

    Record(String new_title, Date new_date, String new_description) {
        this.title = new_title;
        this.date = new_date;
        this.description = new_description;
    }



}
