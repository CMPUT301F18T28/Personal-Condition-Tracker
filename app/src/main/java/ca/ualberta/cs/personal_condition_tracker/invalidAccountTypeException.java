package ca.ualberta.cs.personal_condition_tracker;

public class invalidAccountTypeException extends Exception {

    invalidAccountTypeException() {
        super("There are only two valid account types: 'Patient' and 'Care Provider'.");
    }

}