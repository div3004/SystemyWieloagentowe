package pl.systemywieloagentowe.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Plansza extends JFrame implements Runnable {

	int PLANSZA_LENGTH = 25;
	int INC = 0;
	int DEC = PLANSZA_LENGTH - 1;
	int TIME = 5;
	int STOP_RIGHT = 5;
	boolean ZATRZYMANIE_RIGHT = false;
	int STOP_LEFT = 5;
	boolean ZATRZYMANIE_LEFT = false;
	boolean FOTORADAR_RIGHT = false;
	boolean FOTORADAR_LEFT = false;
	Samochod mSamochodRight;
	Samochod mSamochodLeft;
	Karetka mKaretkaRight;
	Karetka mKaretkaLeft;
	Policja mPolicjaRight;
	Fotoradar mFotoradarRight;
	Policja mPolicjaLeft;
	private Thread watek;

	JPanelGraphic[][] Plansza = new JPanelGraphic[6][PLANSZA_LENGTH];
	JPanel mplanszaPanel = new JPanel(new GridBagLayout());
	JPanel mStatystykiPanel = new JPanel(new GridBagLayout());
	JTextArea textAreaPolicja = new JTextArea(12, 24);
	JScrollPane scrollPanePolicja = new JScrollPane(textAreaPolicja);
	JTextArea textAreaKaretka = new JTextArea(12, 35);
	JScrollPane scrollPaneKaretka = new JScrollPane(textAreaKaretka);
	JTextArea textAreaFotoradar = new JTextArea(12, 24);
	JScrollPane scrollPaneFotoradar = new JScrollPane(textAreaFotoradar);
	GridBagConstraints c = new GridBagConstraints();
	JFrame frame = new JFrame("Systemy Wieloagentowe");

	public Plansza() {
		mKaretkaRight = new Karetka();
		mKaretkaLeft = new Karetka();
		mSamochodRight = new Samochod(2);
		mSamochodRight.setmPoleX(4);
		mSamochodLeft = new Samochod(1);
		mSamochodLeft.setmPoleX(1);
		mFotoradarRight = new Fotoradar();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1100, 600));
		textAreaPolicja.enable(false);
		textAreaKaretka.enable(false);
		textAreaFotoradar.enable(false);
		frame.setLayout(new BorderLayout());

		RysujPlansze();
		RysujStatystyki();

		Random losuj = new Random();
		int left = losuj.nextInt(3) + 1;
		int right = losuj.nextInt(3) + 1;

		// losujBlokadeRight();

		switch (left) {
		case 1:
			losujBlokadeLeft();
			break;
		case 2:
			losujPolicjeLeft();
			break;
		case 3:
			losujFotoradarLeft();
			break;
		}

		switch (right) {
		case 1:
			losujBlokadeRight();
			break;
		case 2:
			losujPolicjeRight();
			break;
		case 3:
			losujFotoradarRight();
			break;
		}

		frame.add(mplanszaPanel, BorderLayout.CENTER);

		watek = new Thread(this);
		watek.start();

		frame.add(mStatystykiPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

	}

	public void losujFotoradarRight() {
		Random random = new Random();
		FOTORADAR_RIGHT = true;
		int przeszkoda = random.nextInt(15) + 3;
		Plansza[5][przeszkoda].setBlokada(true);
		Plansza[5][przeszkoda].ChangeBackgroundImage("src/img/fotoradar.png");
	}

	public void losujFotoradarLeft() {
		Random random = new Random();
		FOTORADAR_LEFT = true;
		int przeszkoda = random.nextInt(15) + 3;
		Plansza[0][przeszkoda].setBlokada(true);
		Plansza[0][przeszkoda].ChangeBackgroundImage("src/img/fotoradar2.png");
	}

	public void RysujStatystyki() {

		JLabel label1 = new JLabel("Lista mandatów policji: ");
		c.gridx = 0;
		c.gridy = 0;
		mStatystykiPanel.add(label1, c);

		c.gridx = 1;
		c.gridy = 1;
		mStatystykiPanel.add(Box.createHorizontalStrut(65), c);

		JLabel label2 = new JLabel("Czy wezwano karetkê?");
		c.gridx = 2;
		c.gridy = 0;
		mStatystykiPanel.add(label2, c);

		c.gridx = 3;
		c.gridy = 1;
		mStatystykiPanel.add(Box.createHorizontalStrut(65), c);

		JLabel label3 = new JLabel("Lista mandatów Fotoradar: ");
		c.gridx = 4;
		c.gridy = 0;
		mStatystykiPanel.add(label3, c);

		c.gridx = 0;
		c.gridy = 1;
		mStatystykiPanel.add(scrollPanePolicja, c);
		c.gridx = 2;
		c.gridy = 1;
		mStatystykiPanel.add(scrollPaneKaretka, c);
		c.gridx = 4;
		c.gridy = 1;
		mStatystykiPanel.add(scrollPaneFotoradar, c);
		c.gridx = 0;
		c.gridy = 3;
		mStatystykiPanel.add(Box.createVerticalStrut(40), c);
	}

	public void losujPolicjeRight() {
		Random random = new Random();

		int przeszkoda = random.nextInt(15) + 3;
		Plansza[5][przeszkoda].setBlokada(true);
		Plansza[5][przeszkoda].ChangeBackgroundImage("src/img/policja.png");
		mPolicjaRight = new Policja();
		FOTORADAR_RIGHT = false;
	}

	public void losujPolicjeLeft() {
		Random random = new Random();

		int przeszkoda = random.nextInt(15) + 3;
		Plansza[0][przeszkoda].setBlokada(true);
		Plansza[0][przeszkoda].ChangeBackgroundImage("src/img/policja2.png");
		mPolicjaLeft = new Policja();
		FOTORADAR_LEFT = false;
	}

	public void losujBlokadeRight() {
		Random random = new Random();
		FOTORADAR_RIGHT = false;
		int przeszkoda = random.nextInt(15) + 3;
		Plansza[4][przeszkoda].setBlokada(true);
		Plansza[4][przeszkoda].ChangeBackgroundImage("src/img/blokada.png");

	}

	public void losujBlokadeLeft() {
		Random random = new Random();
		FOTORADAR_LEFT = false;
		int przeszkoda = random.nextInt(15) + 3;
		Plansza[1][przeszkoda].setBlokada(true);
		Plansza[1][przeszkoda].ChangeBackgroundImage("src/img/blokada.png");

	}

	public void SprawszPolicjaLeft() {
		if (DEC > 2) {
			if (!ZATRZYMANIE_LEFT) {

				if ((Plansza[0][DEC - 1].getBlokada())
						&& (mSamochodLeft.getmPredkosc() >= 2)) {
					ZATRZYMANIE_LEFT = true;

					Random losuj = new Random();
					int kwota = losuj.nextInt(5) + 1;

					textAreaPolicja.append("Kierowca 1 otrzyma³ mandat: "
							+ kwota + "00 PLN\n");

					mSamochodLeft.setmPoleX(0);
				} else if ((Plansza[0][DEC - 2].getBlokada())
						&& (mSamochodLeft.getmPredkosc() >= 2)) {
					ZATRZYMANIE_LEFT = true;

					Random losuj = new Random();
					int kwota = losuj.nextInt(5) + 1;

					textAreaPolicja.append("Kierowca 1 otrzyma³ mandat: "
							+ kwota + "00 PLN\n");

					mSamochodLeft.setmPoleX(0);

				}
			} else {
				STOP_LEFT--;
				if (STOP_LEFT <= 0) {

					ZATRZYMANIE_LEFT = false;
					STOP_LEFT = 5;

					mSamochodLeft.setmPoleX(1);
					Plansza[0][DEC]
							.ChangeBackgroundImage("src/img/pobocze.png");

					mSamochodLeft.setmPredkosc(1);

				}

			}
		}
	}

	public void SprawdzPolicjaRight() {

		if (!ZATRZYMANIE_RIGHT) {

			if ((Plansza[5][INC + 1].getBlokada())
					&& (mSamochodRight.getmPredkosc() >= 2)) {
				ZATRZYMANIE_RIGHT = true;
				mSamochodRight.setmPoleX(5);

				Random losuj = new Random();
				int kwota = losuj.nextInt(5) + 1;

				textAreaPolicja.append("Kierowca 2 otrzyma³ mandat: " + kwota
						+ "00 PLN\n");

			} else if ((Plansza[5][INC + 2].getBlokada())
					&& (mSamochodRight.getmPredkosc() >= 2)) {
				ZATRZYMANIE_RIGHT = true;

				Random losuj = new Random();
				int kwota = losuj.nextInt(5) + 1;

				textAreaPolicja.append("Kierowca 2 otrzyma³ mandat: " + kwota
						+ "00 PLN\n");

				mSamochodRight.setmPoleX(5);

			}
		} else {
			STOP_RIGHT--;
			if (STOP_RIGHT <= 0) {

				ZATRZYMANIE_RIGHT = false;
				STOP_RIGHT = 5;

				mSamochodRight.setmPoleX(4);
				Plansza[5][INC].ChangeBackgroundImage("src/img/pobocze.png");

				mSamochodRight.setmPredkosc(1);

			}

		}
	}

	public void SprawdzFotoradarRight() {
		if ((Plansza[5][INC + 1].getBlokada())
				&& (mSamochodRight.getmPredkosc() >= 2)) {
			textAreaFotoradar.append("Kierowca 2: otrzyma³ mandat: 200 PLN\n");

			mSamochodRight.setmPredkosc(1);
		} else if ((Plansza[5][INC + 2].getBlokada())
				&& (mSamochodRight.getmPredkosc() >= 2)) {

			textAreaFotoradar.append("Kierowca 2: otrzyma³ mandat: 200 PLN\n");
			;
			mSamochodRight.setmPredkosc(1);
		}
	};

	public void SprawdzFotoradarLeft() {
		if (DEC > 2) {
			if ((Plansza[0][DEC - 1].getBlokada())
					&& (mSamochodLeft.getmPredkosc() >= 2)) {
				textAreaFotoradar
						.append("Kierowca 1: otrzyma³ mandat: 200 PLN\n");

				mSamochodLeft.setmPredkosc(1);
			} else if ((Plansza[0][DEC - 2].getBlokada())
					&& (mSamochodLeft.getmPredkosc() >= 2)) {

				textAreaFotoradar
						.append("Kierowca 1: otrzyma³ mandat: 200 PLN\n");
				;
				mSamochodLeft.setmPredkosc(1);
			}
		}
	};

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
						textAreaKaretka
								.append("Kierowca 2: TAK,Karetka zosta³a wezwana\n");
					} else {

						textAreaKaretka
								.append("Kierowca 2:  chcia³ wezwaæ,ale\nkaretka ju¿ jest\n");

					}

				} else {
					if (!mKaretkaRight.getByla()) {
						textAreaKaretka
								.append("Kierowca 2: NIE,Karetka niezosta³a wezwana\n");
					}
				}

			}

			else if (mSamochodRight.SprawdzBlokade(Plansza[mSamochodRight
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
						textAreaKaretka
								.append("Kierowca 2: TAK,Karetka zosta³a wezwana\n");
					} else {

						textAreaKaretka
								.append("Kierowca 2:  chcia³ wezwaæ,ale\nkaretka ju¿ jest\n");

					}

				} else {
					if (!mKaretkaRight.getByla()) {
						textAreaKaretka
								.append("Kierowca 2: NIE,Karetka niezosta³a wezwana\n");
					}
				}
			}
		}

		if (DEC > 2) {
			if (!ZATRZYMANIE_LEFT) {
				if (mSamochodLeft.SprawdzBlokade(Plansza[mSamochodLeft
						.getmPoleX()][DEC - 1].getBlokada())) {

					int WezwijLeft = random.nextInt(10);
					if (WezwijLeft > 7) {
						if (!mKaretkaLeft.getByla()) {
							mKaretkaLeft
									.setmPoleX(mSamochodLeft.getmPoleX() - 1);
							mKaretkaLeft.setmPoleY(DEC - 2);
							Plansza[mKaretkaLeft.getmPoleX()][mKaretkaLeft
									.getmPoleY()]
									.ChangeBackgroundImage("src/img/karetka.png");
							mKaretkaLeft.setByla(true);
							textAreaKaretka
									.append("Kierowca 1: TAK,Karetka zosta³a wezwana\n");
						} else {
							textAreaKaretka
									.append("Kierowca 1: chcia³ wezwaæ,ale\nkaretka ju¿ jest\n");
						}
					} else {
						if (!mKaretkaLeft.getByla()) {
							textAreaKaretka
									.append("Kierowca 1: NIE,Karetka niezosta³a wezwana\n");
						}
					}
				} else if (mSamochodLeft.SprawdzBlokade(Plansza[mSamochodLeft
						.getmPoleX()][DEC - 2].getBlokada())) {
					int WezwijLeft = random.nextInt(10);
					if (WezwijLeft > 7) {
						if (!mKaretkaLeft.getByla()) {
							mKaretkaLeft
									.setmPoleX(mSamochodLeft.getmPoleX() - 1);
							mKaretkaLeft.setmPoleY(DEC - 3);
							Plansza[mKaretkaLeft.getmPoleX()][mKaretkaLeft
									.getmPoleY()]
									.ChangeBackgroundImage("src/img/karetka.png");
							mKaretkaLeft.setByla(true);
							textAreaKaretka
									.append("Kierowca 1: TAK,Karetka zosta³a wezwana\n");
						} else {
							textAreaKaretka
									.append("Kierowca 1: chcia³ wezwaæ,ale\nkaretka ju¿ jest\n");
						}
					} else {
						if (!mKaretkaLeft.getByla()) {
							textAreaKaretka
									.append("Kierowca 1: NIE,Karetka niezosta³a wezwana\n");
						}
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
				mplanszaPanel.add(Plansza[0][i], c);
			}
		}

		for (int i = 1; i <= 2; i++) {
			for (int j = 0; j < PLANSZA_LENGTH; j++) {
				Plansza[i][j] = new JPanelGraphic("src/img/droga.png");
				if (j <= PLANSZA_LENGTH - 5) {
					c.gridx = j;
					c.gridy = i;
					mplanszaPanel.add(Plansza[i][j], c);
				}
			}

		}

		for (int i = 3; i <= 4; i++) {
			for (int j = 0; j < PLANSZA_LENGTH; j++) {
				Plansza[i][j] = new JPanelGraphic("src/img/droga.png");
				if (j <= PLANSZA_LENGTH - 5) {
					c.gridx = j;
					c.gridy = i;
					mplanszaPanel.add(Plansza[i][j], c);
				}
			}

		}

		for (int i = 0; i < PLANSZA_LENGTH; i++) {
			Plansza[5][i] = new JPanelGraphic("src/img/pobocze.png");
			if (i <= PLANSZA_LENGTH - 5) {
				c.gridx = i;
				c.gridy = 5;
				mplanszaPanel.add(Plansza[5][i], c);
			}
		}

	}

	@Override
	public void run() {

		while (true) {

			SprawdzPoleBlokada();
			if (!FOTORADAR_LEFT) {
				SprawszPolicjaLeft();
			} else {
				SprawdzFotoradarLeft();
			}

			if (!FOTORADAR_RIGHT) {
				SprawdzPolicjaRight();
			} else {
				SprawdzFotoradarRight();
			}
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
			}

			if (!ZATRZYMANIE_LEFT) {

				DEC = DEC - mSamochodLeft.getmPredkosc();
			}

			mplanszaPanel.repaint();

			if (DEC < 0) {
				DEC = 21;
				mSamochodLeft.setmPoleX(1);
				mSamochodLeft.setmPredkosc();
			}

			if (INC >= 23) {

				TIME--;
				if (TIME == 0) {
					CzyscPlansze();

					Random losuj = new Random();
					int left = losuj.nextInt(2) + 1;
					int right = losuj.nextInt(3) + 1;

					// losujBlokadeRight();

					switch (left) {
					case 1:
						losujBlokadeLeft();
						break;
					case 2:
						losujPolicjeLeft();
						break;
					}

					switch (right) {
					case 1:
						losujBlokadeRight();
						break;
					case 2:
						losujPolicjeRight();
						break;
					case 3:
						losujFotoradarRight();
						break;
					}

					// losujPolicjeRight();
					// losujPolicjeLeft();
					// losujBlokadeLeft();
					textAreaKaretka.setText("");
					textAreaPolicja.setText("");
					textAreaFotoradar.setText("");
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