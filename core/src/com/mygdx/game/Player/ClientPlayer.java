package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;

public class ClientPlayer {

    private static ClientPlayer clientPlayer = new ClientPlayer();

    private Sprite playerSprite;
    private float deltaX;
    private float deltaY;

    /**
     * Constructor for the client player
     */
    private ClientPlayer() {
        this.deltaX = 0f;
        this.deltaY = 0f;
    }

    /**
     * Set velocity of the player to the specified velocity
     * @param x velocity
     * @param y velocity
     */
    public void setVelocity(float x, float y){
        deltaX = x;
        deltaY = y;
    }

    /**
     * Update the sprites position by using the value of deltas
     */
    public void updatePosition(){
        Sprite playerSprite = getPlayerSprite();
        float x = playerSprite.getX();
        float y = playerSprite.getY();

        float newX = x + deltaX;
        float newY = y + deltaY;

        //If your y is twice ur x, dat bad
        if(newY > newX * .5f){ return; };

        playerSprite.setPosition(newX, newY);
    }

    /**
     * Render the sprite in the batch
     * @param batch the batch to render with
     */
    public void render(Batch batch) {
        updatePosition();
        playerSprite.draw(batch);
    }
    /**
     * Get the player's sprite
     * @return the player's sprite
     */
    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    /**
     * Set teh
     */
    public void setPlayerSprite(Sprite sprite){
        this.playerSprite = sprite;
    }

    public static ClientPlayer getInstance(){
        return clientPlayer;
    }
}
