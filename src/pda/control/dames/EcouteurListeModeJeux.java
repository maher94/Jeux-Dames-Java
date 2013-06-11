package pda.control.dames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pda.view.dames.EcranParametre;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Ecouteur de la liste des modes de jeux de l'écran paramètres.<br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcouteurListeModeJeux implements ActionListener{
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Ecrans paramètres
	 */
	private EcranParametre ecranParametres;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur de l'écouteur du changement sur la liste
	 * @param ecranParametresP l'écran des paramètres
	 */
	public EcouteurListeModeJeux(EcranParametre ecranParametresP){
		this.ecranParametres = ecranParametresP;
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Méthode appelée lors du clic sur la liste
	 */
	public void actionPerformed(ActionEvent clic) {
		this.ecranParametres.actualiserAffichage();
	}
	//====================================================================================================
	
}