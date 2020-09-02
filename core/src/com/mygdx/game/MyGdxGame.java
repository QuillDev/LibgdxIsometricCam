package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class MyGdxGame extends ApplicationAdapter {

    private GameManager gameManager = new GameManager();

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
        gameManager.dispose();
    }
}
