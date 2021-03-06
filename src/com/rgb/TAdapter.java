package com.rgb;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * This class of game control through the keyboard
 */
public class TAdapter extends KeyAdapter {
    Maze m;
    static StartPage startPage;

    /** Sound panel
     *
     */
    Sound bonusMusic = new Sound(new File("src/music/bonus.mp3"));
    Sound intoNextLevelMusic = new Sound(new File("src/music/intoNextLevel.mp3"));

    /**
     * Constructor
     * @param maze - reference to the maze class
     * @param start - reference to the game frame class
     */
    public TAdapter(Maze maze, StartPage start){
        m = maze;
        startPage = start;
    }

    /**
     *  Change the hero’s displacement by y if the hero moves up
     */
    private void up(){
        m.setReq_dy(-1);
    }

    /**
     * Change the hero’s displacement by y if the hero moves down
     */
    private void down(){
        m.setReq_dy(1);
    }

    /**
     * Change the hero’s displacement by x if the hero moves left
     */
    private void left(){
        m.setReq_dx(-1);
    }

    /**
     * Change the hero’s displacement by x if the hero moves right
     */
    private void right(){
        m.setReq_dx(1);
    }

    /**
     * Override method that check when the key goes down
     * @param e -- An event which indicates that a keystroke occurred in a component
     */
    @Override
    public void keyPressed(KeyEvent e) {
        m.setReq_dx(0);
        m.setReq_dy(0);
        int code = e.getKeyCode();
        //up
        if(code == KeyEvent.VK_UP && m.isInGame()){
            up();
        }
        //down
        if(code == KeyEvent.VK_DOWN && m.isInGame()){
            down();
        }
        //left
        if(code == KeyEvent.VK_LEFT && m.isInGame()){
            left();
        }
        //right
        if(code == KeyEvent.VK_RIGHT && m.isInGame()){
            right();
        }

        if(code == KeyEvent.VK_SPACE && m.isInGame()){
            if(m.isInGame()==true && m.isDying() == false){

                if(m.isHeart()==true) {
                    bonusMusic.play(false);
                    m.addHeroLives();
                }
                if(m.isPortal()){
                    intoNextLevelMusic.play(false);
                    m.nextLevel();
                }

            }

            if(m.isGameFinished() == true && m.isDying() == false) {
                startPage.endPageWin();
            }
        }
        if(code == KeyEvent.VK_ENTER && m.isInGame()){
          //  startPage.endPageLoser();
        }

        if(code == KeyEvent.VK_ESCAPE && m.isInGame())
        {
            startPage.pausePage();
        }
        if(code == KeyEvent.VK_1 && m.isInGame())
        {
            m.setCheat(1);
        }

        if(code == KeyEvent.VK_0 && m.isInGame())
        {
            m.setCheat(0);
        }
        if(code == KeyEvent.VK_A && m.isInGame())
        {
            double new_speed = m.getMyHeroSpeed(m.gameLevel-1);
            m.setMyHeroSpeed(new_speed*1.05, m.gameLevel-1);
        }
        if(code == KeyEvent.VK_D && m.isInGame())
        {
            double new_speed = m.getMyHeroSpeed(m.gameLevel-1);
            m.setMyHeroSpeed(new_speed/1.05, m.gameLevel-1);
        }
        if(code == KeyEvent.VK_S && m.isInGame())
        {
            m.setMyHeroSpeed(1, m.gameLevel-1);
        }

    }

    /**
     * Stop hero if key is released
     * @param e - An event which indicates that a keystroke occurred in a component
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP && m.isInGame()){
            m.setReq_dy(0);
        }
        //down
        if(code == KeyEvent.VK_DOWN && m.isInGame()){
            m.setReq_dy(0);
        }
        //left
        if(code == KeyEvent.VK_LEFT && m.isInGame()){
            m.setReq_dx(0);
        }
        //right
        if(code == KeyEvent.VK_RIGHT && m.isInGame()){
            m.setReq_dx(0);
        }

    }

}