package com.mygdx.game.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.Maps.MapManager;
import com.mygdx.game.Player.ClientPlayer;
import com.mygdx.game.Rendering.AshesRender;

public class GameManager {

    //Create the sole instance
    private final static GameManager gameManager = new GameManager();
    private final ClientPlayer clientPlayer = ClientPlayer.getInstance();
    private final MapManager mapManager = MapManager.getInstance();

    private GameManager(){ }

    //Map properties
    private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles, mapHeightInPixels, mapWidthInPixels;

    //Camera and renderer
    private OrthographicCamera camera;

    //TODO Add map stuff to it's own file
    public void create() {
        //Get the player texture
        Texture playerTexture = new Texture(Gdx.files.internal("tiles/dev/dummy.png"));

        clientPlayer.setPlayerSprite(new Sprite(playerTexture));

        //set local properties using map properties
        setProperties();

        //Configure the camera
        this.camera = new OrthographicCamera(320.f, 180.f);
        setupCamera();

        //set the input processor
        Gdx.input.setInputProcessor(new InputHandler());
    }

    //TODO Export this to a render file
    public void render() {
        new AshesRender().render();
    }

    /**
     * Set the local properties using the maps properties
     */
    private void setProperties(){
        //Get the tiled map
        TiledMap map = mapManager.loadMap("maps/level.tmx");

        //Set local properties by using the maps properties
        MapProperties mapProperties = map.getProperties();
        tileWidth = mapProperties.get("tilewidth", Integer.class);
        tileHeight = mapProperties.get("tileheight", Integer.class);
        mapWidthInTiles = mapProperties.get("width", Integer.class);
        mapHeightInTiles = mapProperties.get("height", Integer.class);
        mapWidthInPixels = mapWidthInTiles * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;
    }


    /**
     * Setup the camera
     */
    private void setupCamera(){
        //start cam in the middle of the map
        camera.position.x = mapWidthInPixels  * .5f;
        camera.position.y = mapHeightInPixels * .5f;
    }


    //GETTERS

    /**
     * Get the camera
     * @return the camera
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Get an instance of the game manager
     * @return the game manager
     */
    public static GameManager getInstance(){
        return gameManager;
    }
}
