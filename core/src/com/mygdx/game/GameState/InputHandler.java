package com.mygdx.game.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Camera.CameraController;
import com.mygdx.game.Player.ClientPlayer;
import com.mygdx.game.Player.PlayerManager;

public class InputHandler implements InputProcessor {

    private CameraController cameraController = CameraController.getInstance();
    private PlayerManager playerManager = PlayerManager.getInstance();

    @Override
    public boolean keyDown(int keycode) {
        playerManager.processKeyDown();
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

        //if the camera is locked then we enable the moveCamOnDrag function
        if(!cameraController.getLocked()){ cameraController.moveCamOnDrag(screenX, screenY); }

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
