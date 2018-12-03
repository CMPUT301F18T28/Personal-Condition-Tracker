package ca.ualberta.cs.personal_condition_tracker.Model;

import java.util.ArrayList;

/**
 * The type Comment record list.
 */
public class CommentRecordList {

    private ArrayList<CommentRecord> commentRecordList;
    private transient ArrayList<Listener> listenerList = null;
    private CommentRecord commentOfInterest = null;

    /**
     * Instantiates a new Comment record list.
     */
    CommentRecordList() {
        this.commentRecordList = new ArrayList<>();
    }

    /**
     * Has comment record boolean.
     *
     * @param commentRecord the new record
     * @return the boolean
     */
    public boolean hasCommentRecord(CommentRecord commentRecord) {
        return this.commentRecordList.contains(commentRecord);
    }

    /**
     * Get comment record comment record.
     *
     * @param index the index
     * @return the comment record
     */
    public CommentRecord getCommentRecord(int index){
        return this.commentRecordList.get(index);
    }

    /**
     * Add comment record.
     *
     * @param commentRecord the new record
     */
    public void addCommentRecord(CommentRecord commentRecord) {
        this.commentRecordList.add(commentRecord);
        notifyListeners();
    }

    /**
     * Delete comment record.
     *
     * @param commentRecord the new record
     */
    public void deleteCommentRecord(CommentRecord commentRecord) {
        this.commentRecordList.remove(commentRecord);
        notifyListeners();
    }

    /**
     * Edit comment record.
     *
     * @param index      the index
     * @param commentRecord the new record
     */
    public void editCommentRecord(int index, CommentRecord commentRecord) {
        this.commentRecordList.set(index, commentRecord);
    }

    /**
     * Gets comment of interest.
     *
     * @return the comment of interest
     */
    public CommentRecord getCommentOfInterest() {
        return commentOfInterest;
    }

    /**
     * Sets comment records.
     *
     * @param commentRecords the comment records
     */
    public void setCommentRecords(ArrayList<CommentRecord> commentRecords) {
        this.commentRecordList = commentRecords;
    }

    /**
     * Sets comment of interest.
     *
     * @param commentOfInterest the comment of interest
     */
    public void setCommentOfInterest(CommentRecord commentOfInterest) {
        this.commentOfInterest = commentOfInterest;
    }

    /**
     * Gets comment records.
     *
     * @return the comment records
     */
    public ArrayList<CommentRecord> getCommentRecords() {
        return commentRecordList;
    }

    /**
     * Add listener.
     *
     * @param listener the listener
     */
    public void addListener(Listener listener){
        getListenerList().add(listener);;
    }

    /**
     * Remove a listener object.
     *
     * @param listener the listener
     * @params Listener
     */
    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }
    /**
     * Get all the listeners.
     * @return ArrayList<Listener>
     */
    private ArrayList<Listener> getListenerList(){
        if(listenerList == null){
            listenerList = new ArrayList<>();
        }
        return listenerList;
    }

    /**
     * Tell listeners to update.
     */
    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }
}
