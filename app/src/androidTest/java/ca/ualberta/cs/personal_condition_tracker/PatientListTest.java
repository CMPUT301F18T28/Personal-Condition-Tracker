package ca.ualberta.cs.personal_condition_tracker;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;

public class PatientListTest extends TestCase {
 
  public void testHasPatient() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patientList.addPatient(patient.getUserID());
        assertTrue(patientList.hasPatient(patient.getUserID()));
  }
  
  public void testAddPatient() {
      PatientList patientList = new PatientList();
      Patient patient = new Patient();
      patientList.addPatient(patient.getUserID());
      assertTrue(patientList.hasPatient(patient.getUserID()));
  }
  
  public void testDeletePatient() {
      PatientList patientList = new PatientList();
      Patient patient = new Patient();
      patientList.addPatient(patient.getUserID());
      patientList.deletePatient(patient.getUserID());
      assertFalse(patientList.hasPatient(patient.getUserID()));
  }
  
  public void testEditPatient() {
        Patient patient = new Patient();
        PatientList patientList = new PatientList();
        patientList.addPatient(patient.getUserID());
        //Patient newer_patient = new Patient();
        //new_patient_list.editPatient();
        //assertTrue(new_patient_list.getPatient(0).equals(newer_patient));
  }
} 
