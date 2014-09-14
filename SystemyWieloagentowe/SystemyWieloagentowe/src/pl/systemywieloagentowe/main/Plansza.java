package pl.systemywieloagentowe.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plansza extends JFrame implements Runnable{

	int PLANSZA_LENGTH = 20;
	
	private Thread watek;
	JPanelGraphic[][] Plansza = new JPanelGraphic[7][PLANSZA_LENGTH];

	GridBagConstraints c = new GridBagConstraints();
	JFrame frame = new JFrame("Systemy Wieloagentowe");



	public Plansza() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1020, 600));

		
		
		frame.setLayout(new GridBagLayout());
		

		

       	
		RysujPlansze();

	
		Plansza[3][3].ChangeBackgroundImage("src/img/samochod2.png");
		
		frame.pack();
		frame.setVisible(true);

	 	

		
	}

	
	public void RysujPlansze()
	{

		for (int i = 0; i < PLANSZA_LENGTH; i++) {
			Plansza[0][i] = new JPanelGraphic("src/img/pobocze.png");
			c.gridx = i;
			c.gridy = 0;
			frame.add(Plansza[0][i], c);
		}

		for (int i = 1; i <=2; i++) {
			for (int j = 0; j < PLANSZA_LENGTH; j++) {
				Plansza[i][j] = new JPanelGraphic("src/img/droga.png");
				c.gridx = j;
				c.gridy = i;
				frame.add(Plansza[i][j], c);
			}

		}

		for (int i = 3; i <= 4; i++) {
			for (int j = 0; j < PLANSZA_LENGTH; j++) {
				Plansza[i][j] = new JPanelGraphic("src/img/droga.png");
				c.gridx = j;
				c.gridy = i ;
				frame.add(Plansza[i][j], c);
			}

		}
		
		for (int i = 0; i < PLANSZA_LENGTH; i++) {
			Plansza[5][i] = new JPanelGraphic("src/img/pobocze.png");
			c.gridx = i;
			c.gridy = 5;
			frame.add(Plansza[5][i], c);
		}
		
		
		
	}


	@Override
	public void run() {
		
		while (true) {
		
			
		}
		// TODO Auto-generated method stub
		
	}
	
}