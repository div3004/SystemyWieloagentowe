package pl.systemywieloagentowe.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelGraphic extends JPanel {

	private BufferedImage background;

	public JPanelGraphic(String Path) {
		try {
			background = ImageIO.read(new File(Path));
		} catch (IOException ex) {

		}
		this.setPreferredSize(new Dimension(50, 50));
	}

	
	public BufferedImage getBackgroundImage() {
		// TODO Auto-generated method stub
		return background;
	}
	
	
	
	public void Clear()
	{
	    try {                
        	background = ImageIO.read(new File("src/img/droga.png"));
         } catch (IOException ex) {
           
         }	
	}
	
	
    public void ChangeBackgroundImage(String NewPath)
    {
        try {                
        	background = ImageIO.read(new File(NewPath));
         } catch (IOException ex) {
           
         }
 
    }
    
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
}
