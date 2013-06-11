package pda.view.dames;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pda.control.dames.EcouteurListeModeJeux;


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
	 * JPanel qui contient le choix du nom du Joueur 2
	 */
	private JPanel nomJoueur2;
	
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
		this.ajoutEcouteurs();
		this.add(comboMode,BorderLayout.NORTH);
		
		
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	public void creerInterface(){
		
		//Gère la partie nord, qui est le choix du mode de jeu
		comboMode = new JComboBox();
		this.iaVSia = new String("IA vs IA");
		this.iaVShumain = new String("IA vs HUMAIN");
		this.humainVShumain = new String("HUMAIN vs HUMAIN");
		comboMode.addItem(this.iaVSia);
		comboMode.addItem(this.iaVShumain);
		comboMode.addItem(this.humainVShumain);
		
		//Gère la partie choix de la variante 
		variante1 = new JRadioButton();
		variante2 = new JRadioButton("Salut");
		groupVariante = new ButtonGroup();
		groupVariante.add(variante1);
		groupVariante.add(variante2);
		nord = new JPanel();
		nord.add(variante1);
		nord.add(variante2);
		nord.add(comboMode);
	}
	
	public void ajoutEcouteurs(){
		this.comboMode.addActionListener(new EcouteurListeModeJeux(this));
	}
	//====================================================================================================
	
}