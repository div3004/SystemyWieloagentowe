package pl.systemywieloagentowe.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.JFrame;

public class Plansza extends JFrame implements Runnable {

	int PLANSZA_LENGTH = 25;
	int INC = 0;
	int DEC = PLANSZA_LENGTH - 1;

	Samochod mSamochod;

	private Thread watek;

	JPanelGraphic[][] Plansza = new JPanelGraphic[6][PLANSZA_LENGTH];

	GridBagConstraints c = new GridBagConstraints();
	JFrame frame = new JFrame("Systemy Wieloagentowe");

	public Plansza() {

		mSamochod = new Samochod();
		mSamochod.setmPoleX(4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1100, 600));

		frame.setLayout(new GridBagLayout());

		RysujPlansze();

		losujBlokade();
		
		
		watek = new Thread(this);
		watek.start();

		frame.pack();
		frame.setVisible(true);

	}
	
	
	
	
   public void losujBlokade()
   {
	   Random random = new Random();
	   int przeszkoda = random.nextInt(20);
	   Plansza[4][przeszkoda].setBlokada(true);
	   Plansza[4][przeszkoda].ChangeBackgroundImage("src/img/blokada.png");
   }
	

   
    public void SprawdzPole()
    {
    	
     mSamochod.SprawdzBlokade(Plansza[mSamochod.getmPoleX()][INC+1].getBlokada());
     mSamochod.SprawdzBlokade(Plansza[mSamochod.getmPoleX()][INC+2].getBlokada());
    	 
 
     
    }
   
   
   
	public void RysujPlansze() {

		for (int i = 0; i < PLANSZA_LENGTH; i++) {
			Plansza[0][i] = new JPanelGraphic("src/img/pobocze.png");

			if (i <= PLANSZA_LENGTH - 5) {
				c.gridx = i;
				c.gridy = 0;
				frame.add(Plansza[0][i], c);
			}
		}

		for (int i = 1; i <= 2; i++) {
			for (int j = 0; j < PLANSZA_LENGTH; j++) {
				Plansza[i][j] = new JPanelGraphic("src/img/droga.png");
				if (j <= PLANSZA_LENGTH - 5) {
					c.gridx = j;
					c.gridy = i;
					frame.add(Plansza[i][j], c);
				}
			}

		}

		for (int i = 3; i <= 4; i++) {
			for (int j = 0; j < PLANSZA_LENGTH; j++) {
				Plansza[i][j] = new JPanelGraphic("src/img/droga.png");
				if (j <= PLANSZA_LENGTH - 5) {
					c.gridx = j;
					c.gridy = i;
					frame.add(Plansza[i][j], c);
				}
			}

		}

		for (int i = 0; i < PLANSZA_LENGTH; i++) {
			Plansza[5][i] = new JPanelGraphic("src/img/pobocze.png");
			if (i <= PLANSZA_LENGTH - 5) {
				c.gridx = i;
				c.gridy = 5;
				frame.add(Plansza[5][i], c);
			}
		}

	}

	@Override
	public void run() {

		while (true) {

			
			
			
			SprawdzPole();
	
			mSamochod.setmPoleY(INC);
			
			
			
			Plansza[mSamochod.getmPoleX()][INC].ChangeBackgroundImage("src/img/samochod.png");
			Plansza[1][DEC].ChangeBackgroundImage("src/img/samochod2.png");
			try {
				watek.sleep(150);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Plansza[mSamochod.getmPoleX()][INC].Clear();
			Plansza[1][DEC].Clear();
			INC=INC+mSamochod.getmPredkosc();
			DEC--;
			frame.repaint();

			if (DEC == 0) {
				DEC = 21;
			}

			if (INC >= 23) {
				INC = 0;
				mSamochod.setmPoleX(4);
				mSamochod.setmPredkosc();
			}

		}
		// TODO Auto-generated method stub

	}

}