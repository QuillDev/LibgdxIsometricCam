package com.mygdx.game.Maps;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapManager {

    //Make singleton instance
    private static MapManager mapManager = new MapManager();

    //Default asset manager
    private AssetManager manager;

    //Make private constructor for singleton
    private MapManager(){
        this.manager = new AssetManager();
    };


    /**
     * Load a map by passing in it's string location
     * @param path to the file
     * @return the map
     */
    public TiledMap loadMap (String path){

        //Configure asset manager
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load(path, TiledMap.class);
        manager.finishLoading();

        //get it from the manager
        return manager.get(path, TiledMap.class);
    }

    //Get an instance of the map manager
    public static MapManager getInstance(){
        return mapManager;
    }
}
