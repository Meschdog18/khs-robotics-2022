package org.firstinspires.ftc.teamcode.Commands.CommandLib.button;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.Gamepad.GamepadKeys;

public class GamepadButton extends Button {

    private final GamepadEx gamepad;
    private final GamepadKeys.Button[] buttons;

    /**
     * Creates a gamepad button for triggering commands.
     *
     * @param gamepad the gamepad with the buttons
     * @param buttons the specified buttons
     */
    public GamepadButton(GamepadEx gamepad, @NonNull GamepadKeys.Button... buttons) {
        this.gamepad = gamepad;
        this.buttons = buttons;
    }

    /**
     * Gets the value of the joystick button.
     *
     * @return The value of the joystick button
     */
    @Override
    public boolean get() {
        boolean res = true;
        for (GamepadKeys.Button button : buttons)
            res = res && gamepad.getButton(button);
        return res;
    }

}
