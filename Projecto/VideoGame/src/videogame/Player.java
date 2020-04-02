/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author adolfo
 */
public class Player extends Item {

    private int direction;
    private int width;
    private int height;
    private Game game;
    //animation
    private Animation animationUp;      //to store the animation for going up
    private Animation animationLeft;    //to store the animation for going left
    private Animation animationDown;    //to store the animation for going down
    private Animation animationRight;   //to store the animation for going right
    private Animation animationIdle;    //to store the animation of idle
    boolean movingRight;
    boolean movingLeft;
    boolean movingUp;
    boolean movingDown;
    boolean idle;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        //animation
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
        this.animationIdle = new Animation(Assets.playerIdle, 1000);
        this.idle = true;
        this.movingDown = false;
        this.movingLeft = false;
        this.movingRight = false;
        this.movingUp = false;

    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    @Override
    public void tick() {

        //animationIdle.tick();
        // moving player depending on flags
        if (game.getKeyManager().up) {
            animationUp.tick();
            setY(getY() - 5);
            setMovingUp(true);
            setMovingDown(false);
            setMovingRight(false);
            setMovingLeft(false);
            setIdle(false);
        } else {
            if (game.getKeyManager().down) {
                animationDown.tick();
                setY(getY() + 5);
                setMovingUp(false);
                setMovingDown(true);
                setMovingRight(false);
                setMovingLeft(false);
                setIdle(false);

            } else {
                if (game.getKeyManager().left) {
                    animationLeft.tick();
                    setX(getX() - 5);
                    setMovingUp(false);
                    setMovingDown(false);
                    setMovingRight(false);
                    setMovingLeft(true);
                    setIdle(false);
                } else {
                    if (game.getKeyManager().right) {
                        animationRight.tick();
                        setX(getX() + 5);
                        setMovingUp(false);
                        setMovingDown(false);
                        setMovingRight(true);
                        setMovingLeft(false);
                        setIdle(false);
                    } else {
                        setMovingUp(false);
                        setMovingDown(false);
                        setMovingRight(false);
                        setMovingLeft(false);
                        setIdle(true);
                    }
                }
            }
        }
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        } else if (getY() <= -20) {
            setY(-20);
        }
    }
    

    @Override
    public void render(Graphics g) {
        if (isMovingUp()) {
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        } else {
            if (isMovingRight()) {
                g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            } else {
                if (isMovingLeft()) {
                    g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                } else {
                    if (isMovingDown()) {
                        g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    } else {
                        if(!game.isPause()){
                            animationIdle.tick();
                            g.drawImage(animationIdle.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                        else{
                            g.drawImage(animationIdle.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }
                }
            }
        }
    }
}
