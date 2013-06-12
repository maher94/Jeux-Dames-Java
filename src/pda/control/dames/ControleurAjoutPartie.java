package pda.control.dames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pda.datas.dames.Partie;
import pda.datas.dames.PartieHumainvsHumain;
import pda.datas.dames.PartieHumainvsIA;
import pda.datas.dames.PartieIAvsIA;
import pda.datas.dames.Pion;
import pda.view.dames.EcranParametre;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Ce controleur s'occupe de la gestion des boutons dans la fenêtre de paramètrage d'une partie.<br>
 * Lorsqu'on appuie sur le bouton valider, si la partie créée est valide, on la lance dans l'écran approprié.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class ControleurAjoutPartie implements ActionListener{

	
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Controleur principal de l'application
	 */
	private DamesCtrl controleur;
	
	/**
	 * L'écran qui contient tous les paramètres de la partie
	 */
	private EcranParametre ecranParametres;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Construit l'écouteur de l'écran de paramètre
	 * @param ecranParametresP l'écran de paramètre écouté
	 */
	public ControleurAjoutPartie(EcranParametre ecranParametresP){
		this.ecranParametres=ecranParametresP;
		this.controleur = ecranParametres.getControleur();
	}
	//====================================================================================================
	

	//==========================================REACTIONS CLICS ==========================================
	/**
	 * Méthode appellée lors du clic sur Valider ou Annuler
	 */
	public void actionPerformed(ActionEvent eP) {
		//Si on clic sur annuler
		if(eP.getSource()==this.ecranParametres.getAnnuler()){
			this.controleur.changerMenu(DamesCtrl.ECRAN_PRINCIPAL);
		}
		//Si on clic sur valider
		if(eP.getSource()==this.ecranParametres.getValider()){
			//Récupération de tous les paramètres
			//Mode
			String choix = (String) this.ecranParametres.getComboMode().getSelectedItem();
			//Difficultee IA
			int difficulte;
			if(this.ecranParametres.getDiff1().isSelected())difficulte=1;			
			else if(this.ecranParametres.getDiff1().isSelected())difficulte=2;
			else difficulte=3;
			//Nom
			String nomJ1 = this.ecranParametres.getJoueur1().getText();
			String nomJ2 = this.ecranParametres.getJoueur2().getText();
			//Couleur
			int couleurJ1;
			if(this.ecranParametres.getCouleurBlanc().isSelected())couleurJ1=Pion.PION_BLANC;
			else couleurJ1=Pion.PION_NOIR;
			int couleurJ2=-couleurJ1;
			//Nombre pion
			int nbPions;
			int largeurPlateau;
			if(this.ecranParametres.getVariante1().isSelected()){
				nbPions=12;
				largeurPlateau=8;
			}else if(this.ecranParametres.getVariante2().isSelected()){
				nbPions=20;
				largeurPlateau=10;
			}else{
				nbPions=30;
				largeurPlateau=12;
			}
			
			//Création en fonction du mode
			Partie partieJouee=null;
			
			try{
				//Mode IA vs IA
				if(choix.equals(this.ecranParametres.getIaVSia())){
					//Couleur qui commence
					int couleur;
					if(this.ecranParametres.getCommenceJ1().isSelected())couleur=Pion.PION_BLANC;
					else couleur=Pion.PION_NOIR;
					//Création partie
					partieJouee=new PartieIAvsIA(largeurPlateau,nbPions, couleur, difficulte);
				}
				
				//Mode IA vs Humain
				if(choix.equals(this.ecranParametres.getIaVShumain())){
					//Couleur qui commence
					int couleur;
					if(this.ecranParametres.getCouleurBlanc().isSelected())couleur=Pion.PION_BLANC;
					else couleur=Pion.PION_NOIR;
					if(!this.ecranParametres.getCommenceJ1().isSelected())couleur=-couleur;
					//Création partie
					partieJouee=new PartieHumainvsIA(largeurPlateau,nbPions, couleur,nomJ1,difficulte, couleurJ1);
				}
				
				//Mode humain vs humain
				if(choix.equals(this.ecranParametres.getHumainVShumain())){
					//Couleur qui commence
					int couleur;
					if(this.ecranParametres.getCouleurBlanc().isSelected())couleur=Pion.PION_BLANC;
					else couleur=Pion.PION_NOIR;
					if(!this.ecranParametres.getCommenceJ1().isSelected())couleur=-couleur;
					//Création partie
					partieJouee=new PartieHumainvsHumain(largeurPlateau,nbPions, couleur, nomJ1, couleurJ1, nomJ2, couleurJ2);
				}
				//Lance la partie
				this.controleur.lancerPartie(partieJouee);
			}catch(Exception e){
				System.err.println("Erreur lors de la création de partie : "+e.getMessage());
				e.printStackTrace();
			}			
		}
	}
	//====================================================================================================
	
}