package pda.view.dames;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import pda.control.dames.DamesCtrl;
import pda.datas.dames.Jeu;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcranChargement extends JList {
	
	
	//============================================ATTRIBUT(S)============================================
	
	/**
	 * JPanel nord
	 */
	private JPanel nord;
	
	/**
	 * JPanel du centre
	 */
	private JPanel centre;
	
	/**
	 * JPanel du sud
	 */
	private JPanel sud;
	
	/**
	 * JList qui contient toutes les parties sauvegardées
	 */
	private JList liste;
	
	/**
	 * JLabel qui contient les instructions
	 */
	private JLabel label1;
	
	/**
	 * Bouton annuler, pour revenir au menu principal
	 */
	private JButton annuler;
	
	/**
	 * Bouton charger pour lancer une partie sauvegardée 
	 */
	private JButton charger;
	
	/**
	 * Bouton supprimer pour supprimer une partie sauvegardée
	 */
	private JButton supprimer;
	
	private DamesCtrl controleur;
	

	
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	public EcranChargement(DamesCtrl controleurP){
		this.controleur = controleurP;
		this.setLayout(new BorderLayout());
		this.creerInterface();
		this.add(nord,BorderLayout.NORTH);
		this.add(centre,BorderLayout.CENTER);
		this.add(sud,BorderLayout.SOUTH);
		
	}
	
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	//====================================================================================================
	
	public void creerInterface(){
		
		//Gère la partie nord de l'écran
		this.nord = new JPanel();
		this.label1 = new JLabel("Veuillez sélectionner une partie :");
		this.nord.add(label1);
		
		//Gère la partie centre de l'écran
		this.centre = new JPanel();
		this.liste = new JList();
		
		
		
		//Gère la partie sud de l'écran avec les boutons Annuler et Supprimer
		this.sud = new JPanel();
		this.annuler = new JButton("Annuler");
		this.supprimer = new JButton("Supprimer");
		this.sud.add(annuler);
		this.sud.add(supprimer);
				
		
	}
	
	public void actualiserInterface(){
		this.centre.removeAll();
	}
	
	public void ajoutEcouteur(){
		
	}
	
}