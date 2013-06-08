package pda.datas.dames;

import pda.datas.dames.exception.InvalidPlateauSizeException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Plateau p = new Plateau(10,20);
			p.placerPions();
			System.out.println("Plateau : \n"+p);
		} catch (InvalidPlateauSizeException e) {
			e.printStackTrace();
		}
	}

}
