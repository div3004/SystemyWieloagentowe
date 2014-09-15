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
	int TIME = 5;
	int STOP_RIGHT = 5;
	boolean ZATRZYMANIE_RIGHT = false;
	Samochod mSamochodRight;
	Samochod mSamochodLeft;
	Karetka mKaretkaRight;
	Karetka mKaretkaLeft;
	Policja mPolicjaRight;
	private Thread watek;

	JPanelGraphic[][] Plansza = new JPanelGraphic[6][PLANSZA_LENGTH];

	GridBagConstraints c = new GridBagConstraints();
	JFrame frame = new JFrame("Systemy Wieloagentowe");

	public Plansza() {
		mKaretkaRight = new Karetka();
		mKaretkaLeft = new Karetka();
		mSamochodRight = new Samochod(2);
		mSamochodRight.setmPoleX(4);
		mSamochodLeft = new Samochod(1);
		mSamochodLeft.setmPoleX(1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1100, 600));

		frame.setLayout(new GridBagLayout());

		RysujPlansze();

		// losujBlokadeRight();
		losujPolicjeRight();
		losujBlokadeLeft();

		watek = new Thread(this);
		watek.start();

		frame.pack();
		frame.setVisible(true);

	}

	public void losujPolicjeRight() {
		Random random = new Random();

		int przeszkoda = random.nextInt(15) + 3;
		Plansza[5][przeszkoda].setBlokada(true);
		Plansza[5][przeszkoda].ChangeBackgroundImage("src/img/policja.png");
		mPolicjaRight = new Policja();
	}

	public void losujBlokadeRight() {
		Random random = new Random();

		int przeszkoda = random.nextInt(15) + 3;
		Plansza[4][przeszkoda].setBlokada(true);
		Plansza[4][przeszkoda].ChangeBackgroundImage("src/img/blokada.png");

	}

	public void losujBlokadeLeft() {
		Random random = new Random();

		int przeszkoda = random.nextInt(15) + 3;
		Plansza[1][przeszkoda].setBlokada(true);
		Plansza[1][przeszkoda].ChangeBackgroundImage("src/img/blokada.png");

	}

	public void SprawszPolicja() {
		if ((Plansza[5][INC + 1].getBlokada())
				&& (mSamochodRight.getmPredkosc() >= 2)) {
			ZATRZYMANIE_RIGHT = true;
			mSamochodRight.setmPoleX(5);
		} else if ((Plansza[5][INC + 2].getBlokada())
				&& (mSamochodRight.getmPredkosc() >= 2)) {
			ZATRZYMANIE_RIGHT = true;
			mSamochodRight.setmPoleX(5);

		}
	}

	public void SprawdzPoleBlokada() {
		Random random = new Random();

		if (!ZATRZYMANIE_RIGHT) {
			if (mSamochodRight.SprawdzBlokade(Plansza[mSamochodRight
					.getmPoleX()][INC + 1].getBlokada())) {

				int WezwijRight = random.nextInt(10);
				if (WezwijRight > 7) {
					if (!mKaretkaRight.getByla()) {
						mKaretkaRight.setmPoleX(mSamochodRight.getmPoleX() + 1);
						mKaretkaRight.setmPoleY(INC);
						Plansza[mKaretkaRight.getmPoleX()][mKaretkaRight
								.getmPoleY()]
								.ChangeBackgroundImage("src/img/karetka.png");
						mKaretkaRight.setByla(true);
					}
				}
			} else if (mSamochodRight.SprawdzBlokade(Plansza[mSamochodRight
					.getmPoleX()][INC + 2].getBlokada())) {

				int WezwijRight = random.nextInt(10);
				if (WezwijRight > 7) {
					if (!mKaretkaRight.getByla()) {
						mKaretkaRight.setmPoleX(mSamochodRight.getmPoleX() + 1);
						mKaretkaRight.setmPoleY(INC + 1);
						Plansza[mKaretkaRight.getmPoleX()][mKaretkaRight
								.getmPoleY()]
								.ChangeBackgroundImage("src/img/karetka.png");
						mKaretkaRight.setByla(true);
					}
				}
			}
		}

		if (DEC > 2) {
			if (mSamochodLeft
					.SprawdzBlokade(Plansza[mSamochodLeft.getmPoleX()][DEC - 1]
							.getBlokada())) {

				int WezwijLeft = random.nextInt(10);
				if (WezwijLeft > 7) {
					if (!mKaretkaLeft.getByla()) {
						mKaretkaLeft.setmPoleX(mSamochodLeft.getmPoleX() - 1);
						mKaretkaLeft.setmPoleY(DEC - 2);
						Plansza[mKaretkaLeft.getmPoleX()][mKaretkaLeft
								.getmPoleY()]
								.ChangeBackgroundImage("src/img/karetka.png");
						mKaretkaLeft.setByla(true);
					}
				}
			} else if (mSamochodLeft.SprawdzBlokade(Plansza[mSamochodLeft
					.getmPoleX()][DEC - 2].getBlokada())) {
				int WezwijLeft = random.nextInt(10);
				if (WezwijLeft > 7) {
					if (!mKaretkaLeft.getByla()) {
						mKaretkaLeft.setmPoleX(mSamochodLeft.getmPoleX() - 1);
						mKaretkaLeft.setmPoleY(DEC - 3);
						Plansza[mKaretkaLeft.getmPoleX()][mKaretkaLeft
								.getmPoleY()]
								.ChangeBackgroundImage("src/img/karetka.png");
						mKaretkaLeft.setByla(true);
					}
				}
			}
		}

	}

	public void CzyscPlansze() {
		for (int i = 0; i < PLANSZA_LENGTH; i++) {
			Plansza[0][i].ChangeBackgroundImage("src/img/pobocze.png");
			Plansza[0][i].setBlokada(false);
		}
		for (int i = 1; i <= 2; i++) {
			for (int j = 0; j < PLANSZA_LENGTH; j++) {
				Plansza[i][j].ChangeBackgroundImage("src/img/droga.png");
				Plansza[i][j].setBlokada(false);
			}

		}
		for (int i = 3; i <= 4; i++) {
			for (int j = 0; j < PLANSZA_LENGTH; j++) {
				Plansza[i][j].ChangeBackgroundImage("src/img/droga.png");
				Plansza[i][j].setBlokada(false);
			}
		}
		for (int i = 0; i < PLANSZA_LENGTH; i++) {
			Plansza[5][i].ChangeBackgroundImage("src/img/pobocze.png");
			Plansza[5][i].setBlokada(false);
		}
		mKaretkaRight.setByla(false);
		mKaretkaLeft.setByla(false);
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

			SprawdzPoleBlokada();

			SprawszPolicja();

			mSamochodRight.setmPoleY(INC);
			mSamochodLeft.setmPoleY(DEC);

			Plansza[mSamochodRight.getmPoleX()][INC]
					.ChangeBackgroundImage("src/img/samochod.png");

			Plansza[mSamochodLeft.getmPoleX()][DEC]

			.ChangeBackgroundImage("src/img/samochod2.png");

			try {
				watek.sleep(150);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Plansza[mSamochodLeft.getmPoleX()][DEC].Clear();
			Plansza[mSamochodRight.getmPoleX()][INC].Clear();

			if (!ZATRZYMANIE_RIGHT) {

				INC = INC + mSamochodRight.getmPredkosc();
			} else {
				STOP_RIGHT--;
				if (STOP_RIGHT == 0) {
					ZATRZYMANIE_RIGHT = false;
				}
			}

			DEC = DEC - mSamochodLeft.getmPredkosc();
			frame.repaint();

			if (DEC < 0) {
				DEC = 21;
				mSamochodLeft.setmPoleX(1);
				mSamochodLeft.setmPredkosc();
			}

			if (INC >= 23) {

				TIME--;
				if (TIME == 0) {
					CzyscPlansze();
					// losujBlokadeRight();
					losujPolicjeRight();
					losujBlokadeLeft();
					TIME = 5;
				}

				INC = 0;
				mSamochodRight.setmPoleX(4);
				mSamochodRight.setmPredkosc();

			}

		}
		// TODO Auto-generated method stub

	}

}