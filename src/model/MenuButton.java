package model;


import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuButton extends Button {


    public MenuButton(String text, String BUTTON_RELEASED_STYLE, String BUTTON_PRESSED_STYLE, String FONT_PATH) throws FileNotFoundException {
        setText(text);
        setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        setStyle(BUTTON_RELEASED_STYLE);
        setPrefWidth(190);
        setPrefHeight(45);
        initializeButtonListeners(BUTTON_PRESSED_STYLE, BUTTON_RELEASED_STYLE);
    }

    private void setBUTTON_PRESSED_STYLE(String BUTTON_PRESSED_STYLE) {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setBUTTON_RELEASED_STYLE(String BUTTON_RELEASED_STYLE) {
        setStyle(BUTTON_RELEASED_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners(String BUTTON_PRESSED_STYLE, String BUTTON_RELEASED_STYLE) {

        setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
                setBUTTON_PRESSED_STYLE(BUTTON_PRESSED_STYLE);
        });

        setOnMouseReleased(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
                setBUTTON_RELEASED_STYLE(BUTTON_RELEASED_STYLE);
        });

        setOnMouseEntered(mouseEvent -> setEffect(new DropShadow()));

        setOnMouseExited(mouseEvent -> setEffect(null));
    }

}
