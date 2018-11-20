package ca.ualberta.cs.personal_condition_tracker;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;

public class PatientListTest extends TestCase {
 
  public void testHasPatient() {
        PatientList new_patient_list = new PatientList();
        Patient new_patient = new Patient();
        new_patient_list.addPatient(new_patient.getUserID());
        assertTrue(new_patient_list.hasPatient(new_patient.getUserID()));
  }
  
  public void testAddPatient() {
        Patient new_patient = new Patient();
        PatientList new_patient_list = new PatientList();
        new_patient_list.addPatient(new_patient.getUserID());
        assertTrue(new_patient_list.hasPatient(new_patient.getUserID()));
  }
  
  public void testDeletePatient() {
        PatientList new_patient_list = new PatientList();
        Patient new_patient = new Patient();
        new_patient_list.addPatient(new_patient.getUserID());
        new_patient_list.deletePatient(new_patient.getUserID());
        assertFalse(new_patient_list.hasPatient(new_patient.getUserID()));
  }
  
  public void testEditPatient() {
        Patient new_patient = new Patient();
        PatientList new_patient_list = new PatientList();
        new_patient_list.addPatient(new_patient.getUserID());
        //Patient newer_patient = new Patient();
        //new_patient_list.editPatient();
        //assertTrue(new_patient_list.getPatient(0).equals(newer_patient));
  }
  
} 
