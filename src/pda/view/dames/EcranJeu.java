package pda.view.dames;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pda.control.dames.ControleurJeu;
import pda.control.dames.DamesCtrl;
import pda.datas.dames.Partie;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Ceci est l'écran de jeu. C'est l'écran principal de l'application Jeu de dames.<br>
 * Dans cet écran, on affichera les informations sur la partie en cours ainsi que le panel du plateau qui affichera le plateau de jeu.<br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcranJeu extends JPanel{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Partie jouée
	 */
	private Partie partieJouee;
	
	/**
	 * Le panel qui affiche le plateau
	 */
	private PanelPlateau affichagePlateau;
	
	/**
	 * La panel qui affiche les informations sur la parties
	 */
	private JPanel affichageInfos;
	
	/**
	 * Le bouton qui permet de sauvegarder la partie
	 */
	private JButton sauvegarder;
	
	/**
	 * Le bouton qui permet de retourner au menu
	 */
	private JButton menu;
	
	/**
	 * Affiche le nom du joueur qui joue
	 */
	private JLabel nomJoueurEnCours;
	
	/**
	 * Affiche la couleur qui doit être jouée
	 */
	private JLabel couleurEnCours;
	
	/**
	 * Affiche le nombre de coups joués dans la partie
	 */
	private JLabel nombreCoups;
	
	/**
	 * Affichage le nombre de pions restant du joueur actuel
	 */
	private JLabel pionRestants;
	
	/**
	 * Le controleur principal de l'application de jeu de dame
	 */
	private DamesCtrl controleur;
	
	/**
	 * Affichage de l'état de l'IA (réfléchi ou en attente )
	 */
	private JLabel etatIA;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur d'une écran de jeu
	 * @param controleurP le controleur de l'application
	 * @param partieJoueeP la partie à jouer
	 * @throws IllegalArgumentException si la partie ou le controleur est null
	 */
	public EcranJeu(DamesCtrl controleurP,Partie partieJoueeP)throws IllegalArgumentException{
		super(new BorderLayout());
		//Récupération du controleur
		if(controleurP==null)throw new IllegalArgumentException("Il faut le controleur principal de l'application");
		this.controleur=controleurP;
		//Récupération de la partie
		if(partieJoueeP==null)throw new IllegalArgumentException("Il faut une partie à jouer pour créer le panel de jeu");
		this.partieJouee = partieJoueeP;
		//IHM
		this.creerIHM();
		this.ajoutEcouteurs();
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Permet d'obtenir la panel qui affiche le plateau.
	 * @return le PanelJeux qui affiche le plateau
	 */
	public PanelPlateau getAffichagePlateau(){
		return this.affichagePlateau;
	}
	
	/**
	 * Permet d'obtenir le controleur principal de l'application
	 * @return le controleur de l'application
	 */
	public DamesCtrl getControleur(){
		return this.controleur;
	}
	
	/**
	 * Obtenir la partie jouée sur l'écran de jeu
	 * @return la partie jouée
	 */
	public Partie getPartieJouee(){
		return this.partieJouee;
	}
	
	/**
	 * Obtenir le bouton qui permet de sauvegarder la partie
	 * @return le bouton de sauvegarde
	 */
	public JButton getSauvegarder() {
		return this.sauvegarder;
	}

	/**
	 * Obtenir le bouton qui fait revenir au menu
	 * @return le bouton qui fait revenir au menu
	 */
	public JButton getMenu() {
		return this.menu;
	}

	/**
	 * Obtenir le Jlabel qui affiche l'état de l'IA
	 * @return le label qui affiche l'état de l'IA
	 */
	public JLabel getEtatIA() {
		return this.etatIA;
	}
	//====================================================================================================
	
	
	//==========================================GRAPHIQUE FENETRE=========================================
	/**
	 * Cette méthode va créer l'interface de l'écran de jeu.
	 */
	public void creerIHM(){
		/*
		 * PANEL D'INFORMATION
		 */
		this.affichageInfos = new JPanel(new BorderLayout());
		this.add(this.affichageInfos,BorderLayout.NORTH);
		
		//Informations
		this.couleurEnCours = new JLabel();
		this.nombreCoups = new JLabel();
		this.nomJoueurEnCours = new JLabel();
		this.pionRestants = new JLabel();
		this.etatIA =new JLabel();
		
		//Boutons
		this.menu = new JButton("Menu");
		this.sauvegarder = new JButton("Sauvegarder");
		
		//Organisation de la fenetre
		JPanel droite = new JPanel(new GridLayout(2,1));
		JPanel gauche = new JPanel(new GridLayout(2,3));
		
		//Gauche
		gauche.add(this.nombreCoups);
		gauche.add(this.nomJoueurEnCours);
		gauche.add(this.couleurEnCours);
		gauche.add(this.etatIA);
		gauche.add(this.pionRestants);
		
		//Droite
		droite.add(this.menu);
		droite.add(this.sauvegarder);
		
		//Ajout haut
		this.affichageInfos.add(gauche,BorderLayout.CENTER);
		this.affichageInfos.add(droite,BorderLayout.EAST);
		
		/*
		 * PANEL DE PLATEAU
		 */
		this.affichagePlateau = new PanelPlateau(this);
		this.add(this.affichagePlateau, BorderLayout.CENTER);
		
		//Actualise
		this.repaint();
	}
	
	/**
	 * Cette méthode va associer les controleurs nécessaires au jeu à l'écran de jeu.
	 */
	public void ajoutEcouteurs(){
		ControleurJeu control = new ControleurJeu(this);
		//Ecoute du plateau de jeu
		this.affichagePlateau.addMouseListener(control);
		//Boutons
		this.menu.addActionListener(control);
		this.sauvegarder.addActionListener(control);
	}
	//====================================================================================================	
}