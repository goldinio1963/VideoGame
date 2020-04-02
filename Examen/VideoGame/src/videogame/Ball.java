/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 * class of ally images that helps the player
 * @author Adolfo
 */
public class Ball extends Item{

    private int direction;
    private Game game;
    private boolean roof;
    private int speed;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    
    //Contructor
    public Ball(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.left = false;
        this.right = true;
        this.up = true;
        this.down = false;
    }

    //Setter and getters
    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isRoof() {
        return roof;
    }

    public void setRoof(boolean roof) {
        this.roof = roof;
    }    

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    @Override
    public void tick() {
        //initialization
        
        // moving ball
        
        if(isRight()) {
            if(isUp()) {
                setX(getX() + 6);
                setY(getY() - 2);
            } else {
                setX(getX() + 6);
                setY(getY() + 2);
            }
        }
        
        if (isLeft()) { 
             if(isUp()) {
                setX(getX() - 6);
                setY(getY() - 2);
            } else { 
                setX(getX() - 6);
                setY(getY() + 2);
            }
        }
        
        // reset x position and y position if colision
        if (getX() + getWidth()-20 >= game.getWidth()) {
            setX(game.getWidth() - getWidth());
            setRight(false);
            setLeft(true);
        }
        else if (getX() <= -30) {
            setX(-30);
            setRight(true);
            setLeft(false);
        }
        if (getY() + getHeight()-20 >= game.getHeight()) {
            setY(game.getHeight() - getHeight());
            setUp(true);
            setDown(false);
        }
        else if (getY() <= -20) {
            setY(-20);
            setUp(false);
            setDown(true);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ally, getX(), getY(), getWidth(), getHeight(), null);
    }
}