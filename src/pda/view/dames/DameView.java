package pda.view.dames;

import java.awt.CardLayout;

import javax.swing.JPanel;

import pda.control.dames.DamesCtrl;
import pda.datas.dames.Partie;


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
	 * Le layout qui permet de changer d'écran
	 */
	private CardLayout changeurMenu;
	
	/**
	 * Le controleur principal du jeu
	 */
	private DamesCtrl controleur;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * 
	 * @param controleurP
	 * @throws IllegalArgumentException
	 */
	public DameView(DamesCtrl controleurP)throws IllegalArgumentException{
		if(controleurP==null)throw new IllegalArgumentException("Le controleur doit exister");
		this.controleur = controleurP;
		//Création des écrans
		this.creerEcrans();
		//Menu par défault
		this.changerMenu(DamesCtrl.ECRAN_PARAMETRES);
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
		//Création panel
		this.changeurMenu = new CardLayout();
		this.panelPrincipal = new JPanel(changeurMenu);
		
		//Créations écrans
		this.ecranParametres = new EcranParametre(this.controleur);
		
		//Ajout des écrans
		this.panelPrincipal.add(this.ecranParametres, DamesCtrl.ECRAN_PARAMETRES);
	}
	
	/**
	 * Cette méthode permet de changer le menu affiché
	 * @param menu l'index du menu à afficher (utiliser les variables de classes)
	 */
	public void changerMenu(String menu){
		//Change menu
		this.changeurMenu.show(this.panelPrincipal, menu);
		
		//Actualise
		this.panelPrincipal.repaint();
		this.panelPrincipal.revalidate();
	}
	
	/**
	 * Cette méthode va permettre de créer et d'afficher l'écran de jeu dans le panel principal.
	 * @param partie la partie que l'on veut jouer
	 */
	public void lancerPartie(Partie partie){
		if(partie!=null){
			//Création et ajout
			if(this.ecranJeu!=null)this.panelPrincipal.remove(this.ecranJeu);
			this.ecranJeu = new EcranJeu(this.controleur, partie);
			this.panelPrincipal.add(this.ecranJeu, DamesCtrl.ECRAN_JEU);

			this.changerMenu(DamesCtrl.ECRAN_JEU);
		}
		
	}
	//====================================================================================================

	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	//====================================================================================================
	
}