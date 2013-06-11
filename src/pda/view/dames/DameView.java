package pda.view.dames;

import javax.swing.JPanel;

import pda.datas.dames.Partie;
import pda.datas.dames.PartieHumainvsHumain;
import pda.datas.dames.PartieHumainvsIA;
import pda.datas.dames.PartieIAvsIA;
import pda.datas.dames.exception.InvalidPlateauSizeException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class DameView {
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Panel principal du jeux de dames (celui où tout est affiché)
	 */
	private JPanel panelPrincipal;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/*
	public DameView(){
		this.panelPrincipal = new EcranPlateau();
	}
	*/
	public DameView(){
		Partie p;
		try {
			p = new PartieHumainvsHumain(10, 20,1,"Mathieu", 1,"Michel", -1);
			this.panelPrincipal = new EcranJeu(p);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPlateauSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Obtenir le panel principal de l'application
	 * @return le panel principal du jeu
	 */
	public JPanel getPanelPrincipal(){
		return this.panelPrincipal;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	//====================================================================================================
	
}