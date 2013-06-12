package pda.view.dames;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pda.control.dames.DamesCtrl;
import pda.control.dames.EcouteurMenu;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcranPrincipal extends JPanel{
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Bouton pour lancer une nouvelle partie
	 */
	private BoutonJeuDame nouvellePartie;
	
	/**
	 * Bouton pour charger une partie
	 */
	private BoutonJeuDame chargerPartie;
	
	/**
	 * Bouton pour quitter une partie
	 */
	private BoutonJeuDame quitter;
	
	/**
	 * Le controleur de l'application de dames
	 */
	private DamesCtrl controleur;
	
	/**
	 * Image de fond
	 */
	private Image fond;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Construit le menu de l'application
	 * @param controleurP le controleur de l'application
	 */
	public EcranPrincipal(DamesCtrl controleurP){
		super(null);
		this.controleur = controleurP;
		try {
			this.fond = ImageIO.read(new File("./data/img/dames/fond_menu.png"));
		} catch (IOException e) {
			System.err.println("Erreur de chargement du fond pour le menu principal : "+e.getMessage());
		}
		this.creerInterface();
		this.ajouterEcouteurs();
	}
	//====================================================================================================


	//============================================ACCESSEUR(S)============================================
	/**
	 * Obtenir le bouton de création de partie
	 * @return le bouton nouvelle partie
	 */
	public BoutonJeuDame getNouvellePartie() {
		return this.nouvellePartie;
	}
	
	/**
	 * Obtenir le bouton de chargement de partie
	 * @return le bouton charger partie
	 */
	public BoutonJeuDame getChargerPartie() {
		return this.chargerPartie;
	}
	
	/**
	 * Obtenir le bouton pour quitter le jeu
	 * @return le bouton quitter
	 */
	public BoutonJeuDame getQuitter() {
		return this.quitter;
	}
	
	/**
	 * Obtenir le controleur de l'application
	 * @return le controleur
	 */
	public DamesCtrl getControleur() {
		return this.controleur;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Méthode qui permet de créer l'interface
	 */
	public void creerInterface(){
		//Panel de boutons, centré dans le panel
		JPanel boutons = new JPanel(new GridLayout(3,1,5,10));
		boutons.setOpaque(false);
		
		//Créations boutons
		this.nouvellePartie = new BoutonJeuDame("Nouvelle partie");
		this.chargerPartie = new BoutonJeuDame("Charger partie");
		this.quitter = new BoutonJeuDame("Quitter");
		
		//Ajout des boutons
		boutons.add(this.nouvellePartie);
		boutons.add(this.chargerPartie);
		boutons.add(this.quitter);
		
		//Placement boutons
		boutons.setBounds(60,150,200,150);
		
		//Ajout total
		this.add(boutons);
	}
	
	/**
	 * Dessine le fond du panel
	 */
	public void paintComponent(Graphics g){
		g.drawImage(this.fond, 0, 0, this.getWidth(), this.getHeight(),null);
	}
	
	/**
	 * Méthode qui permet d'ajouter les écouteurs aux boutons
	 */
	public void ajouterEcouteurs(){
		EcouteurMenu ecouteMenu = new EcouteurMenu(this);
		this.nouvellePartie.addActionListener(ecouteMenu);
		this.chargerPartie.addActionListener(ecouteMenu);
		this.quitter.addActionListener(ecouteMenu);
	}
	//====================================================================================================
	
}