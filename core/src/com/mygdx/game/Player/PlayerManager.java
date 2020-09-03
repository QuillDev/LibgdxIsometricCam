package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.W;

public class PlayerManager {

    private static PlayerManager playerManager = new PlayerManager();

    //Create list of players
    private final List<Player> playerList = new ArrayList<>();

    private float maxVelocity = 2f;

    //make the constructor for player manger private
    private PlayerManager(){}

    /**
     * Register a player to the playerList
     * @param player player to register
     */
    public void registerPlayer(Player player){
        playerList.add(player);
    }


    public void processKeyDown(){

        Player player = playerList.get(0);

        float deltaX = 0f;
        float deltaY = 0f;

        if(Gdx.input.isKeyJustPressed(W)){
            if(Math.abs(deltaY) < maxVelocity) { deltaY += maxVelocity; }
            System.out.println("Pressed W");
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            if(Math.abs(deltaY) < maxVelocity) { deltaY -= maxVelocity; }
            System.out.println("Pressed S");
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            if(Math.abs(deltaX) < maxVelocity){ deltaX -= maxVelocity; }
            System.out.println("Pressed A");

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            if(Math.abs(deltaX) < maxVelocity){ deltaX += maxVelocity; }
            System.out.println("Pressed D");
        }

        player.setVelocity(player.getDeltaX() + deltaX, + player.getDeltaY() + deltaY);
    }

    public void processKeyUp(int keycode){
        Input input = Gdx.input;

        Player player = playerList.get(0);
        if(Input.Keys.W == keycode){ player.setDeltaY(player.getDeltaY() - maxVelocity);}
        if(Input.Keys.S == keycode){ player.setDeltaY(player.getDeltaY() + maxVelocity);}
        if(Input.Keys.A == keycode){ player.setDeltaX(player.getDeltaX() + maxVelocity);}
        if(Input.Keys.D == keycode){ player.setDeltaX(player.getDeltaX() - maxVelocity);}

        //System.out.println("Delta X:" + player.getDeltaX() + " Delta Y: " + player.getDeltaY());

        if(!input.isKeyPressed(W) && !input.isKeyPressed(Input.Keys.A) && !input.isKeyPressed(Input.Keys.S) && !input.isKeyPressed(Input.Keys.D)){
            player.setVelocity(0, 0);
        }
        /**
         * If all keys are released then set player velocity to 0
         */

    }
    /**
     * Render all players
     * @param batch
     */
    public void renderAllPlayers(Batch batch){

        for(Player player : playerList){
            player.updatePosition();
            player.getPlayerSprite().draw(batch);
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
