package ca.ualberta.cs.personal_condition_tracker;
import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CareProviderTest extends TestCase {

    @Test
    public void testHasPatient() {
        PatientList new_patient_list = new PatientList();
        String new_patient = "testPatient";
        new_patient_list.addPatient(new_patient);
        assertTrue(new_patient_list.hasPatient(new_patient));
    }

    @Test
    public void testGetPatients() {
        PatientList new_patient_list = new PatientList();
        String new_patient = "testPatient";
        new_patient_list.addPatient(new_patient);
        ArrayList<String> patientIDs = new_patient_list.getPatientIDs();
        assertTrue(patientIDs.get(0).equals(new_patient));
    }

    @Test
    public void testAddPatient() {
        PatientList new_patient_list = new PatientList();
        String new_patient = "testPatient";
        new_patient_list.addPatient(new_patient);
        assertTrue(new_patient_list.hasPatient(new_patient));
    }

    @Test
    public void testRemovePatient() {
        PatientList new_patient_list = new PatientList();
        String new_patient = "testPatient";
        new_patient_list.addPatient(new_patient);
        new_patient_list.deletePatient(new_patient);
        assertFalse(new_patient_list.hasPatient(new_patient));
    }
  
}
