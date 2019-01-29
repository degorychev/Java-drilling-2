package lab3;

import java.util.Calendar;
import java.awt.*;

public class automobil {
	public String zavod;
	public String model;
	public Calendar Vipusk;
	public int power;
	public Color colorauto;
	public String Nomer;
	
	public automobil(String ZAVOD, String MODEL, Calendar VIPUSK, int POWER, Color COLORAUTO, String NOMER) {
		zavod = ZAVOD;
		model = MODEL;
		Vipusk=VIPUSK; 
		power=POWER;
		colorauto = COLORAUTO;
		Nomer = NOMER;
	}
}
