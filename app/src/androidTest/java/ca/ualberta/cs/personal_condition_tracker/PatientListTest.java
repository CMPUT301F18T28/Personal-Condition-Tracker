package ca.ualberta.cs.personal_condition_tracker;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;




public class PatientListTest extends TestCase {
 
  public void testHasPatient() {
        PatientList new_patient_list = new PatientList();
        Patient new_patient = new Patient();
        new_patient_list.addPatient(new_patient);
        assertTrue(new_patient_list.hasPatient(new_patient));
  }
  
  public void testAddPatient() {
        Patient new_patient = new Patient();
        PatientList new_patient_list = new PatientList();
        new_patient_list.addPatient(new_patient);
        assertTrue(new_patient_list.hasPatient(new_patient));
  }
  
  public void testDeletePatient() {
        PatientList new_patient_list = new PatientList();
        Patient new_patient = new Patient();
        new_patient_list.addPatient(new_patient);
        new_patient_list.deletePatient(new_patient);
        assertFalse(new_patient_list.hasPatient(new_patient));
  }
  
  public void testEditPatient() {
        Patient new_patient = new Patient();
        PatientList new_patient_list = new PatientList();
        new_patient_list.addPatient(new_patient);
        Patient newer_patient = new Patient();
        //new_patient_list.editPatient();
        //assertTrue(new_patient_list.getPatient(0).equals(newer_patient));
  }
  
} 
