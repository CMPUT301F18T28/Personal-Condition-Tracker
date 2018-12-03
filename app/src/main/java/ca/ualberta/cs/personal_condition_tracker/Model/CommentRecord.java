package ca.ualberta.cs.personal_condition_tracker.Model;

import java.util.Date;

import io.searchbox.annotations.JestId;

/**
 * The type Comment record.
 */
public class CommentRecord {

    private String title;
    private Date date;
    private String comment;
    private String conditionIDForComment;
    @JestId
    private String id;

    public CommentRecord() {
        this.title = "";
        this.date = new Date();
        this.comment = "";
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets condition id for comment.
     *
     * @return the condition id for comment
     */
    public String getConditionIDForComment() {
        return conditionIDForComment;
    }

    /**
     * Sets condition id for comment.
     *
     * @param conditionIDForComment the condition id for comment
     */
    public void setConditionIDForComment(String conditionIDForComment) {
        this.conditionIDForComment = conditionIDForComment;
    }

    @Override
    public String toString(){
        return getTitle() + "\n" + getDate().toString() + "\n" + getComment();
    }


}
