package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {

    private Texture playerTexture;
    private Sprite playerSprite;
    private float deltaX;
    private float deltaY;

    /**
     * Constructor for players
     * @param playerTexture the texture of the player
     */
    public Player(Texture playerTexture) {
        this.playerTexture = playerTexture;
        this.playerSprite = new Sprite(playerTexture);
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

        playerSprite.setPosition(x + deltaX, y + deltaY);

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
}
