package pda.view.dames;

import javax.swing.JPanel;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class DameView {
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Panel principal du jeux de dames (celui où tout est affiché)
	 */
	private JPanel panelPrincipal;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	public DameView(){
		this.panelPrincipal = new EcranPlateau();
	}
	public DameView(){
		panelPrincipal = new EcranParametre();
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Obtenir le panel principal de l'application
	 * @return le panel principal du jeu
	 */
	public JPanel getPanelPrincipal(){
		return this.panelPrincipal;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	//====================================================================================================
	
}