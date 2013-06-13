package pda.view.dames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import pda.control.dames.ControleurAjoutPartie;
import pda.control.dames.DamesCtrl;
import pda.control.dames.EcouteurCouleurJoueur;
import pda.control.dames.EcouteurListeModeJeux;
import pda.control.dames.EcouteurNomJoueur;
import pda.datas.dames.Pion;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Cet écran permet à l'utilisateur de choisir tous les paramètres d'une partie.<br>
 * Les paramètres disponnibles changent selon le type de partie que l'utilisateur choisi.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */
public class EcranParametre extends JPanel {
	
	
	//============================================ATTRIBUT(S)============================================
	
	/**
	 * JPanel nord qui contiend la comboBox
	 */
	private JPanel nord;
	
	/**
	 * JPanel centre qui contient tous les paramètres
	 */
	private JPanel centre;
	
	/**
	 * JPanel sud qui contient les boutons "Valider" et "Annuler"
	 */
	private JPanel sud;
	
	/**
	 * JPanel qui contient le choix de la variante
	 */
	private JPanel variantes;
	
	/**
	 * JPanel qui contient le choix de la difficulté de l'IA
	 */
	private JPanel diffIA;
	
	/**
	 * JPanel qui contient le choix de la couleur du Joueur 1
	 */
	private JPanel choixCouleur;
	
	/**
	 * JPanel qui contient le choix du nom du Joueur 1
	 */
	private JPanel nomJoueur1;
	
	/**
	 * JTextField qui sert à saisir le nom de Joueur 1
	 */
	private JTextField joueur1;
	
	/**
	 * Affichage de :"Nom du Joueur 1 :"
	 */
	private JLabel textJoueur1;
	
	/**
	 * JPanel qui contient le choix du nom du Joueur 2
	 */
	private JPanel nomJoueur2;
	
	/**
	 * JTextField qui sert à saisir le nom de Joueur 2
	 */
	private JTextField joueur2;
	
	/**
	 * Affichage de :"Nom du Joueur 2 :"
	 */
	private JLabel textJoueur2;
	
	/**
	 * JPanel qui contient le choix du joueur qui commence
	 */
	private JPanel choixCommence;
	
	/**
	 * Bouton radio pour le choix de variante 1
	 */
	private JRadioButton variante1;
	
	/**
	 * Bouton radio pour le choix de variante 2
	 */
	private JRadioButton variante2;
	
	/**
	 * Bouton Radio pour le choix de variante 3
	 */
	private JRadioButton variante3;
	
	/**
	 * Bouton Radio pour le choix de difficulté 1
	 */
	private JRadioButton diff1;
	
	/**
	 * Bouton Radio pour le choix de difficulté 2
	 */
	private JRadioButton diff2;
	
	/**
	 * Bouton Radio pour le choix de difficulté 3
	 */
	private JRadioButton diff3;
	
	/**
	 * Bouton Radio pour le choix de la couleur blanche pour Joueur 1
	 */
	private JRadioButton couleurBlanc;
	
	/**
	 * Bouton Radio pour le choix de la couleur noir pour le Joueur 1
	 */
	private JRadioButton couleurNoir;
	
	/**
	 * Panel qui sert à afficher des boutons
	 */
	private JPanel choixGche;
	
	/**
	 * Panel qui sert à afficher des boutons
	 */
	private JPanel choixDte;
	
	/**
	 * Label qui affiche la couleur du joueur 2
	 */
	private JLabel couleurJ2;
	
	/**
	 * Bouton Radio pour choisir que le Joueur 1 commence
	 */
	private JRadioButton commenceJ1;
	
	/**
	 * Bouton Radio pour choisir que le Joueur 2 commence
	 */
	private JRadioButton commenceJ2;
	
	/**
	 * Groupe de Bouton Radio pour le choix de la variante
	 */
	private ButtonGroup groupVariante;
	
	/**
	 * Groupe de Bouton Radio pour le choix de la difficulté de l'IA
	 */
	private ButtonGroup groupDiff;
	
	/**
	 * groupe de Bouton Radio pour le choix de la couleur du joueur1
	 */
	private ButtonGroup groupCouleurJ1;
	
	/**
	 * Groupe de Bouton Radio pour le choix du Joueur qui commence
	 */
	private ButtonGroup groupChoixJ;
	
	/**
	 * Combo Box pour le choix du mode de jeu
	 */
	private JComboBox comboMode;
	
	/**
	 * Chaîne de caractère correspondant au mode de jeu IA vs IA
	 */
	private String iaVSia;
	
	/**
	 * Chaîne de caractère correspondant au mode de jeu IA vs HUMAIN
	 */
	private String iaVShumain;
	
	/**
	 * Chaîne de caractère correspondant au mode de jeu HUMAIN vs HUMAIN
	 */
	private String humainVShumain;
	
	/**
	 * Le controleur de l'application
	 */
	private DamesCtrl controleur;
	
	/**
	 * Bouton valider
	 */
	private JButton valider;
	
	/**
	 * Bouton annuler
	 */
	private JButton annuler;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur de l'écran de paramètre, qui initialise l'écran et met en place les <br>
	 * différents composants 
	 * @param controleurP le controleur de l'application
	 */
	public EcranParametre(DamesCtrl controleurP){
		this.setLayout(new BorderLayout());
		this.controleur = controleurP;
		this.creerInterface(); 
		this.ajoutEcouteurs();
		this.add(nord,BorderLayout.NORTH);
		this.add(centre,BorderLayout.CENTER);
		this.add(sud,BorderLayout.SOUTH);
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Sert à retourner le JTextField joueur1
	 * @return Retourne joueur1
	 */
	public JTextField getJoueur1() {
		return this.joueur1;
	}

	/**
	 * Sert à retourner le JTextField joueur2
	 * @return Retourne joueur2
	 */
	public JTextField getJoueur2() {
		return this.joueur2;
	}

	/**
	 * Sert à retourner le bouton radio de selection du joueur1
	 * @return Retourne bouton radio
	 */
	public JRadioButton getCommenceJ1() {
		return this.commenceJ1;
	}

	/**
	 * Sert à retourner le bouton radio de selection du joueur2
	 * @return Retourne bouton radio
	 */
	public JRadioButton getCommenceJ2() {
		return this.commenceJ2;
	}
	
	/**
	 * Sert à retourner le bouton radio de choix de la couleur blanche du Joueur 1
	 * @return Retourne le bouton radio
	 */
	public JRadioButton getCouleurBlanc(){
		return this.couleurBlanc;
	}
	
	/**
	 * Sert à retourner le bouton radio de choix de la couleur noire du Joueur 1
	 * @return Retourne le bouton radio
	 */
	public JRadioButton getCouleurNoir(){
		return this.couleurNoir;
	}
	
	/**
	 * Sert à retourner le JLabel correspondant à la couleur du Joueur 2
	 * @return Retourne le JLabel
	 */
	public JLabel getCouleurJ2(){
		return this.couleurJ2;
	}
	
	/**
	 * Permet d'obtenir le bouton valider
	 * @return le bouton valider
	 */
	public JButton getValider() {
		return this.valider;
	}
	
	/**
	 * Permet d'obtenir le bouton annuler
	 * @return le bouton annuler
	 */
	public JButton getAnnuler() {
		return this.annuler;
	}
	
	/**
	 * Obtenir le bouton qui choisi la taille 8x8
	 * @return le bouton qui choisi 8x8
	 */
	public JRadioButton getVariante1() {
		return this.variante1;
	}

	/**
	 * Obtenir le bouton qui choisi la taille 10x10
	 * @return le bouton qui choisi 10x10
	 */
	public JRadioButton getVariante2() {
		return this.variante2;
	}

	/**
	 * Obtenir le bouton qui choisi la taille 12x12
	 * @return le bouton qui choisi 12x12
	 */
	public JRadioButton getVariante3() {
		return this.variante3;
	}

	/**
	 * Obtenir le bouton qui choisi la difficulte 1
	 * @return le bouton de difficulte 1
	 */
	public JRadioButton getDiff1() {
		return this.diff1;
	}

	/**
	 * Obtenir le bouton qui choisi la difficulte 2
	 * @return le bouton de difficulte 2
	 */
	public JRadioButton getDiff2() {
		return this.diff2;
	}

	/**
	 * Obtenir le bouton qui choisi la difficulte 3
	 * @return le bouton de difficulte 3
	 */
	public JRadioButton getDiff3() {
		return this.diff3;
	}

	/**
	 * Obtenir la boite de choix du mode de partie
	 * @return la boite de choix du mode
	 */
	public JComboBox getComboMode() {
		return this.comboMode;
	}
	
	/**
	 * Obtenir le controleur principal de l'application
	 * @return le controleur
	 */
	public DamesCtrl getControleur() {
		return this.controleur;
	}
	
	/**
	 * Obtenir l'item du choix IA vs IA
	 * @return l'item du choix IA vs IA
	 */
	public String getIaVSia() {
		return this.iaVSia;
	}

	/**
	 * Obtenir l'item du choix Humain vs IA
	 * @return l'item du choix Humain vs IA
	 */
	public String getIaVShumain() {
		return this.iaVShumain;
	}

	/**
	 * Obtenir l'item du choix Humain vs Humain
	 * @return l'item du choix Humain vs Humain
	 */
	public String getHumainVShumain() {
		return this.humainVShumain;
	}
	//====================================================================================================
	
	
	//================================================IHM================================================
	/**
	 * Sert à créér et mettre en place tous les composants de l'écran de paramètrage.
	 */
	public void creerInterface(){	
		//Gère la partie sud avec les boutons "Valider" et "Annuler"
		this.sud = new JPanel();
		this.valider = new JButton("Valider");
		this.valider.setPreferredSize(new Dimension(150,20));
		this.annuler = new JButton("Annuler");
		this.annuler.setPreferredSize(new Dimension(150,20));
		this.sud.add(annuler);
		this.sud.add(valider);
		
		//Gère la partie nord, qui est le choix du mode de jeu
		this.nord = new JPanel();
		this.comboMode = new JComboBox();
		this.iaVSia = "IA vs IA";
		this.iaVShumain = "IA vs HUMAIN";
		this.humainVShumain = "HUMAIN vs HUMAIN";
		this.comboMode.addItem(this.iaVSia);
		this.comboMode.addItem(this.iaVShumain);
		this.comboMode.addItem(this.humainVShumain);
		this.nord.add(comboMode);		
		
		//-------Gère la partie CENTRE de l'écran-------
		
		this.centre = new JPanel();
		this.centre.setLayout(new GridLayout(5,1));	
		
		//Gère la partie choix de la variante
		this.variante1 = new JRadioButton("8X8");
		this.variante2 = new JRadioButton("10X10");
		this.variante3 = new JRadioButton("12X12");
		this.groupVariante = new ButtonGroup();
		this.groupVariante.add(variante1);
		this.groupVariante.add(variante2);
		this.groupVariante.add(variante3);
		variantes = new JPanel();
		this.variantes.add(variante1);
		this.variantes.add(variante2);
		this.variantes.add(variante3);
		this.variantes.setBorder(BorderFactory.createTitledBorder("Taille du plateau"));
		
		//Gère la partie choix de la difficulté de l'IA
		this.diff1 = new JRadioButton("Facile");
		this.diff2 = new JRadioButton("Moyen");
		this.diff3 = new JRadioButton("Difficile");
		this.groupDiff = new ButtonGroup();
		this.groupDiff.add(diff1);
		this.groupDiff.add(diff2);
		this.groupDiff.add(diff3);
		diffIA = new JPanel();
		this.diffIA.add(diff1);
		this.diffIA.add(diff2);
		this.diffIA.add(diff3);
		this.diffIA.setBorder(BorderFactory.createTitledBorder("Choix difficulté"));
		
		//Gère la partie choix de la couleur du Joueur 1
		this.couleurBlanc = new JRadioButton("Blanc");
		this.couleurNoir = new JRadioButton("Noir");
		this.groupCouleurJ1 = new ButtonGroup();
		this.groupCouleurJ1.add(couleurBlanc);
		this.groupCouleurJ1.add(couleurNoir);
		this.choixCouleur = new JPanel();
		this.choixCouleur.setLayout(new GridLayout(1,2));
		this.choixGche = new JPanel();
		this.choixDte = new JPanel();
		this.couleurJ2 = new JLabel(Pion.getCouleurBlanc());
		choixGche.add(this.couleurBlanc);
		choixGche.add(this.couleurNoir);
		choixDte.add(couleurJ2);
		this.choixCouleur.add(choixGche);
		this.choixCouleur.add(choixDte);
		choixGche.setBorder(BorderFactory.createTitledBorder("Choix couleur Joueur 1"));
		choixDte.setBorder(BorderFactory.createTitledBorder("Couleur Joueur 2"));
		
		//Gère la partie choix du nom du Joueur 1
		joueur1 = new JTextField(10);
		joueur1.setText("Joueur 1");
		textJoueur1 = new JLabel("Nom du Joueur 1 :");
		this.nomJoueur1 = new JPanel();
		this.nomJoueur1.add(textJoueur1);
		this.nomJoueur1.add(joueur1);
		this.nomJoueur1.setBorder(BorderFactory.createTitledBorder("Choix nom du Joueur1"));
		
		//Gère la partie choix du nom du Joueur 2
		joueur2 = new JTextField(10);
		joueur2.setText("Joueur 2");
		textJoueur2 = new JLabel("Nom du Joueur 2 :");
		this.nomJoueur2 = new JPanel();
		this.nomJoueur2.add(textJoueur2);
		this.nomJoueur2.add(joueur2);
		this.nomJoueur2.setBorder(BorderFactory.createTitledBorder("Choix nom du Joueur2"));
		
		//Gère la partie du choix du joueur qui commence*
		this.commenceJ1 = new JRadioButton("Joueur 1");
		this.commenceJ2 = new JRadioButton("Joueur 2");
		this.groupChoixJ = new ButtonGroup();
		this.groupChoixJ.add(commenceJ1);
		this.groupChoixJ.add(commenceJ2);
		this.choixCommence = new JPanel();
		this.choixCommence.add(commenceJ1);
		this.choixCommence.add(commenceJ2);
		this.choixCommence.setBorder(BorderFactory.createTitledBorder("Qui commence ?"));
		
		//Affichage par défaut
		this.actualiserAffichage();
	}	
		

	/**
	 * Sert à actualiser l'écran de paramétrage à chaque fois que le mode de jeu est modifié
	 * L'écran est vidé est reconstruit à chaque fois, en fonction du mode de jeu choisi.
	 */
	public void actualiserAffichage(){
		
		this.centre.removeAll();
		this.centre.setLayout(new GridLayout(5,1));
		
		if (comboMode.getSelectedItem()==this.iaVSia){
			this.commenceJ1.setText(Pion.getCouleurBlanc());
			this.commenceJ2.setText(Pion.getCouleurNoir());
			this.centre.add(variantes);
			this.centre.add(diffIA);
			this.centre.add(choixCommence);
		}
		if (comboMode.getSelectedItem()==this.iaVShumain){
			this.commenceJ2.setText("IA");
			this.commenceJ1.setText("Joueur");
			this.joueur1.setText("Joueur");
			this.joueur2.setText("IA");
			this.centre.add(variantes);
			this.centre.add(diffIA);
			this.centre.add(choixCouleur);
			this.centre.add(nomJoueur1);
			this.centre.add(choixCommence);
		}
		if(comboMode.getSelectedItem()==this.humainVShumain){
			this.commenceJ1.setText("Joueur 1");
			this.commenceJ2.setText("Joueur 2");
			this.joueur1.setText("Joueur 1");
			this.joueur2.setText("Joueur 2");
			this.centre.add(variantes);
			this.centre.add(choixCouleur);
			this.centre.add(nomJoueur1);
			this.centre.add(nomJoueur2);
			this.centre.add(choixCommence);
		}
		this.centre.revalidate(); 
		
	}
	
	/**
	 * Sert à prendre en compte les écouteurs sur les items choisi. 
	 */
	public void ajoutEcouteurs(){
		//Modif en fonction du contenu
		this.comboMode.addActionListener(new EcouteurListeModeJeux(this));
		this.joueur1.addKeyListener(new EcouteurNomJoueur(this));
		this.joueur2.addKeyListener(new EcouteurNomJoueur(this));
		this.couleurBlanc.addActionListener(new EcouteurCouleurJoueur(this));
		this.couleurNoir.addActionListener(new EcouteurCouleurJoueur(this));
		
		//Clic annuler ou valider
		this.valider.addActionListener(new ControleurAjoutPartie(this));
		this.annuler.addActionListener(new ControleurAjoutPartie(this));
	}
	//====================================================================================================
}