package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

public class PatientList {
    private ArrayList<String> patientIDs;
    private transient ArrayList<Listener> listenerList = null;

    PatientList(){
        this.patientIDs = new ArrayList<>();
    }

    public String getPatientByIndex(int index) {
        return patientIDs.get(index);
    }
    public Patient getPatient(String patientID){
        //todo, use this for loading a patient account w/ elasticsearch
        return null;
    }

    public ArrayList<String> getPatientIDs(){return patientIDs;}

    public boolean hasPatient(String patientID) {
        return patientIDs.contains(patientID);
    }

    public void addPatient(String patientID) {
        patientIDs.add(patientID);
        notifyListeners();
    }

    public void deletePatient(String patientID) {
        patientIDs.remove(patientID);
        notifyListeners();
    }

    public void addListener(Listener listener){
        getListenerList().add(listener);;
    }

    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }

    private ArrayList<Listener> getListenerList(){
        if(listenerList == null){
            listenerList = new ArrayList<>();
        }
        return listenerList;
    }

    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }
}
