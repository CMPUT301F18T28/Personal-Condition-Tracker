package ca.ualberta.cs.personal_condition_tracker;
import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CareProviderTest extends TestCase {

    @Test
    public void testHasPatient() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patientList.addPatient(patient.getUserID());
        assertTrue(patientList.hasPatient(patient.getUserID()));

    }

    @Test
    public void testGetPatients() {
        PatientList patientList = new PatientList();
        String patient = "testPatient";
        patientList.addPatient(patient);
        ArrayList<String> patientIDs = patientList.getPatientIDs();
        assertTrue(patientIDs.get(0).equals(patient));
    }

    @Test
    public void testAddPatient() {
        PatientList patientList = new PatientList();
        String testPatient = "testPatient";
        patientList.addPatient(testPatient);
        assertTrue(patientList.hasPatient(testPatient));
    }

    @Test
    public void testRemovePatient() {
        PatientList patientList = new PatientList();
        String testPatient = "testPatient";
        patientList.addPatient(testPatient);
        patientList.deletePatient(testPatient);
        assertFalse(patientList.hasPatient(testPatient));
    }
  
}
