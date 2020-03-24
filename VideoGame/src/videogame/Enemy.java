/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 * class of the enemies
 * @author Adolfo
 */
public class Enemy extends Item{

    private int direction;
    private Game game;
    private Animation animationleft;
    private int speed;
    
    //constructor
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.animationleft = new Animation(Assets.EnemyLeft, 100);
        
    }
    
    //setters and getters

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
        //initialization

        int random = (int)((Math.random()*3)+3);  
        setSpeed(random);
        // moving enemy
        setX(getX()-getSpeed());
        animationleft.tick();
        
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
            setY(((int)(Math.random()*game.getHeight())));
            setX((int)(Math.random()*game.getWidth()+300));
        }
        
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
            //setSpeed(getSpeed()+1);
            //setFloorCoins(getFloorCoins() + 1);
        }
        /*
        else if (getY() <= -20) {
            setY(-20);
        }
        */
    }

    @Override
    public void render(Graphics g) {
        
        g.drawImage(animationleft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}