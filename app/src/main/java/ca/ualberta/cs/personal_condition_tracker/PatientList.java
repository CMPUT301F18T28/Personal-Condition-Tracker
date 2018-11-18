package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

public class PatientList {
    private ArrayList<String> patientIDs;

    PatientList(){
        patientIDs = new ArrayList<>();
    }

    public String getPatientByIndex(int index) {
        return patientIDs.get(index);
    }
    public Patient getPatient(String patientID){
        //todo, use this for loading a patient account w/ elasticsearch
        return null;
    }

    public boolean hasPatient(String patientID) {
        return patientIDs.contains(patientID);
    }

    public void addPatient(String patientID) {
        patientIDs.add(patientID);
    }

    public void deletePatient(String patientID) {
        patientIDs.remove(patientID);

    }
}
