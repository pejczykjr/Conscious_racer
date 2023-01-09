package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class is responsible for manipulating keys.
 * @author Mateusz Pieczykolan
 */
public class KeyInput extends KeyAdapter {

    /**
     * Declaration of handler instance.
     */
    private final Handler handler;
    /**
     * Declaration of spawn instance.
     */
    private final Spawn spawn;

    /**
     * This variable keeps information if key is pressed and is made for better player movement.
     */
    private boolean []keyDown = new boolean[4];

    /**
     * Constructor, initializes keyDown array.
     * @param handler passing the handler instance
     * @param spawn passing the spawn instance
     */
    public KeyInput(Handler handler, Spawn spawn) {
        this.handler = handler;
        this.spawn=spawn;

        for(boolean initKeyDown:keyDown)
            initKeyDown=false;
    }

    /**
     * This method is responsible for moving Player object when WASD key is pressed. It also performs action<br>
     * to go to STATE.GamePaused when Esc is pressed and vice versa.
     * @param e the event to be processed
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();   //Gets pressed key

        //It loops through all the game objects and checks id
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.id == ID.Player1 || tempObject.id == ID.Player2 || tempObject.id == ID.Player3) {
                //Key events for Player when key is pressed

                if (key == KeyEvent.VK_W) {tempObject.setVelY(-spawn.getEnemyVelocity()/2); keyDown[0]=true;}
                if (key == KeyEvent.VK_S) {tempObject.setVelY(spawn.getEnemyVelocity()/2); keyDown[1]=true;}
                if (key == KeyEvent.VK_D) {tempObject.setVelX(spawn.getEnemyVelocity()); keyDown[2]=true;}
                if (key == KeyEvent.VK_A) {tempObject.setVelX(-spawn.getEnemyVelocity()); keyDown[3]=true;}
            }
        }

        if(key == KeyEvent.VK_ESCAPE){
            if(Game.gameState == STATE.Game)
                Game.gameState = STATE.GamePaused;
            else if(Game.gameState == STATE.GamePaused)
                Game.gameState = STATE.Game;
        }
    }

    /**
     * This method is responsible for setting velocity of Player object to 0 when WASD key is released.
     * @param e the event to be processed
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();   //Gets released key

        //It loops through all the game objects and checks id
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.id == ID.Player1 || tempObject.id == ID.Player2 || tempObject.id == ID.Player3) {
                //Key events for Player when key is released

                if (key == KeyEvent.VK_W) keyDown[0] = false;
                if (key == KeyEvent.VK_S) keyDown[1] = false;
                if (key == KeyEvent.VK_D) keyDown[2] = false;
                if (key == KeyEvent.VK_A) keyDown[3] = false;

                //Vertical movement
                if (!keyDown[0] && !keyDown[1])
                    tempObject.setVelY(0);
                //Horizontal movement
                if (!keyDown[2] && !keyDown[3])
                    tempObject.setVelX(0);
            }
        }
    }

}
