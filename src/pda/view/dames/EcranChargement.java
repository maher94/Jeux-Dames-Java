package pda.view.dames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pda.control.dames.DamesCtrl;
import pda.control.dames.EcouteurChargement;
import pda.datas.dames.Partie;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Ecran qui va permettre à charger l'une des parties conservées dans le jeu.<br>
 * Il pourra également choisir de retourner au menu ou de supprimer une des parties conservées.
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
	
	/**
	 * Le controleur de l'application
	 */
	private DamesCtrl controleur;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur de l'écran de paramètre, qui initialise l'écran et met en place les <br>
	 * différents composants 
	 */
	public EcranChargement(DamesCtrl controleurP){
		this.controleur = controleurP;
		this.setLayout(new BorderLayout());
		this.creerInterface();
		this.ajoutEcouteur();
		this.add(nord,BorderLayout.NORTH);
		this.add(centre,BorderLayout.CENTER);
		this.add(sud,BorderLayout.SOUTH);
		
	}
	
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Sert à retourner le controleur
	 * @return Retourne de controleur
	 */
	public DamesCtrl getControleur(){
		return this.controleur;
	}
	
	/**
	 * Sert à retourner la liste des partie
	 * @return Retourne la liste
	 */
	public JList getListe(){
		return this.liste;
	}
	
	/**
	 * Sert à retourner le bouton charger
	 * @return Retourne le bouton charger
	 */
	public JButton getCharger(){
		return this.charger;
	}
	
	/**
	 * Sert à retourner le bouton supprimer
	 * @return Retourne le bouton supprimer
	 */
	public JButton getSupprimer(){
		return this.supprimer;
	}
	
	/**
	 * Sert à retourner le bouton annuler
	 * @return Retourne le bouton annuler
	 */
	public JButton getAnnuler(){
		return this.annuler;
	}
	//====================================================================================================
	
	
	//================================================IHM================================================
	/**
	 * Sert à créer et mettre en place les différents item graphique dans l'écran
	 */
	public void creerInterface(){
		
		//Gère la partie nord de l'écran
		this.nord = new JPanel();
		this.label1 = new JLabel("Veuillez sélectionner une partie :");
		this.nord.add(label1);
		
		//Gère la partie centre de l'écran
		this.centre = new JPanel();
		this.liste = new JList();
		this.liste.setModel(new DefaultListModel());
		JScrollPane scroll = new JScrollPane(this.liste);
		scroll.setPreferredSize(new Dimension(315,250));
		this.centre.add(scroll);
		
	
		
		
		//Gère la partie sud de l'écran avec les boutons Annuler et Supprimer
		this.sud = new JPanel();
		this.annuler = new JButton("Annuler");
		this.supprimer = new JButton("Supprimer");
		this.charger = new JButton("Charger");
		this.sud.add(annuler);
		this.sud.add(supprimer);
		this.sud.add(charger);
				
		this.actualiserInterface();
		
	}
	
	/**
	 * Sert à actualiser la liste de partie à chaque clic sur le bouton supprimer
	 */
	public void actualiserInterface(){
		//Si il y a un jeu chargé
		if(this.controleur.getJeu()!=null){
			DefaultListModel tabliste = (DefaultListModel) this.liste.getModel();
			tabliste.removeAllElements();
			Object[] cles = this.controleur.getJeu().date();
			for(Object o : cles){
				Date date = (Date)o;
				Partie partie = this.controleur.getJeu().obtenir(date);
				SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy-HH:mm",Locale.FRANCE);
				tabliste.addElement(formatdate.format(date)+"   /    "+partie.getJ1().getNom()+" VS "+partie.getJ2().getNom());
			}
		}
	}
	
	/**
	 * Sert à lié les écouteurs au item choisis
	 */
	public void ajoutEcouteur(){
		this.supprimer.addActionListener(new EcouteurChargement(this));
		this.charger.addActionListener(new EcouteurChargement(this));
		this.annuler.addActionListener(new EcouteurChargement(this));
	}
	//====================================================================================================
}