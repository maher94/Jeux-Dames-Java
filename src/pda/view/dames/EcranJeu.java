package pda.view.dames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pda.control.dames.ControleurJeu;
import pda.control.dames.DamesCtrl;
import pda.datas.dames.Partie;
import pda.datas.dames.Pion;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
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
	 * Affichage du nombre de pions blancs restants
	 */
	private JLabel pionBlancRestants;
	
	/**
	 * Affichage du nombre de pions noir restants 
	 */
	private JLabel pionNoirRestants;
	
	/**
	 * Le controleur principal de l'application de jeu de dame
	 */
	private DamesCtrl controleur;
	
	/**
	 * Affichage de l'état de l'IA (réfléchi ou en attente )
	 */
	private JLabel etatIA;
	
	/**
	 * Police qui affiche les informations
	 */
	private static final Font POLICE_LABEL = new Font("Tahoma",Font.PLAIN,11);
	
	/**
	 * Police de description des informations
	 */
	private static final Font POLICE_DESC = new Font("Tahoma",Font.BOLD,11);
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
		//Lancement partie
		this.partieJouee.lancerPartie();
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
		
		//Description des informations
		JLabel dCouleur = new JLabel("Couleur");
		JLabel dNombreCoups = new JLabel("Tours");
		JLabel dNomJoueur = new JLabel("Joueur");
		JLabel dPionBlancsRestants = new JLabel("Blancs");
		JLabel dPionNoirsRestants = new JLabel("Noirs");
		JLabel dEtatIA = new JLabel("IA");
		
		//Polices
		dCouleur.setFont(EcranJeu.POLICE_DESC);
		dNombreCoups.setFont(EcranJeu.POLICE_DESC);
		dNomJoueur.setFont(EcranJeu.POLICE_DESC);
		dPionBlancsRestants.setFont(EcranJeu.POLICE_DESC);
		dPionNoirsRestants.setFont(EcranJeu.POLICE_DESC);
		dEtatIA.setFont(EcranJeu.POLICE_DESC);
		
		//Informations
		this.couleurEnCours = new JLabel();
		this.nombreCoups = new JLabel();
		this.nomJoueurEnCours = new JLabel();
		this.pionBlancRestants = new JLabel();
		this.pionNoirRestants = new JLabel();
		this.etatIA =new JLabel("Aucune");
		
		//Polices
		this.couleurEnCours.setFont(EcranJeu.POLICE_LABEL);
		this.nombreCoups.setFont(EcranJeu.POLICE_LABEL);
		this.nomJoueurEnCours.setFont(EcranJeu.POLICE_LABEL);
		this.pionBlancRestants.setFont(EcranJeu.POLICE_LABEL);
		this.pionNoirRestants.setFont(EcranJeu.POLICE_LABEL);
		this.etatIA.setFont(EcranJeu.POLICE_LABEL);
		
		
		//Boutons
		this.menu = new JButton("Menu");
		this.sauvegarder = new JButton("Sauvegarder");
		
		//Organisation de la fenetre
		JPanel droite = new JPanel(new GridLayout(1,2));
		JPanel gauche = new JPanel(new GridLayout(3,4));
		gauche.setBorder(BorderFactory.createRaisedBevelBorder());
		
		//Gauche
		gauche.add(dNombreCoups);
		gauche.add(this.nombreCoups);
		gauche.add(dNomJoueur);
		gauche.add(this.nomJoueurEnCours);
		gauche.add(dCouleur);
		gauche.add(this.couleurEnCours);
		gauche.add(dEtatIA);
		gauche.add(this.etatIA);
		gauche.add(dPionBlancsRestants);
		gauche.add(this.pionBlancRestants);
		gauche.add(dPionNoirsRestants);
		gauche.add(this.pionNoirRestants);
		
		//Droite
		droite.add(this.menu);
		droite.add(this.sauvegarder);
		//droite.setPreferredSize(new Dimension(105,40));
		
		//Ajout haut
		this.affichageInfos.add(gauche,BorderLayout.CENTER);
		this.affichageInfos.add(droite,BorderLayout.NORTH);
		
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
		//Tente de lancer la partie
		control.lancerPartie();
	}
	
	/**
	 * Cette méthode permet d'actualiser les informations sur la partie en cours.
	 */
	public void actualiserInformationsPartie(){
		this.couleurEnCours.setText(Pion.getNomCouleur(this.partieJouee.getCouleurJouee()));
		this.nombreCoups.setText(""+this.partieJouee.getNbTours());
		this.nomJoueurEnCours.setText(this.partieJouee.getJoueurActuel().getNom());
		this.pionBlancRestants.setText(""+this.partieJouee.getPionRestant(Pion.PION_BLANC));
		this.pionNoirRestants.setText(""+this.partieJouee.getPionRestant(Pion.PION_NOIR));
	}
	//====================================================================================================	
}