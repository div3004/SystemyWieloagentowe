package pl.systemywieloagentowe.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				JFrame frame = new JFrame("Systemy Wieloagentowe");
				JPanelGraphic[] mTopPobocze = new JPanelGraphic[20];
				JPanelGraphic[] mBottomPobocze = new JPanelGraphic[20];
				JPanelGraphic[][] mTopDroga = new JPanelGraphic[2][20];
				JPanelGraphic[][] mBottomDroga = new JPanelGraphic[2][20];
				GridBagConstraints c = new GridBagConstraints();
				JPanel panel1 = new JPanel();

				frame.setPreferredSize(new Dimension(1200, 600));
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLayout(new GridBagLayout());

				panel1.setLayout(new GridBagLayout());

				for (int i = 0; i < 20; i++) {
					mTopPobocze[i] = new JPanelGraphic("src/img/pobocze.png");
					c.gridx = i;
					c.gridy = 0;
					frame.add(mTopPobocze[i], c);
				}

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 20; j++) {
						mTopDroga[i][j] = new JPanelGraphic("src/img/droga.png");
						c.gridx = j;
						c.gridy = i + 1;
						frame.add(mTopDroga[i][j], c);
					}

				}

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 20; j++) {
						mBottomDroga[i][j] = new JPanelGraphic(
								"src/img/droga.png");
						c.gridx = j;
						c.gridy = i + 3;
						frame.add(mBottomDroga[i][j], c);
					}

				}

				for (int i = 0; i < 20; i++) {
					mBottomPobocze[i] = new JPanelGraphic("src/img/pobocze.png");
					c.gridx = i;
					c.gridy = 5;
					frame.add(mBottomPobocze[i], c);
				}

				frame.pack();
				frame.setVisible(true);
			}
		});
	}

}
