package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class GameManager {

    //Assets Manager
    private AssetManager manager;

    //Map
    private TiledMap map;
    private TiledMapTileLayer terrainLayer;
    private int[] decorationLayerIndices;

    //Map properties
    private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles, mapHeightInPixels, mapWidthInPixels;

    //Camera and renderer
    private OrthographicCamera camera;
    private IsometricTiledMapRenderer renderer;



    public void create() {
        this.manager = new AssetManager();

        //setup the manager
        setupManager();

        //set the map by loading it from the manager
        map = manager.get("maps/level.tmx", TiledMap.class);

        //Read map layers
        MapLayers mapLayers = map.getLayers();
        this.terrainLayer = (TiledMapTileLayer) mapLayers.get("Floor");
        this.decorationLayerIndices = new int[]{
                mapLayers.getIndex("Foliage")
        };

        //set local properties using map properties
        setProperties();

        //Configure the camera
        this.camera = new OrthographicCamera(320.f, 180.f);
        setupCamera();

        //create a renderer for the map object
        this.renderer = new IsometricTiledMapRenderer(map);
    }

    //TODO Export this to a render file
    public void render(){
        //Clear the screen
        Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update the camera
        camera.update();
        renderer.setView(camera);

        //render
        renderer.render(decorationLayerIndices);
        renderer.getBatch().begin();
        renderer.renderTileLayer(terrainLayer);
        renderer.getBatch().end();
    }

    public void dispose(){

        //free resources
        manager.dispose();
    }
    /**
     * Setup the manager to load our level
     */
    private void setupManager(){
        //Set it to a TmxMapLoader
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load("maps/level.tmx", TiledMap.class);
        manager.finishLoading();
    }

    /**
     * Set the local properties using the maps properties
     */
    private void setProperties(){
        //Set local properties by using the maps properties
        MapProperties mapProperties = map.getProperties();
        tileWidth = mapProperties.get("tilewidth", Integer.class);
        tileHeight = mapProperties.get("tileheight", Integer.class);
        mapWidthInTiles = mapProperties.get("width", Integer.class);
        mapHeightInTiles = mapProperties.get("height", Integer.class);
        mapWidthInPixels = mapWidthInTiles * tileWidth;
        mapHeightInTiles = mapHeightInTiles * tileHeight;
    }

    /**
     * Setup the camera
     */
    private void setupCamera(){
        //start cam in the middle of the map
        camera.position.x = mapHeightInPixels * .5f;
        camera.position.y = mapHeightInPixels * .5f;
    }
}
