package pda.control.dames;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pda.control.IApplication;
import pda.control.PdaCtrl;
import pda.datas.dames.Jeu;
import pda.datas.dames.Partie;
import pda.view.dames.DameView;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Le controleur principal de l'application de jeu de dames.<br>
 * C'est à partir de ce controleur qu'on pourra décider de jouer une partie ou sauvegarder une nouvelle partie.<br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class DamesCtrl implements IApplication{

	//============================================ATTRIBUT(S)============================================
	/**
	 * Le nom de l'application
	 */
	private String nom="dames";
		
	/**
	 * La vue du jeu de dames
	 */
	private DameView vue;
		
	/**
	 * Le jeu de dame actuellement chargé
	 */
	private Jeu jeuCharge;
	
	/**
	 * Menu principal
	 */
	public static final String ECRAN_PRINCIPAL="Menu principal";
		
	/**
	 * Menu des parties sauvegardées
	 */
	public static final String ECRAN_CHARGEMENT="Charger partie";
	
	/**
	 * Ecran de paramètrage d'une partie
	 */
	public static final String ECRAN_PARAMETRES="Paramètres d'une partie";
	
	/**
	 * Ecran de jeu
	 */
	public static final String ECRAN_JEU="Joueur une partie";
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur du controleur principal du jeu de dame.<br>
	 * Cela va initialiser la vue ainsi que le modèle.
	 */
	public DamesCtrl(){
		this.vue = new DameView(this);
		//Charge l'objet jeu enregistré
		this.jeuCharge = Jeu.charger();
		//Si le chargement a échoué
		if(this.jeuCharge==null)this.jeuCharge = new Jeu();
		//Menu par défault
		this.changerMenu(DamesCtrl.ECRAN_PRINCIPAL);
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Obtenir le jeu actuellement chargé
	 * @return le jeu chargé
	 */
	public Jeu getJeu(){
		return this.jeuCharge;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	/**
	 * Permet de sauvegarder une partie dans le jeu.
	 * @param p La partie à sauvegarder
	 */
	public void sauvegarderPartie(Partie p){
		this.jeuCharge.ajouter(p);
	}
	//====================================================================================================
		
	
	//===========================================CHANGEMENT IHM===========================================
	/**
	 * Cette méthode permet de changer le menu affiché dans la vue principale.
	 * @param menu menu à afficher (utiliser les variables de classes)
	 */
	public void changerMenu(String menu){
		//Change la vue
		this.vue.changerMenu(menu);
	}
	
	/**
	 * Cette méthode va permet de lancer une partie dans l'application
	 * @param partie la partie à lancer
	 */
	public void lancerPartie(Partie partie){
		//Lance la partie
		this.vue.lancerPartie(partie);
		//On recharge le jeu (pour conserver une partie différente de celle jouée)
		Jeu jeuCharge = Jeu.charger();
		if(jeuCharge!=null)this.jeuCharge=jeuCharge;
	}
	//====================================================================================================

	
	//============================================METHODES PDA============================================
	/**
	 * Démarrage du jeu de dame.
	 * Charge le jeu qui contient score, statistique ainsi que parties sauvegardées.
	 */
	public void start(PdaCtrl pda) {
		System.out.println("\n\tIUT VANNES\nPROJET JEUX DE DAMES - 2013\n");
		System.out.println("Mathieu THEBAUD\nNathan VILLIOT\n");
	}
	
	/**
	 * Obtenir le panel qui affiche le jeu de dame.
	 */
	public JPanel getAppliPanel() {
		return this.vue.getPanelPrincipal();
	}

	/**
	 * Permet de fermer le jeu de dames.
	 * On demandera une confirmation de la fermeture dans la vue.
	 */
	public boolean close() {
		//Demande confirmation
		int reponse = JOptionPane.showConfirmDialog(this.getAppliPanel(),"Voulez vous vraiment quitter l'application Jeu de dame ?","Quitter",JOptionPane.YES_NO_OPTION);
		return reponse==JOptionPane.YES_OPTION;
	}

	/**
	 * Changer le nom de l'application
	 */
	public void setAppliName(String theName) {
		this.nom = theName;
	}
	
	/**
	 * Obtenir le nom de l'application
	 */
	public String getAppliName() {
		return this.nom;
	}
	//====================================================================================================
	
}