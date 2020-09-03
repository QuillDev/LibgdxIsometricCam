package com.mygdx.game.Camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.mygdx.game.GameState.GameManager;

public class CameraController {

    //get an instance of the game manager
    private GameManager gameManager = GameManager.getInstance();
    private OrthographicCamera cam = gameManager.getCamera();

    //Vectors for calculating camera position
    private final Vector3 currentPosition = new Vector3();
    private final Vector3 lastPosition = new Vector3(-1, -1, -1);
    private final Vector3 deltaPosition = new Vector3();

    //The z plane we are on (2d projection!)
    private final Plane zPlane = new Plane(new Vector3(0, 0, 1), 0);

    //whether to invert camera zooming
    private boolean invertZoom = false;
    private final float maxZoom = .5f;
    private final float minZoom = 5.5f;

    public void moveCamOnDrag (int x, int y) {
        Ray pickRay = cam.getPickRay(x, y);
        Intersector.intersectRayPlane(pickRay, zPlane, currentPosition);

        //Check if the position has changed
        if(lastPositionChanged()) {
            pickRay = cam.getPickRay(lastPosition.x, lastPosition.y);
            Intersector.intersectRayPlane(pickRay, zPlane, deltaPosition);
            deltaPosition.sub(currentPosition);
            cam.position.add(deltaPosition.x, deltaPosition.y, deltaPosition.z);
        }

        //set the last position to the changed position
        lastPosition.set(x, y, 0);
    }

    /**
     * Zoom the camera
     * @param amount amount the scroll wheel was zoomed
     */
    public void zoomCam(int amount){

        //Save the current zoom
        float currentZoom = cam.zoom;

        //If the amount scrolled is positive
        if(amount > 0){
            //If the zoom is not inverted, use typical zoom logic
            if(!invertZoom){ if(currentZoom <= minZoom){ cam.zoom += .1f; } }
            else{ if(currentZoom >= maxZoom){ cam.zoom -= .1f; } }
        }
        else {
            if(!invertZoom){if(currentZoom >= .5f){ cam.zoom -= .1f; } }
            else{ if(currentZoom <= minZoom) {cam.zoom += .1f;} }

        }

    }

    /**
     * when drag stops reset lastPosition to -1-1-1
     */
    public void setLastOnDragStop(){
        lastPosition.set(-1, -1, -1);
    }

    /**
     * Check if the lastPosition has changed
     * @return whether it changed or not
     */
    private boolean lastPositionChanged(){
        return (!(lastPosition.x == -1 && lastPosition.y == -1 && lastPosition.z == -1));
    }

}
