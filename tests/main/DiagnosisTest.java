package main;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class DiagnosisTest {


    @Test
    void setResults() {
        Diagnosis diagnosis = new Diagnosis();
        Patient p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Male", 0, 0);
        Hashtable<String, Integer> values ;
        values=diagnosis.setResults(p,5000,30,40,5,40,40,0.7,70,30,70,17);
        for(String v:values.keySet())
            assertEquals(values.get(v),0);
        values=diagnosis.setResults(p,11000,54,40,5,40,40,0.7,70,30,70,17);
        for(String v:values.keySet())
            assertEquals(values.get(v),0);
        values=diagnosis.setResults(p,11500,54,40,5,40,40,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);

        values=diagnosis.setResults(p,11500,70,40,5,40,40,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);
        assertEquals(values.get("Neut"),1);


        values=diagnosis.setResults(p,11500,70,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);
        assertEquals(values.get("RBC"),0);
        assertEquals(values.get("Neut"),1);
        assertEquals(values.get("HCT"),0);

        p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "female", 0, 0);

        values=diagnosis.setResults(p,11500,70,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);
        assertEquals(values.get("RBC"),0);
        assertEquals(values.get("Neut"),1);
        assertEquals(values.get("HCT"),0);

        values=diagnosis.setResults(p,11500,70,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);
        assertEquals(values.get("RBC"),0);
        assertEquals(values.get("Neut"),1);
        assertEquals(values.get("HCT"),0);

        p = new Patient(123456789, "FirstName", "LastName", 16, 123, 23, 586535522, "O+", "female", 0, 0);
        values=diagnosis.setResults(p,5500,70,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),0);
        assertEquals(values.get("Neut"),1);
        assertEquals(values.get("HCT"),0);

        p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "female", 1, 0);

        values=diagnosis.setResults(p,11500,70,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);
        assertEquals(values.get("RBC"),0);
        assertEquals(values.get("Neut"),1);
        assertEquals(values.get("HCT"),0);

        p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "female", 0, 1);

        values=diagnosis.setResults(p,11500,67,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);
        assertEquals(values.get("RBC"),0);
        assertEquals(values.get("Neut"),1);
        assertEquals(values.get("HCT"),0);


        p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "male", 1, 1);

        values=diagnosis.setResults(p,11500,12,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);
        assertEquals(values.get("RBC"),0);
        assertEquals(values.get("Neut"),-1);
        assertEquals(values.get("HCT"),0);



        p = new Patient(123456789, "FirstName", "LastName", 3, 123, 23, 586535522, "O+", "male", 1, 1);

        values=diagnosis.setResults(p,11500,12,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),0);
        assertEquals(values.get("RBC"),0);
        assertEquals(values.get("Neut"),-1);
        assertEquals(values.get("HCT"),0);


        p = new Patient(123456789, "FirstName", "LastName", 60, 123, 23, 586535522, "O+", "male", 1, 1);

        values=diagnosis.setResults(p,11500,12,12,5,40,33,0.7,70,30,70,17);
        assertEquals(values.get("WBC"),1);
        assertEquals(values.get("RBC"),0);
        assertEquals(values.get("Neut"),-1);
        assertEquals(values.get("HCT"),0);

    }

    @Test
    void setWBCLevel() {
        Diagnosis diagnosis = new Diagnosis();
        Patient p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Male", 1, 1);
       assertEquals(diagnosis.setWBCLevel(p,4500),0);
        assertEquals(diagnosis.setWBCLevel(p,5000),0);
        assertEquals(diagnosis.setWBCLevel(p,11000),0);
        assertEquals(diagnosis.setWBCLevel(p,3000),-1);
        assertEquals(diagnosis.setWBCLevel(p,4000),-1);
        assertEquals(diagnosis.setWBCLevel(p,13000),1);
        assertEquals(diagnosis.setWBCLevel(p,11500),1);

        p = new Patient(123456789, "FirstName", "LastName", 18, 123, 23, 586535522, "O+", "Male", 1, 1);
        assertEquals(diagnosis.setWBCLevel(p,4500),0);
        assertEquals(diagnosis.setWBCLevel(p,5000),0);
        assertEquals(diagnosis.setWBCLevel(p,11000),0);
        assertEquals(diagnosis.setWBCLevel(p,3000),-1);
        assertEquals(diagnosis.setWBCLevel(p,4000),-1);
        assertEquals(diagnosis.setWBCLevel(p,13000),1);
        assertEquals(diagnosis.setWBCLevel(p,11500),1);

        p = new Patient(123456789, "FirstName", "LastName", 17, 123, 23, 586535522, "O+", "Male", 1, 1);
        assertEquals(diagnosis.setWBCLevel(p,5500),0);
        assertEquals(diagnosis.setWBCLevel(p,15500),0);
        assertEquals(diagnosis.setWBCLevel(p,11000),0);
        assertEquals(diagnosis.setWBCLevel(p,5000),-1);
        assertEquals(diagnosis.setWBCLevel(p,5030),-1);
        assertEquals(diagnosis.setWBCLevel(p,16000),1);
        assertEquals(diagnosis.setWBCLevel(p,15600),1);

        p = new Patient(123456789, "FirstName", "LastName", 4, 123, 23, 586535522, "O+", "Male", 1, 1);
        assertEquals(diagnosis.setWBCLevel(p,5500),0);
        assertEquals(diagnosis.setWBCLevel(p,15500),0);
        assertEquals(diagnosis.setWBCLevel(p,11000),0);
        assertEquals(diagnosis.setWBCLevel(p,5000),-1);
        assertEquals(diagnosis.setWBCLevel(p,5030),-1);
        assertEquals(diagnosis.setWBCLevel(p,16000),1);
        assertEquals(diagnosis.setWBCLevel(p,15600),1);


        p = new Patient(123456789, "FirstName", "LastName", 3, 123, 23, 586535522, "O+", "Male", 1, 1);
        assertEquals(diagnosis.setWBCLevel(p,6000),0);
        assertEquals(diagnosis.setWBCLevel(p,17500),0);
        assertEquals(diagnosis.setWBCLevel(p,11000),0);
        assertEquals(diagnosis.setWBCLevel(p,5000),-1);
        assertEquals(diagnosis.setWBCLevel(p,5930),-1);
        assertEquals(diagnosis.setWBCLevel(p,18000),1);
        assertEquals(diagnosis.setWBCLevel(p,20000),1);


    }
    @Test
    void setHbLevel() {
        Diagnosis diagnosis = new Diagnosis();
        Patient p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Male", 1, 1);
        assertEquals(diagnosis.setHbLevel(p, 11), -1);
        assertEquals(diagnosis.setHbLevel(p, 12), 0);
        assertEquals(diagnosis.setHbLevel(p, 17), 0);
        assertEquals(diagnosis.setHbLevel(p, 18), 0);
        assertEquals(diagnosis.setHbLevel(p, 19), 1);
        assertEquals(diagnosis.setHbLevel(p, 16), 0);
        assertEquals(diagnosis.setHbLevel(p, 17.6), 0);
         p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Female", 1, 1);
        assertEquals(diagnosis.setHbLevel(p, 11), -1);
        assertEquals(diagnosis.setHbLevel(p, 12), 0);
        assertEquals(diagnosis.setHbLevel(p, 16), 0);
        assertEquals(diagnosis.setHbLevel(p, 17), 1);
        assertEquals(diagnosis.setHbLevel(p, 18), 1);
        assertEquals(diagnosis.setHbLevel(p, 19), 1);
        p = new Patient(123456789, "FirstName", "LastName", 16, 123, 23, 586535522, "O+", "Female", 1, 1);
        assertEquals(diagnosis.setHbLevel(p, 11), -1);
        assertEquals(diagnosis.setHbLevel(p, 11.5), 0);
        assertEquals(diagnosis.setHbLevel(p, 12), 0);
        assertEquals(diagnosis.setHbLevel(p, 15.5), 0);
        assertEquals(diagnosis.setHbLevel(p, 17), 1);
        assertEquals(diagnosis.setHbLevel(p, 18), 1);
        assertEquals(diagnosis.setHbLevel(p, 19), 1);

    }


    @Test
    void setApLevel() {
        Diagnosis diagnosis = new Diagnosis();
        Patient p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Male", 1, 1);
        assertEquals(diagnosis.setApLevel(p, 60), 0);
        assertEquals(diagnosis.setApLevel(p, 120), 0);
        assertEquals(diagnosis.setApLevel(p, 66), 0);
        assertEquals(diagnosis.setApLevel(p, 110), 0);
        assertEquals(diagnosis.setApLevel(p, 59), -1);
        assertEquals(diagnosis.setApLevel(p, 30), -1);
        assertEquals(diagnosis.setApLevel(p, 125), 1);
        assertEquals(diagnosis.setApLevel(p, 130), 1);

       p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Male", 0, 1);
        assertEquals(diagnosis.setApLevel(p, 30), 0);
        assertEquals(diagnosis.setApLevel(p, 90), 0);
        assertEquals(diagnosis.setApLevel(p, 66), 0);
        assertEquals(diagnosis.setApLevel(p, 110), 1);
        assertEquals(diagnosis.setApLevel(p, 29), -1);
        assertEquals(diagnosis.setApLevel(p, 10), -1);
        assertEquals(diagnosis.setApLevel(p, 125), 1);
        assertEquals(diagnosis.setApLevel(p, 91), 1);

    }

    @Test
    void setHdlLevel() {
        Diagnosis diagnosis = new Diagnosis();
        Patient p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setHdlLevel(p, 29), 0);
        assertEquals(diagnosis.setHdlLevel(p, 62), 0);
        assertEquals(diagnosis.setHdlLevel(p, 30), 0);
        assertEquals(diagnosis.setHdlLevel(p, 37.6), 0);
        assertEquals(diagnosis.setHdlLevel(p, 28.5), -1);
        assertEquals(diagnosis.setHdlLevel(p, 15), -1);
        assertEquals(diagnosis.setHdlLevel(p, 62.3), 1);
        assertEquals(diagnosis.setHdlLevel(p, 70), 1);

        p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "female", 1, 0);
        assertEquals(diagnosis.setHdlLevel(p, 34), 0);
        assertEquals(diagnosis.setHdlLevel(p, 82), 0);
        assertEquals(diagnosis.setHdlLevel(p, 44), 0);
        assertEquals(diagnosis.setHdlLevel(p, 37.6), 0);
        assertEquals(diagnosis.setHdlLevel(p, 28.5), -1);
        assertEquals(diagnosis.setHdlLevel(p, 33), -1);
        assertEquals(diagnosis.setHdlLevel(p, 90), 1);
        assertEquals(diagnosis.setHdlLevel(p, 101), 1);


        p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Male", 1, 1);
        assertEquals(diagnosis.setHdlLevel(p, 29), 0);
        assertEquals(diagnosis.setHdlLevel(p, 74.4), 0);
        assertEquals(diagnosis.setHdlLevel(p, 35), 0);
        assertEquals(diagnosis.setHdlLevel(p, 37.6), 0);
        assertEquals(diagnosis.setHdlLevel(p, 28.5), -1);
        assertEquals(diagnosis.setHdlLevel(p, 12), -1);
        assertEquals(diagnosis.setHdlLevel(p, 90), 1);
        assertEquals(diagnosis.setHdlLevel(p, 75), 1);


        p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "female", 1, 1);
        assertEquals(diagnosis.setHdlLevel(p, 34), 0);
        assertEquals(diagnosis.setHdlLevel(p, 98.4), 0);
        assertEquals(diagnosis.setHdlLevel(p, 35.4), 0);
        assertEquals(diagnosis.setHdlLevel(p, 86), 0);
        assertEquals(diagnosis.setHdlLevel(p, 28.5), -1);
        assertEquals(diagnosis.setHdlLevel(p, 33.7), -1);
        assertEquals(diagnosis.setHdlLevel(p, 103), 1);
        assertEquals(diagnosis.setHdlLevel(p, 99), 1);


    }

    @Test
    void setIronLevel() {
        Diagnosis diagnosis = new Diagnosis();
        Patient p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setIronLevel(p, 60), 0);
        assertEquals(diagnosis.setIronLevel(p, 160), 0);
        assertEquals(diagnosis.setIronLevel(p, 159.5), 0);
        assertEquals(diagnosis.setIronLevel(p, 79.1), 0);
        assertEquals(diagnosis.setIronLevel(p, 59), -1);
        assertEquals(diagnosis.setIronLevel(p, 12), -1);
        assertEquals(diagnosis.setIronLevel(p, 161), 1);
        assertEquals(diagnosis.setIronLevel(p, 180.4), 1);

        p = new Patient(123456789, "FirstName", "LastName", 20, 123, 23, 586535522, "O+", "Female", 1, 0);
        assertEquals(diagnosis.setIronLevel(p, 48), 0);
        assertEquals(diagnosis.setIronLevel(p, 128), 0);
        assertEquals(diagnosis.setIronLevel(p, 119.5), 0);
        assertEquals(diagnosis.setIronLevel(p, 79.1), 0);
        assertEquals(diagnosis.setIronLevel(p, 39), -1);
        assertEquals(diagnosis.setIronLevel(p, 47.4), -1);
        assertEquals(diagnosis.setIronLevel(p, 129), 1);
        assertEquals(diagnosis.setIronLevel(p, 130.4), 1);


    }

    @Test
    void setCreatinineLevel() {

        Diagnosis diagnosis = new Diagnosis();
        //60+
        Patient p = new Patient(123456789, "FirstName", "LastName", 60, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.6), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.2), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.7), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 1), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.5), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.3), 1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.25), 1);

        //18-59
        //59
         p = new Patient(123456789, "FirstName", "LastName", 59, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.6), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 1), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.7), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.9), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.5), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.09), 1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.25), 1);

        //18
        p = new Patient(123456789, "FirstName", "LastName", 18, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.6), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 1), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.7), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.9), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.5), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.09), 1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.25), 1);


        //3-17
        //17
        p = new Patient(123456789, "FirstName", "LastName", 17, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.5), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 1), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.7), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.9), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.3), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.09), 1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.25), 1);
        //3
        p = new Patient(123456789, "FirstName", "LastName", 3, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.5), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 1), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.7), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.9), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.3), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.09), 1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1.25), 1);

        //0-2
        //2
        p = new Patient(123456789, "FirstName", "LastName", 2, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.5), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.2), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.4), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.49), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.1), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1), 1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.65), 1);


        //2
        p = new Patient(123456789, "FirstName", "LastName", 0, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.5), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.2), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.4), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.49), 0);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.1), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0), -1);
        assertEquals(diagnosis.setCreatinineLevel(p, 1), 1);
        assertEquals(diagnosis.setCreatinineLevel(p, 0.65), 1);


    }

    @Test
    void setUreaLevel() {


        Diagnosis diagnosis = new Diagnosis();
        //IsEastern
        Patient p = new Patient(123456789, "FirstName", "LastName", 60, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setUreaLevel(p, 18.7), 0);
        assertEquals(diagnosis.setUreaLevel(p, 47.3), 0);
        assertEquals(diagnosis.setUreaLevel(p, 19), 0);
        assertEquals(diagnosis.setUreaLevel(p, 40.7), 0);
        assertEquals(diagnosis.setUreaLevel(p, 17), -1);
        assertEquals(diagnosis.setUreaLevel(p, 18), -1);
        assertEquals(diagnosis.setUreaLevel(p, 15), -1);
        assertEquals(diagnosis.setUreaLevel(p, 47.4), 1);
        assertEquals(diagnosis.setUreaLevel(p, 50), 1);
        assertEquals(diagnosis.setUreaLevel(p, 70), 1);

        // not Eastern
         p = new Patient(123456789, "FirstName", "LastName", 60, 123, 23, 586535522, "O+", "Male", 0, 0);
        assertEquals(diagnosis.setUreaLevel(p, 18.7), 0);
        assertEquals(diagnosis.setUreaLevel(p, 43), 0);
        assertEquals(diagnosis.setUreaLevel(p, 17), 0);
        assertEquals(diagnosis.setUreaLevel(p, 40.7), 0);
        assertEquals(diagnosis.setUreaLevel(p, 12), -1);
        assertEquals(diagnosis.setUreaLevel(p, 10.5), -1);
        assertEquals(diagnosis.setUreaLevel(p, 15), -1);
        assertEquals(diagnosis.setUreaLevel(p, 47.3), 1);
        assertEquals(diagnosis.setUreaLevel(p, 50), 1);
        assertEquals(diagnosis.setUreaLevel(p, 70), 1);

    }

    @Test
    void setHctLevel() {
        Diagnosis diagnosis = new Diagnosis();

        //male
        Patient p = new Patient(123456789, "FirstName", "LastName", 60, 123, 23, 586535522, "O+", "Male", 1, 0);
        assertEquals(diagnosis.setHctLevel(p, 54), 0);
        assertEquals(diagnosis.setHctLevel(p, 37), 0);
        assertEquals(diagnosis.setHctLevel(p, 50.3), 0);
        assertEquals(diagnosis.setHctLevel(p, 37.1), 0);
        assertEquals(diagnosis.setHctLevel(p, 55), 1);
        assertEquals(diagnosis.setHctLevel(p, 57.3), 1);
        assertEquals(diagnosis.setHctLevel(p, 33), -1);
        assertEquals(diagnosis.setHctLevel(p, 12), -1);
        assertEquals(diagnosis.setHctLevel(p, 47), 0);

        //female
        p = new Patient(123456789, "FirstName", "LastName", 60, 123, 23, 586535522, "O+", "female", 1, 0);
        assertEquals(diagnosis.setHctLevel(p, 33), 0);
        assertEquals(diagnosis.setHctLevel(p, 47), 0);
        assertEquals(diagnosis.setHctLevel(p, 37), 0);
        assertEquals(diagnosis.setHctLevel(p, 39.1), 0);
        assertEquals(diagnosis.setHctLevel(p, 54), 1);
        assertEquals(diagnosis.setHctLevel(p, 57.3), 1);
        assertEquals(diagnosis.setHctLevel(p, 32.9), -1);
        assertEquals(diagnosis.setHctLevel(p, 12), -1);

    }

    @Test
    void setRbcLevel() {
        Diagnosis diagnosis = new Diagnosis();

        assertEquals(diagnosis.setRbcLevel( 4.5), 0);
        assertEquals(diagnosis.setRbcLevel(6), 0);
        assertEquals(diagnosis.setRbcLevel(5), 0);
        assertEquals(diagnosis.setRbcLevel(4.7), 0);
        assertEquals(diagnosis.setRbcLevel(7), 1);
        assertEquals(diagnosis.setRbcLevel(6.3), 1);
        assertEquals(diagnosis.setRbcLevel(4), -1);
        assertEquals(diagnosis.setRbcLevel(3), -1);

    }

    @Test
    void setLymphLevel() {
        Diagnosis diagnosis = new Diagnosis();

        assertEquals(diagnosis.setLymphLevel( 36), 0);
        assertEquals(diagnosis.setLymphLevel( 52), 0);
        assertEquals(diagnosis.setLymphLevel( 37), 0);
        assertEquals(diagnosis.setLymphLevel( 50.2), 0);
        assertEquals(diagnosis.setLymphLevel( 12), -1);
        assertEquals(diagnosis.setLymphLevel( 25), -1);
        assertEquals(diagnosis.setLymphLevel( 0), -1);
        assertEquals(diagnosis.setLymphLevel( 35.9), -1);
        assertEquals(diagnosis.setLymphLevel( 100), 1);
        assertEquals(diagnosis.setLymphLevel( 54), 1);
        assertEquals(diagnosis.setLymphLevel( 52.1), 1);
        assertEquals(diagnosis.setLymphLevel( 77), 1);

    }

    @Test
    void setNeutLevel() {
        Diagnosis diagnosis = new Diagnosis();

        assertEquals(diagnosis.setNeutLevel( 28), 0);
        assertEquals(diagnosis.setNeutLevel( 54), 0);
        assertEquals(diagnosis.setNeutLevel( 37), 0);
        assertEquals(diagnosis.setNeutLevel( 50.2), 0);
        assertEquals(diagnosis.setNeutLevel( 54.2), 1);
        assertEquals(diagnosis.setNeutLevel( 60), 1);
        assertEquals(diagnosis.setNeutLevel( 100), 1);
        assertEquals(diagnosis.setNeutLevel( 12), -1);
        assertEquals(diagnosis.setNeutLevel( 27.9), -1);
        assertEquals(diagnosis.setNeutLevel( 24.6), -1);

    }
}