package main;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class TreatmentTest {

    @Test
    void analysis() {
        Treatment treatment = new Treatment();
        Hashtable<String, String> data;
        Hashtable<String, Integer> values=new Hashtable<>();
        values.put("WBC",0);
        values.put("Neut",0);
        values.put("Lymph",0);
        values.put("RBC",0);
        values.put("HCT",0);
        values.put("Urea",0);
        values.put("Hb",0);
        values.put("Crtn",0);
        values.put("Iron",0);
        values.put("HDL",0);
        values.put("AP",0);
        values.put("Is Your Temperature Average ? [~ 37°C]",0);
        values.put("Are You A Smoker ?",0);
        values.put("Do You Drink Enough Water ?",0);
        values.put("Any Existing History With Lung Diseases ?",0);
        values.put("Are You Currently Pregnant ?",0);
        values.put("Do You Currently Have Diarrhea ?",0);
        values.put("Did You Vomit Recently ?",0);
        values.put("Do You Do A Lot Of Physical Activity ?",0);
        values.put("Is Your Meat Intake Regular ?",0);
        values.put("Any Previous History With Muscle Diseases ?",0);

       data=treatment.analysis(values);
       assertEquals(data.size(),0);

        values.put("WBC",-1);
        data=treatment.analysis(values);
        assertEquals(data.size(),1);
        assertEquals(data.get("Viral Disease"),"Rest at home.");


        values.put("WBC",-1);
        values.put("Do You Drink Enough Water ?",-1);
        data=treatment.analysis(values);
        assertEquals(data.size(),2);
        assertEquals(data.get("Viral Disease"),"Rest at home.");
        assertEquals(data.get("Water Dehydration"),"Drink More Water.");


        values.put("WBC",-1);
        values.put("Do You Drink Enough Water ?",-1);
        values.put("Neut",-1);
        data=treatment.analysis(values);
        assertEquals(data.size(),4);
        assertEquals(data.get("Viral Disease"),"Rest at home.");
        assertEquals(data.get("Water Dehydration"),"Drink More Water.");
        assertEquals(data.get("Disruption of blood / blood cell formation"),"10 MG pill of B12 a day for a month\n" +
        "5 MG pill of folic acid a day for a month");
        assertEquals(data.get("Tendency to bacterial infections"),"Dedicated Antibiotics");


        values.put("WBC",-1);
        values.put("Do You Drink Enough Water ?",-1);
        values.put("Neut",1);
        data=treatment.analysis(values);
        assertEquals(data.size(),3);
        assertEquals(data.get("Viral Disease"),"Rest at home.");
        assertEquals(data.get("Water Dehydration"),"Drink More Water.");
        assertEquals(data.get("Infection"),"Dedicated Antibiotics");


        values.put("WBC",0);
        values.put("Neut",0);
        values.put("Do You Drink Enough Water ?",-1);
        values.put("Lymph",1);
        data=treatment.analysis(values);
        assertEquals(data.size(),2);
        assertEquals(data.get("Prolonged Bacterial Infection"),"Dedicated Antibiotics");
        assertEquals(data.get("Lymphoma Cancer"),"Entrectinib");



        values.put("RBC",-1);
        values.put("Lymph",1);
        data=treatment.analysis(values);
        assertEquals(data.size(),4);
        assertEquals(data.get("Prolonged Bacterial Infection"),"Dedicated Antibiotics");
        assertEquals(data.get("Lymphoma Cancer"),"Entrectinib");
        assertEquals(data.get("Anemia"),"Two 10 MG B12 pills a day for a month.");
        assertEquals(data.get("Bleeding"),"To be rushed to the hospital urgently.");



        values.put("RBC",0);
        values.put("Lymph",0);
        values.put("AP",1);
        data=treatment.analysis(values);
        assertEquals(data.size(),4);
        assertEquals(data.get("Liver disease"),"Referral to a specific diagnosis for the purpose of determining treatment.");
        assertEquals(data.get("Diseases of the biliary tract"),"Referral to surgical treatment.");
        assertEquals(data.get("Overactive thyroid gland"),"Propylthiouracil To reduce thyroid activity.");
        assertEquals(data.get("Use of various medications"),"Referral to a family doctor for a match between medications.");



        values.put("Are You Currently Pregnant ?",-1);
        data=treatment.analysis(values);
        assertEquals(data.size(),4);
        assertEquals(data.get("Liver disease"),"Referral to a specific diagnosis for the purpose of determining treatment.");
        assertEquals(data.get("Diseases of the biliary tract"),"Referral to surgical treatment.");
        assertEquals(data.get("Overactive thyroid gland"),"Propylthiouracil To reduce thyroid activity.");
        assertEquals(data.get("Use of various medications"),"Referral to a family doctor for a match between medications.");


        values.put("Are You Currently Pregnant ?",1);
        data=treatment.analysis(values);
        assertEquals(data.size(),5);
        assertEquals(data.get("Liver disease"),"Referral to a specific diagnosis for the purpose of determining treatment.");
        assertEquals(data.get("Diseases of the biliary tract"),"Referral to surgical treatment.");
        assertEquals(data.get("Overactive thyroid gland"),"Propylthiouracil To reduce thyroid activity.");
        assertEquals(data.get("Use of various medications"),"Referral to a family doctor for a match between medications.");
        assertEquals(data.get("Pregnancy"),"During Pregnancy The AP Level Increases.");


        values.put("Iron",-1);
        values.put("Are You Currently Pregnant ?",1);
        data=treatment.analysis(values);
        assertEquals(data.size(),7);
        assertEquals(data.get("Liver disease"),"Referral to a specific diagnosis for the purpose of determining treatment.");
        assertEquals(data.get("Diseases of the biliary tract"),"Referral to surgical treatment.");
        assertEquals(data.get("Overactive thyroid gland"),"Propylthiouracil To reduce thyroid activity.");
        assertEquals(data.get("Use of various medications"),"Referral to a family doctor for a match between medications.");
        assertEquals(data.get("Pregnancy"),"During Pregnancy The AP Level Increases.");
        assertEquals(data.get("Malnutrition - Inadequate nutrition"),"Schedule an appointment with a nutritionist.");
        assertEquals(data.get("Blood Loss"),"To be rushed to the hospital urgently.");




        values.put("Iron",1);
        values.put("Are You Currently Pregnant ?",1);
        data=treatment.analysis(values);
        assertEquals(data.size(),6);
        assertEquals(data.get("Liver disease"),"Referral to a specific diagnosis for the purpose of determining treatment.");
        assertEquals(data.get("Diseases of the biliary tract"),"Referral to surgical treatment.");
        assertEquals(data.get("Overactive thyroid gland"),"Propylthiouracil To reduce thyroid activity.");
        assertEquals(data.get("Use of various medications"),"Referral to a family doctor for a match between medications.");
        assertEquals(data.get("Pregnancy"),"During Pregnancy The AP Level Increases.");
        assertEquals(data.get("Iron poisoning"),"To be rushed to the hospital urgently.");


        values.put("Iron",0);
        values.put("RBC",0);
        values.put("Lymph",0);
        values.put("AP",0);

        data=treatment.analysis(values);
        assertEquals(data.size(),0);




    }
}