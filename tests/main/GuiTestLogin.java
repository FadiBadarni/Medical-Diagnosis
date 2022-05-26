package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class GuiTestLogin extends GuiTest {


    @Override
    protected Parent getRootNode() {
        Parent parent ;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            return parent;
        } catch (IOException ex) {
            assertEquals(1,3);
            return null;
        }
    }

    @Test
    public void setButton() {
        TextField username = find("#username");
        username.setText("abada12");
        verifyThat("#username", hasText("abada12"));
        assertEquals(username.getText(),"abada12");
        
        TextField password = find("#password");
        password.setText("test123@");
        verifyThat("#lastname", hasText("test123@"));
        assertNotEquals( password.getText(),"abada12");

        Button login = find("#loginButton");

        assertFalse(login.disableProperty().get());

        Button register = find("#registerButton");
        assertFalse( register.disableProperty().get());
        register.defaultButtonProperty().get();
    }

}