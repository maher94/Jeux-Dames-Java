package pda.control.dames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pda.datas.dames.Pion;
import pda.view.dames.EcranParametre;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcouteurCouleurJoueur implements ActionListener {
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Ecrans parametre
	 */
	private EcranParametre ecranParametre;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	
	/**
	 * Constructeur de l'écouteur du changement de couleur du joueur 1
	 * @param ecranParametresP l'écran des paramètres
	 */
	public EcouteurCouleurJoueur(EcranParametre ecranParametresP){
		this.ecranParametre = ecranParametresP;
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	/**
	 * Méthode qui change le contenu du JLabel de la couleur du Joueur 2 en fonction
	 * de la couleur du Joueur 1
	 */
	public void actionPerformed(ActionEvent clic) {
		
		if (clic.getSource()==this.ecranParametre.getCouleurBlanc()){
			this.ecranParametre.getCouleurJ2().setText(Pion.getCouleurNoir());
		}
		if (clic.getSource()==this.ecranParametre.getCouleurNoir()){
			this.ecranParametre.getCouleurJ2().setText(Pion.getCouleurBlanc());
		}
	
	}
	//====================================================================================================
	
}