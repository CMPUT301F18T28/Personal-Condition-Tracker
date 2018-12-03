package ca.ualberta.cs.personal_condition_tracker.Model;

import java.util.Date;

import io.searchbox.annotations.JestId;

/**
 * CommentRecord class corresponds to a record that care providers can add to a patient's condition such that
 * the care provider can add their own thoughts on the patient's condition.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class CommentRecord {

    private String title;
    private Date date;
    private String comment;
    private String conditionIDForComment;
    @JestId
    private String id;

    /**
     * Constructor for instantiating a coment record type object.
     */
    public CommentRecord() {
        this.title = "";
        this.date = new Date();
        this.comment = "";
    }

    /**
     * Constructor with specified attributes: title, date, and comment.
     * @param title String representing the title of the CommenRecord.
     * @param date Date representing the timestamp of the CommentRecord.
     * @param comment String representing the comment of a care provider for a CommentRecord.
     */
    public CommentRecord(String title, Date date, String comment) {
        this.title = title;
        this.date = date;
        this.comment = comment;
    }

    /**
     * Gets the title of a CommentRecord.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of a CommentRecord.
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the timestamp of a CommentRecord.
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the timestamp of a CommentRecord.
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the comment of a CommentRecord.
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of a CommentRecord.
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets id.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets condition id for comment.
     * @return the condition id for comment
     */
    public String getConditionIDForComment() {
        return conditionIDForComment;
    }

    /**
     * Sets condition id for comment.
     * @param conditionIDForComment the condition id for comment
     */
    public void setConditionIDForComment(String conditionIDForComment) {
        this.conditionIDForComment = conditionIDForComment;
    }
    /**
     * Override toString to properly display a CommentRecord in a listview.
     * @return String
     */
    @Override
    public String toString(){
        return getTitle() + "\n" + getDate().toString() + "\n" + getComment();
    }


}
