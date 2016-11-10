/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package img;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author faiz
 */
public class BackgroundKlug extends JPanel{
    private Image image;

    public BackgroundKlug() {
        image = new ImageIcon(getClass().getResource("BackgroundKlug.jpg")).getImage();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics gd = (Graphics2D) grphcs.create();
        gd.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        gd.dispose();
    }
}
