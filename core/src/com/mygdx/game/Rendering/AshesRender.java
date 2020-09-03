package com.mygdx.game.Rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.mygdx.game.Camera.CameraController;
import com.mygdx.game.GameState.GameManager;
import com.mygdx.game.Maps.MapManager;
import com.mygdx.game.Player.ClientPlayer;

public class AshesRender {
    //Get the game manager
    private GameManager gameManager = GameManager.getInstance();

    //get the client player
    ClientPlayer clientPlayer = ClientPlayer.getInstance();

    //Create instance variables
    private OrthographicCamera camera;
    private IsometricTiledMapRenderer renderer;
    private MapManager mapManager = MapManager.getInstance();
    private TiledMap map;
    private MapLayers mapLayers;
    private TiledMapTileLayer floorLayer;
    private int[] decorationLayerIndices;
    private CameraController cameraController = CameraController.getInstance();


    /**
     * Create the ashes renderer
     */
    public AshesRender(){
        this.camera = gameManager.getCamera();
        this.map = mapManager.loadMap("maps/level.tmx");
        this.renderer = new IsometricTiledMapRenderer(map);
        this.mapLayers = map.getLayers();
        this.floorLayer = (TiledMapTileLayer) mapLayers.get("Floor");
        this.decorationLayerIndices = new int[]{
                mapLayers.getIndex("Foliage")
        };

    }

    /**
     * Render the scene
     */
    public void render(){
        //Clear the screen
        Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update the camera
        camera.update();
        renderer.setView(camera);

        //if the camera is set to free cam then allow freecam
        if(cameraController.getLocked()) { cameraController.lockToPlayer(ClientPlayer.getInstance()); }

        //Get the sprite batch
        Batch batch = renderer.getBatch();

        //Render the base
        batch.begin();

        //Get the floor
        renderer.renderTileLayer(floorLayer);

        //render the player
        clientPlayer.render(batch);

        //End the batch
        batch.end();

        //Render any post decorations
        renderer.render(decorationLayerIndices);
    }
}
