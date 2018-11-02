package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

public class PhotographList {

    private ArrayList<Photograph> photos;

    PhotographList() {
        this.photos = new ArrayList<Photograph>();
    }

    public boolean hasPhotograph(Photograph new_photo) {
        return this.photos.contains(new_photo);
    }
    public Photograph getPhotograph(int index){
        return this.photos.get(index);
    }

    public void addPhotograph(Photograph new_photo) {
        this.photos.add(new_photo);
    }
    public void deletePhotograph(Photograph new_photo) {
        this.photos.remove(new_photo);
    }
    public void editPhotograph(int index, Photograph new_photo) {
        this.photos.set(index, new_photo);
    }


}
