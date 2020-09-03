package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.GameState.GameManager;

public class Ashes extends ApplicationAdapter {

    private GameManager gameManager = GameManager.getInstance();


    @Override
    public void create(){
        gameManager.create();
    }

    @Override
    public void render(){
        gameManager.render();
    }

    @Override
    public void dispose(){
    }
}
