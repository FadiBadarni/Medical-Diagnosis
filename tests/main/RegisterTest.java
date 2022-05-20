package main;

import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    @Test
    void register() {
    }

    @Test
    void isCurrentInput() {
        Register r=new Register();
        assertEquals(r.isCurrentInput("123456781","123$abQw","firstname","lastname","email@emial"
                ,"user12q","123$abQw"),"true");
        assertEquals(r.isCurrentInput("12345678","123$ab","firstname","lastname","email@emial"
                ,"user12q","123$ab"),"ID is not within required length");
        assertEquals(r.isCurrentInput("123456781","123$ab","firstname","lastname","email@emial"
                ,"user12q","pass@#12"),"Passwords Do Not Match");
        assertEquals(r.isCurrentInput("123456781","123$ab","firstname","lastname","email@emial"
                ,"user12q","123$ab"),"Password is not within required length");
        assertEquals(r.isCurrentInput("123456781","123$abQw","firstname","lastname","emailemial"
                ,"user12q","123$abQw"),"Please Enter A Valid Email Address");
        assertEquals(r.isCurrentInput("123456781","123$abQw","","lastname","email@emial"
                ,"user12q","123$abQw"),"You must to add Firstname and Lastname");
        assertEquals(r.isCurrentInput("123456781","123$abQw","sdfsd","","email@emial"
                ,"user12q","123$abQw"),"You must to add Firstname and Lastname");
        assertEquals(r.isCurrentInput("12345sf81","123$abQw","sdfsd","sdffs","email@emial"
                ,"user12q","123$abQw"),"ID Must be a valid number");
        assertEquals(r.isCurrentInput("123456781","123aabQw","firstname","lastname","email@emial"
                ,"user12q","123aabQw"),"Password Does Not Meat Minimum Requirements.");
        assertEquals(r.isCurrentInput("123456781","123$abQw","firstname","lastname","email@emial"
                ,"user132q","123$abQw"),"A Maximum of 2 digits is allowed in usernames.");
        assertEquals(r.isCurrentInput("123456781","ggjj$abQw","firstname","lastname","email@emial"
                ,"user12q","ggjj$abQw"),"Password Does Not Meat Minimum Requirements.");




    }
}