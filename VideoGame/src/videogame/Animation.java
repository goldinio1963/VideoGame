/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Adolfo
 */
public class Animation {
    private int speed;                  //for the speed of every frame;
    private int index;                  //to get the index of the next frame to paint
    private long lastTime;              //to save the prevoius time of the animation
    private long timer;                 //to acumulate the time of the animation
    private BufferedImage[] frames;     // to store every image - frame

    
    /**
     * Creating the animation with the frames and the speed for each
     * @param frames an <code>array</code> of images
     * @param speed an <code>int</code> value for the speed of every frame
     */
    public Animation (BufferedImage[] frames, int speed){
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    // setters and getters
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
    
    
    public void tick(){
        //acumulating time from thr prevoiud tick to thid one
        timer += System.currentTimeMillis() - lastTime;
        
        //updating the lastTime for the next tick
        lastTime = System.currentTimeMillis();
        
        //check the timer to increse the index
        if(timer > speed) {
            index++;
            timer = 0;
            //check index not to get out of the bounds
            if(index >= frames.length) {
                index = 0;
            }
        }
    }

}