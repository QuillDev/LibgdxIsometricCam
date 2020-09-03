package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Camera.CameraController;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.W;

public class PlayerManager {

    private static PlayerManager playerManager = new PlayerManager();

    //Create list of players
    private ClientPlayer clientPlayer = ClientPlayer.getInstance();
    private CameraController cameraController = CameraController.getInstance();

    private float maxVelocity = 1.5f;

    //make the constructor for player manger private
    private PlayerManager(){}

    /**
     * Runs when a key is pressed,
     */
    public void processKeyDown(){

        float deltaX = 0f;
        float deltaY = 0f;

        //If W is pressed, increase deltaY by max velocity
        if(Gdx.input.isKeyJustPressed(W)){
            if(Math.abs(deltaY) < maxVelocity) { deltaY += maxVelocity; }
        }

        //If W is pressed, increase deltaY by max velocity
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            if(Math.abs(deltaY) < maxVelocity) { deltaY -= maxVelocity; }
        }

        //If W is pressed, increase deltaY by max velocity
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            if(Math.abs(deltaX) < maxVelocity){ deltaX -= maxVelocity; }

        }

        //If W is pressed, increase deltaY by max velocity
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            if(Math.abs(deltaX) < maxVelocity){ deltaX += maxVelocity; }
        }

        //If SPACE is pressed
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            cameraController.setLocked(!cameraController.getLocked());
        }

        //set the velocity of the plyaer
        clientPlayer.setVelocity(clientPlayer.getDeltaX() + deltaX, + clientPlayer.getDeltaY() + deltaY);
    }

    /**
     * Runs when a key is released
     * @param keycode the keycode for the key pressed
     */
    public void processKeyUp(int keycode){
        Input input = Gdx.input;

        if(Input.Keys.W == keycode){ clientPlayer.setDeltaY(clientPlayer.getDeltaY() - maxVelocity);}
        if(Input.Keys.S == keycode){ clientPlayer.setDeltaY(clientPlayer.getDeltaY() + maxVelocity);}
        if(Input.Keys.A == keycode){ clientPlayer.setDeltaX(clientPlayer.getDeltaX() + maxVelocity);}
        if(Input.Keys.D == keycode){ clientPlayer.setDeltaX(clientPlayer.getDeltaX() - maxVelocity);}

        //If all hte keys are released, set the velocity to zero
        if(!input.isKeyPressed(W) && !input.isKeyPressed(Input.Keys.A) && !input.isKeyPressed(Input.Keys.S) && !input.isKeyPressed(Input.Keys.D)){
            clientPlayer.setVelocity(0, 0);
        }
    }

    /**
     * Get an instance of the player manager
     * @return the player manager instance
     */
    public static PlayerManager getInstance(){
        return playerManager;
    }

}
