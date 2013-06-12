package pda.control.dames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pda.view.dames.EcranPrincipal;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcouteurMenu implements ActionListener{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Le menu dont on est à l'écoute
	 */
	private EcranPrincipal menu;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Créer l'écouteur du menu principal
	 * @param menuP l'écran de menu
	 */
	public EcouteurMenu(EcranPrincipal menuP){
		this.menu = menuP;
	}
	//====================================================================================================

	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Effectue une action en fonction du clic sur un bouton
	 * @param eP l'évenement
	 */
	public void actionPerformed(ActionEvent eP) {
		//Clic sur nouvelle partie
		if(eP.getSource()==this.menu.getNouvellePartie()){
			this.menu.getControleur().changerMenu(DamesCtrl.ECRAN_PARAMETRES);
		}
		//Clic sur charger
		if(eP.getSource()==this.menu.getChargerPartie()){
			this.menu.getControleur().changerMenu(DamesCtrl.ECRAN_SAUVEGARDES);
		}
		//Clic sur quitter
		if(eP.getSource()==this.menu.getQuitter()){
			if(this.menu.getControleur().close()){
				System.exit(0);
			}
		}
	}
	//====================================================================================================
	
}