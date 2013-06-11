package pda.view.dames;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
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
	
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	 public EcranParametre(){
		this.setLayout(new BorderLayout());
		this.creerInterface();
		this.add(nord,BorderLayout.NORTH);
		this.add(centre,BorderLayout.CENTER);
		
		
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	public void creerInterface(){
		
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
		this.centre.setLayout(new GridLayout(6,1));	
		
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
		this.variantes.setBorder(BorderFactory.createTitledBorder("Choix variante"));
		this.centre.add(variantes);
		
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
		this.centre.add(diffIA);
		
		//Gère la partie choix de la couleur du Joueur 1
		this.couleurBlanc = new JRadioButton("Blanc");
		this.couleurNoir = new JRadioButton("Noir");
		this.groupCouleurJ1 = new ButtonGroup();
		this.groupCouleurJ1.add(couleurBlanc);
		this.groupCouleurJ1.add(couleurNoir);
		this.choixCouleur = new JPanel();
		this.choixCouleur.add(couleurBlanc);
		this.choixCouleur.add(couleurNoir);
		this.choixCouleur.setBorder(BorderFactory.createTitledBorder("Choix couleur Joueur1"));
		this.centre.add(choixCouleur);
		
		//Gère la partie choix du nom du Joueur 1
		joueur1 = new JTextField("Joueur 1");
		textJoueur1 = new JLabel("Nom du Joueur 1 :");
		this.nomJoueur1 = new JPanel();
		this.nomJoueur1.add(textJoueur1);
		this.nomJoueur1.add(joueur1);
		this.nomJoueur1.setBorder(BorderFactory.createTitledBorder("Choix nom du Joueur1"));
		this.centre.add(nomJoueur1);
		
		//Gère la partie choix du nom du Joueur 2
		joueur2 = new JTextField("Joueur 2");
		textJoueur2 = new JLabel("Nom du Joueur 2 :");
		this.nomJoueur2 = new JPanel();
		this.nomJoueur2.add(textJoueur2);
		this.nomJoueur2.add(joueur2);
		this.nomJoueur2.setBorder(BorderFactory.createTitledBorder("Choix nom du Joueur2"));
		this.centre.add(nomJoueur2);
		
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
		this.centre.add(choixCommence);
		
		
		
		
		
		
	
	}
	//====================================================================================================
	
}