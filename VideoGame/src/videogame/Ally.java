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
public class Ally extends Item{

    private int direction;
    private Game game;
    private Animation animationRight;
    private int speed;
    
    //Contructor
    public Ally(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.animationRight = new Animation(Assets.AllyRight, 100);
    }

    //Setter and getters
    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Override
    public void tick() {
        //speed and animation
        int random = (int)((Math.random()*3)+2);  
        setSpeed(random);
        // moving ally
        setX(getX()+getSpeed());
        animationRight.tick();
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
            setY(((int)(Math.random()*game.getHeight())));
            setX((int)(Math.random()*game.getWidth()-300));
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        /*
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
            //setSpeed(getSpeed()+1);
            //setFloorCoins(getFloorCoins() + 1);
        }
        */
        else if (getY() <= -20) {
            setY(-20);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}