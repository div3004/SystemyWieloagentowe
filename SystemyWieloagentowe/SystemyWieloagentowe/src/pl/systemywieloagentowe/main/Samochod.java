package pl.systemywieloagentowe.main;

import java.util.Random;

public class Samochod {

	int mPredkosc;
	int mPoleX;
	int mPoleY;

	public Samochod() {
		setmPredkosc();
	}
	
	
	public boolean SprawdzBlokade(boolean Pole)
	{
		if (Pole)
		{
	
		mPoleX=mPoleX-1;
		return true;
		} else
		{
		
		return false;
		}
	}
	
	

	public int getmPoleX() {
		return mPoleX;
	}

	public int getmPoleY() {
		return mPoleY;
	}

	public int getmPredkosc() {
		return mPredkosc;
	}

	public void setmPoleX(int mPoleX) {
		this.mPoleX = mPoleX;
	}

	public void setmPoleY(int mPoleY) {
		this.mPoleY = mPoleY;
	}

	public void setmPredkosc() {
		Random random = new Random();
		mPredkosc = random.nextInt(2) + 1;
	}

}
