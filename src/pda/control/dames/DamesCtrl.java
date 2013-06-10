package pda.control.dames;

import javax.swing.JPanel;

import pda.control.IApplication;
import pda.control.PdaCtrl;
import pda.view.dames.DameView;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
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
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur du controleur principal du jeu de dame.
	 * Cela va initialiser la vue ainsi que le modèle.
	 */
	public DamesCtrl(){
		this.vue = new DameView();
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
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
		return true;
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