package pda.view.dames;

import javax.swing.JPanel;

import pda.control.dames.DamesCtrl;
import pda.datas.dames.Partie;
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
	
	/**
	 * Ecran de jeu
	 */
	private EcranJeu ecranJeu;
	
	/**
	 * Ecran parametres partie
	 */
	private EcranParametre ecranParametres;
	
	/**
	 * Ecran menu principal
	 */
	private JPanel ecranMenu;
	
	/**
	 * Ecran des scores
	 */
	private JPanel ecranScores;
	
	/**
	 * Ecran des sauvegardes
	 */
	private JPanel ecranSauvegardes;
	
	/**
	 * Le controleur principal du jeu
	 */
	private DamesCtrl controleur;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	public DameView(){
		
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
	
	
	//===========================================CHANGEMENT IHM===========================================
	/**
	 * Cette méthode va créer les différents écrans de l'application.<br>
	 * Cela ne créera pas l'écran de jeu car celui-ci ne peut être créer qu'à partir d'une partie.
	 */
	public void creerEcrans(){
		
	}
	
	/**
	 * Cette méthode permet de changer le menu affiché
	 * @param indexMenu l'index du menu à afficher (utiliser les variables de classes)
	 */
	public void changerMenu(int indexMenu){
		//Change menu
		switch(indexMenu){
		case DamesCtrl.ECRAN_PRINCIPAL:
			this.panelPrincipal = this.ecranMenu;
			break;
		case DamesCtrl.ECRAN_SCORES:
			this.panelPrincipal = this.ecranScores;
			break;
		case DamesCtrl.ECRAN_PARAMETRES:
			this.panelPrincipal = this.ecranParametres;
			break;
		case DamesCtrl.ECRAN_SAUVEGARDES:
			this.panelPrincipal = this.ecranSauvegardes;
			break;
		case DamesCtrl.ECRAN_JEU:
			this.panelPrincipal = this.ecranJeu;
			break;
		default:
			System.err.println("Menu choisi est inexistant");
			break;
		}
		//Actualise
		this.panelPrincipal.repaint();
	}
	
	/**
	 * Cette méthode va permettre de créer et d'afficher l'écran de jeu dans le panel principal.
	 * @param partie la partie que l'on veut jouer
	 */
	public void lancerPartie(Partie partie){
		if(partie!=null){
			this.ecranJeu = new EcranJeu(this.controleur, partie);
			this.changerMenu(DamesCtrl.ECRAN_JEU);
		}
		
	}
	//====================================================================================================

	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	//====================================================================================================
	
}