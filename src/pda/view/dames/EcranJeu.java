package pda.view.dames;

import java.awt.BorderLayout;

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
	 * Le controleur principal de l'application de jeu de dame
	 */
	private DamesCtrl controleur;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	
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
	 * Obtenir la partie jouée sur l'écran de jeu
	 * @return la partie jouée
	 */
	public Partie getPartieJouee(){
		return this.partieJouee;
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
		this.affichageInfos = new JPanel();
		this.add(this.affichageInfos,BorderLayout.NORTH);
		
		
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
		//Ecoute du plateau de jeu
		this.affichagePlateau.addMouseListener(new ControleurJeu(this));
		//
	}
	//====================================================================================================
	
}