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
public class SpriteSheet {
    private BufferedImage sheet;    //Store the image sheet
    
    /**
     *  Create a new spritesheet
     * @param sheet the <code>image</code> with the sprite
     */
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    /**
     * Crop a sprite from the spritesheet
     * @param x an <code>int</code> value with the x coordinate
     * @param y an <code>int</code> value with the y coordinate
     * @param width an <code>int</code> value with the width
     * @param height an <code>int</code> value with the height
     * @return 
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
