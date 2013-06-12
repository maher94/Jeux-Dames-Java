package pda.view.dames;

import java.awt.CardLayout;

import javax.swing.JPanel;

import pda.control.dames.DamesCtrl;
import pda.datas.dames.Partie;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Vue principale de l'application du jeu de dame.<br>
 * Le panel principal de cette application est géré par une card layout qui permettra de basculer d'un écran à l'autre facilement.
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
	private EcranPrincipal ecranMenu;
		
	/**
	 * Ecran des sauvegardes
	 */
	private EcranChargement ecranChargement;
	
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
	 * Constructeur de la vue principale de l'application de jeu de dame.<br>
	 * Ce constructeur va s'occuper d'initialiser les différents écrans de l'application (sauf celui de jeu qui sera recréer à chaque nouvelle partie)
	 * @param controleurP le controleur principal de l'application
	 * @throws IllegalArgumentException si le constructeur est null
	 */
	public DameView(DamesCtrl controleurP)throws IllegalArgumentException{
		if(controleurP==null)throw new IllegalArgumentException("Le controleur doit exister");
		this.controleur = controleurP;
		//Création des écrans
		this.creerEcrans();
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
		this.ecranChargement = new EcranChargement(this.controleur);
		this.ecranMenu = new EcranPrincipal(this.controleur);	
		
		//Ajout des écrans
		this.panelPrincipal.add(this.ecranParametres, DamesCtrl.ECRAN_PARAMETRES);
		this.panelPrincipal.add(this.ecranMenu,DamesCtrl.ECRAN_PRINCIPAL);
		this.panelPrincipal.add(this.ecranChargement,DamesCtrl.ECRAN_CHARGEMENT);
	}
	
	/**
	 * Cette méthode permet de changer le menu affiché
	 * @param menu l'index du menu à afficher (utiliser les variables de classes)
	 */
	public void changerMenu(String menu){
		//Si on passe à l'écran de chargement, on actualise
		if(menu.equals(DamesCtrl.ECRAN_CHARGEMENT)){
			this.ecranChargement.actualiserInterface();
		}
		//Change menu	
		this.changeurMenu.show(this.panelPrincipal, menu);
		
		//Actualise
		this.panelPrincipal.repaint();
		this.panelPrincipal.revalidate();
	}
	
	/**
	 * Cette méthode va permettre de créer et d'afficher l'écran de jeu dans le panel principal.
	 * @param partie la partie que l'on veut jouer (doit exister)
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
}