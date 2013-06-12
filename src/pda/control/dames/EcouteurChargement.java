package pda.control.dames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import pda.datas.dames.Partie;
import pda.view.dames.EcranChargement;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Ecouteur à l'écoute des boutons de l'écran qui sert à charger une partie enregistrée dans la classe jeu.<br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcouteurChargement implements ActionListener {
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Ecran de chargement
	 */
	private EcranChargement ecranChargement;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur des écouteurs des boutons de l'écran de chargement
	 * @param ecranChargementP L'écran de chargement
	 */
	public EcouteurChargement(EcranChargement ecranChargementP){
		this.ecranChargement = ecranChargementP;
	}
	//====================================================================================================

	
	//===========================================ECOUTE BOUTONS===========================================
	/**
	 * Méthode qui gère les clic sur les différents bouton de l'écran de chargement
	 */
	public void actionPerformed(ActionEvent clic) {
		//Si on clic sur supprimer
		if (clic.getSource()==this.ecranChargement.getSupprimer()){		
			if (this.ecranChargement.getListe().getSelectedIndex() != -1){
				Date date = (Date) this.ecranChargement.getControleur().getJeu().date()[this.ecranChargement.getListe().getSelectedIndex()];
				this.ecranChargement.getControleur().getJeu().supprimer(date);
				this.ecranChargement.actualiserInterface();
			}
		}
		//Si on clic sur charger
		if (clic.getSource()==this.ecranChargement.getCharger()){
			if (this.ecranChargement.getListe().getSelectedIndex() != -1){		
				Partie partie = this.ecranChargement.getControleur().getJeu().obtenir((Date) this.ecranChargement.getControleur().getJeu().date()[this.ecranChargement.getListe().getSelectedIndex()]);
				this.ecranChargement.getControleur().lancerPartie(partie);
			}
		}
		//Si on clic sur annuler
		if (clic.getSource()==this.ecranChargement.getAnnuler()){
			this.ecranChargement.getControleur().changerMenu(this.ecranChargement.getControleur().ECRAN_PRINCIPAL);
		}
	}
	//====================================================================================================
}