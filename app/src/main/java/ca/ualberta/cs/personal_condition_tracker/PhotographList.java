package ca.ualberta.cs.personal_condition_tracker;

/**
 * PhotographList contains a list of Photographs and allows the user to add, delete and replace them.
 * @author  R. Voon; rcvoon@ualberta.ca
 * @author  D. Buksa; draydon@ualberta.ca
 * @author  W. Nichols; wnichols@ualberta.ca
 * @author  D. Douziech; douziech@ualberta.ca
 * @author  C. Neureuter; neureute@ualberta.ca
 * @version 1.1, 11-18-18
 * @since   1.0
 */

import java.util.ArrayList;

public class PhotographList {

    private ArrayList<Photograph> photos;

    PhotographList() {
        this.photos = new ArrayList<Photograph>();
    }

    /**
     * Serves to confirm the presence of a Photograph within the list thereof
     * @return boolean True if the Photograph is contained within the list, false otherwise
     * @see Photograph
     */

    public boolean hasPhotograph(Photograph new_photo) {
        return this.photos.contains(new_photo);
    }

    /**
     * Retrieves a Photograph based on its index.
     * @param index Index of the photograph to retrieve
     * @return Photograph Photograph corresponding to the index specified
     * @see Photograph
     */

    public Photograph getPhotograph(int index){
        return this.photos.get(index);
    }

    /**
     * Appends a Photograph to the list thereof
     * @param new_photo Photograph to append
     * @return Nothing
     * @see Photograph
     */

    public void addPhotograph(Photograph new_photo) {
        this.photos.add(new_photo);
    }

    /**
     * Removes a Photograph from the list thereof
     * @param new_photo Photograph to remove
     * @return Nothing
     * @see Photograph
     */


    public void deletePhotograph(Photograph new_photo) {
        this.photos.remove(new_photo);
    }

    /**
     * Swaps out an existing Photograph at the specified index with another.
     * @param index Index of the Photograph to replace
     * @param new_photo Photograph to replace the one currently at the specified index location
     * @return Nothing
     * @see Photograph
     */


    //Note: This method should be refactored to something like 'swapPhoto'; editing implies making changes
    // existing photograph, not replacing it entirely.

    public void editPhotograph(int index, Photograph new_photo) {
        this.photos.set(index, new_photo);
    }


}
