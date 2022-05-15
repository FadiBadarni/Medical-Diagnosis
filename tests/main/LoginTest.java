package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.GuiTest.find;
import static org.loadui.testfx.controls.Commons.hasText;

class LoginTest{


    @Test
    void registerButton_Click() {
    }

    @Test
    void isCorrectPassword() {

        Login p=new Login();
        assertFalse(p.isCorrectPassword("username","password"));
        assertTrue(p.isCorrectPassword("123sd","ssdfsd"));
        assertFalse(p.isCorrectPassword("fadi","badarni"));
        assertFalse(p.isCorrectPassword("123sd","password"));
        assertFalse(p.isCorrectPassword("taylor","werbtrh"));
        assertTrue(p.isCorrectPassword("gfhfg","asdfghasdfgh"));


    }


    @Test
    void userLogin() {
//
//        TextField firstname = find("#klk");
//        firstname.setText("bennet");
//        verifyThat("#firstname", hasText("bennet"));
//
//        TextField lastname = find("#lastname");
//        lastname.setText("schulz");
//        verifyThat("#lastname", hasText("schulz"));
//
//        Button search = find("#search");
//        assertFalse(search.disableProperty().get());
//        //   TextField firstname = find("#password");
//        //firstname.setText("bennet");
//        //verifyThat("#firstname", hasText("bennet"));
////
////        TextField lastname = find("#lastname");
////        lastname.setText("schulz");
////        verifyThat("#lastname", hasText("schulz"));
////
////        Button search = find("#search");
////        assertFalse(search.disableProperty().get());
////        Login p=new Login();
////
////        PasswordField passwordField=new PasswordField();
////        passwordField.setText("password");
////
////        TextField textField=new TextField();
////        textField.setText("username");
////        p.setPassword(passwordField);
////        p.setUsername(textField);
////        p.userLogin(null);
//
//
//   //     assertEquals(p.getWrongLogin(),"Wrong Credentials");
//


    }

    @Test
    void testRegisterButton_Click() {
    }

    @Test
    void testIsCorrectPassword() {
    }
//
//    @Override
//    protected Parent getRootNode() {
////        Parent parent = null;
////        try {
////            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
////            return parent;
////        } catch (IOException ex) {
////            // TODO ...
////        }
////        return parent;
////    }
}
