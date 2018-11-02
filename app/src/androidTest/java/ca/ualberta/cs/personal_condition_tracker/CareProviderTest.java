package ca.ualberta.cs.personal_condition_tracker;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;

public class CareProviderTest extends TestCase {
 
    public void testHasPatient {
        PatientList new_patient_list = new PatientList();
        Patient new_patient = new Patient("Title",  "I am a Patient.");
        new_patient_list.addPatient(new_patient);
        assertTrue(new_patient_list.hasPatient(new_patient));
    }
  
}
