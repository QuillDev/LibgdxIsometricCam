package com.mygdx.game.GameState;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Camera.CameraController;
import com.mygdx.game.Player.PlayerManager;

import java.awt.event.KeyEvent;

public class InputHandler implements InputProcessor {

    private CameraController cameraController = new CameraController();
    private PlayerManager playerManager = PlayerManager.getInstance();

    @Override
    public boolean keyDown(int keycode) {
        playerManager.processKeyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        playerManager.processKeyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        cameraController.setLastOnDragStop();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        cameraController.moveCamOnDrag(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        cameraController.zoomCam(amount);
        return false;
    }
}
