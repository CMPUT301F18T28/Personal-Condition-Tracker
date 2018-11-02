/* Personal Condition Tracker : A simple and attractive Android application that allows an individual to
 document, track and review the progression of a personal health issue (a 'condition'), thus serving to facilitate
 enhanced clarity of communicating between patient and care provider, early detection and accurate prognosis with the
 aim of obtaining medical treatment as soon as possible.

 Document the facts - get the treatment you deserve!

 Copyright (C) 2018

 R. Voon; rcvoon@ualberta.ca
 D. Buksa; draydon@ualberta.ca
 W. Nichols; wnichols@ualberta.ca
 D. Douziech; douziech@ualberta.ca
 C. Neureuter; neureute@ualberta.ca

*/

package ca.ualberta.cs.personal_condition_tracker;

import junit.framework.TestCase;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ConditionListTest extends TestCase {

    public void testAdd_SearchCondition() {

        ConditionList conditionList = new ConditionList();

        Condition condition = new Condition("ShoulderPain", "Pain in shoulder.");

        conditionList.addCondition(condition);

        assertTrue(conditionList.searchConditions(condition) == condition);
    }

    public void testDeleteCondition() {

        ConditionList conditionList = new ConditionList();

        Condition condition = new Condition("ShoulderPain", "Pain in shoulder.");

        conditionList.addCondition(condition);

        conditionList.deleteCondition(condition);

        assertTrue(conditionList.sizeOfList() == 0);

    }

    public void testEditCondition() {

        ConditionList conditionList = new ConditionList();

        Condition condition = new Condition("ShoulderPain", "Pain in shoulder");

        conditionList.addCondition(condition);

        Date date = new Date(2018, 11, 02);

        conditionList.editCondition(condition, "NeckPain", date, "Pain in my neck");

        assertTrue(conditionList.searchConditions(condition).getTitle().equals("NeckPain"));

        assertTrue(conditionList.searchConditions(condition).getDate().equals(date));

        assertTrue(conditionList.searchConditions(condition).getDescription().equals("Pain in my neck"));

    }

    public void testSortByDate()throws ParseException{

        ConditionList conditionList = new ConditionList();

        Condition condition1 = new Condition("RightShoulderPain", new SimpleDateFormat("dd-MM-yyyy").parse("02-02-2020"), "Pain in my right shoulder");

        Condition condition2 = new Condition("NeckPain", new SimpleDateFormat("dd-MM-yyyy").parse("02-02-2019"), "Pain in my neck");

        Condition condition3 = new Condition("LeftShoulderPain", new SimpleDateFormat("dd-MM-yyyy").parse("02-02-2018"), "Pain in my left shoulder");

        conditionList.addCondition(condition1);

        conditionList.addCondition(condition2);

        conditionList.addCondition(condition3);

        //conditionList.printListByDate();

        conditionList.sortByDate();

        assertTrue(conditionList.getByIndex(0).getDate().toString().equals("Fri Feb 02 00:00:00 MST 2018"));

        assertTrue(conditionList.getByIndex(1).getDate().toString().equals("Sat Feb 02 00:00:00 MST 2019"));

        assertTrue(conditionList.getByIndex(2).getDate().toString().equals("Sun Feb 02 00:00:00 MST 2020"));

    }
}
