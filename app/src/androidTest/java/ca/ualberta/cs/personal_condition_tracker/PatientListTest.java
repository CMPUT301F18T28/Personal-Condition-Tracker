package ca.ualberta.cs.personal_condition_tracker;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;

public class PatientListTest extends TestCase {
 
  public void testHasPatient() {
        PatientList new_patient_list = new PatientList();
        String new_patient = "testPatient";
        new_patient_list.addPatient(new_patient);
        assertTrue(new_patient_list.hasPatient(new_patient));
  }
  
  public void testAddPatient() {
        String new_patient = "testPatient";
        PatientList new_patient_list = new PatientList();
        new_patient_list.addPatient(new_patient);
        assertTrue(new_patient_list.hasPatient(new_patient));
  }
  
  public void testDeletePatient() {
        PatientList new_patient_list = new PatientList();
        String new_patient = "testPatient";
        new_patient_list.addPatient(new_patient);
        new_patient_list.deletePatient(new_patient);
        assertFalse(new_patient_list.hasPatient(new_patient));
  }

} 
