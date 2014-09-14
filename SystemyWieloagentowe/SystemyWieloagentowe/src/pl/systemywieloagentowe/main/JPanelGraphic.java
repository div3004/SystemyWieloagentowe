package pl.systemywieloagentowe.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelGraphic extends JPanel {

	private BufferedImage background;

	public JPanelGraphic(String path) {
		try {
			background = ImageIO.read(new File(path));
		} catch (IOException ex) {

		}
		this.setPreferredSize(new Dimension(50, 50));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
}
