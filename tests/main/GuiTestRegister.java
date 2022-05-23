package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class GuiTestRegister extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("Register.fxml"));
            return parent;
        } catch (IOException ex) {}
        return null;
    }

    @Test
    public void setBothnamesAndCheckEnabledSearchButton() {

            TextField firstname = find("#firstname");
            firstname.setText("firstname");
            verifyThat("#firstname", hasText("firstname"));
            assertEquals(firstname.getText(),"firstname");
            assertNotEquals(firstname.getText(),"");


            TextField lastname = find("#lastname");
            firstname.setText("lastname");
            verifyThat("#lastname", hasText("lastname"));
            assertEquals(lastname.getText(),"lastname");
            assertNotEquals(lastname.getText(),"");



            TextField username = find("#username");
            username.setText("abada12");
            verifyThat("#username", hasText("abada12"));
            assertEquals(username.getText(),"abada12");



            TextField password = find("#password");
            password.setText("test123@");
            verifyThat("#lastname", hasText("test123@"));
            assertNotEquals( password.getText(),"abada12");



            Button register = find("#registerButton");
            assertFalse( register.disableProperty().get());
            register.defaultButtonProperty().get();

        }


}