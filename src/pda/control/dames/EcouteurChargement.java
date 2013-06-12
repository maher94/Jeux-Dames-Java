package pda.control.dames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import pda.datas.dames.Partie;
import pda.view.dames.EcranChargement;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
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

	/**
	 * Méthode qui gère les clic sur les différents bouton de l'écran de chargement
	 */
	public void actionPerformed(ActionEvent clic) {
		if (clic.getSource()==this.ecranChargement.getSupprimer()){		
			if (this.ecranChargement.getListe().getSelectedIndex() != -1){		
				System.out.println((Date) this.ecranChargement.getControleur().getJeu().date()[this.ecranChargement.getListe().getSelectedIndex()]);
				Date date = (Date) this.ecranChargement.getControleur().getJeu().date()[this.ecranChargement.getListe().getSelectedIndex()];
				this.ecranChargement.getControleur().getJeu().supprimer(date);
				this.ecranChargement.actualiserInterface();
		}
		}
		if (clic.getSource()==this.ecranChargement.getCharger()){
			Partie partie = this.ecranChargement.getControleur().getJeu().getdates().get((Date) this.ecranChargement.getControleur().getJeu().date()[this.ecranChargement.getListe().getSelectedIndex()]);
			this.ecranChargement.getControleur().lancerPartie(partie);
		}
		if (clic.getSource()==this.ecranChargement.getAnnuler()){
			this.ecranChargement.getControleur().changerMenu(this.ecranChargement.getControleur().ECRAN_PRINCIPAL);
		}
	}
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	//====================================================================================================
	
}