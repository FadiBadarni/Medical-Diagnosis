package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GuiTestRegister extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
            return parent;
        } catch (IOException ex) {}
        return null;
    }

    @Test
    public void setBothnamesAndCheckEnabledSearchButton() {

            TextField firstname = find("#firstname", (TextField) null);
            firstname.setText("firstname");
          //  verifyThat("#firstname", hasText("firstname"));
            assertEquals(firstname.getText(),"firstname");
            assertNotEquals(firstname.getText(),"");


            TextField lastname = find("#lastname", (TextField) null);
            lastname.setText("lastname");
           // verifyThat("#lastname", hasText("lastname"));
            assertEquals(lastname.getText(),"lastname");
            assertNotEquals(lastname.getText(),"");




            TextField username = find("#username", (TextField) null);
            username.setText("abada12");
           // verifyThat("#username", hasText("abada12"));
            assertEquals(username.getText(),"abada12");



            TextField password = find("#password", (TextField) null);
            password.setText("test123@");
          //  verifyThat("#lastname", hasText("test123@"));
            assertNotEquals( password.getText(),"abada12");



            Button register = find("#registerButton", (java.awt.Button) null);
            assertFalse( register.disableProperty().get());
            register.defaultButtonProperty().get();

        }


}