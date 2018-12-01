package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

/**
 * The type Comment record list.
 */
public class CommentRecordList {

    private ArrayList<CommentRecord> comment_record_list;
    private transient ArrayList<Listener> listenerList = null;
    private CommentRecord commentOfInterest = null;

    /**
     * Instantiates a new Comment record list.
     */
    CommentRecordList() {
        this.comment_record_list = new ArrayList<CommentRecord>();
    }

    /**
     * Has comment record boolean.
     *
     * @param new_record the new record
     * @return the boolean
     */
    public boolean hasCommentRecord(CommentRecord new_record) {
        return this.comment_record_list.contains(new_record);
    }

    /**
     * Get comment record comment record.
     *
     * @param index the index
     * @return the comment record
     */
    public CommentRecord getCommentRecord(int index){
        return this.comment_record_list.get(index);
    }

    /**
     * Add comment record.
     *
     * @param new_record the new record
     */
    public void addCommentRecord(CommentRecord new_record) {
        this.comment_record_list.add(new_record);
        notifyListeners();
    }

    /**
     * Delete comment record.
     *
     * @param new_record the new record
     */
    public void deleteCommentRecord(CommentRecord new_record) {
        this.comment_record_list.remove(new_record);
        notifyListeners();
    }

    /**
     * Edit comment record.
     *
     * @param index      the index
     * @param new_record the new record
     */
    public void editCommentRecord(int index, CommentRecord new_record) {
        this.comment_record_list.set(index, new_record);
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
        this.comment_record_list = commentRecords;
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
        return comment_record_list;
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
