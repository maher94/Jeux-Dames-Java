package pda.control.dames;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pda.view.dames.EcranParametre;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Ecouteur qui va permettre de changer dynamiquement le nom des joueurs qui commencent en fonction des noms entrés.<br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcouteurNomJoueur implements KeyListener {
	
	
	//============================================ATTRIBUT(S)============================================
	
	/**
	 * Ecrans paramètres
	 */
	private EcranParametre ecranParametres;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur de l'écouteur du changement sur la liste
	 * @param ecranParametresP l'écran des paramètres
	 */
	public EcouteurNomJoueur(EcranParametre ecranParametresP){
		this.ecranParametres = ecranParametresP;
	}
	//====================================================================================================


	//===========================================ECOUTE CLAVIER===========================================
	/**
	 * Méthode qui va réagir à la pression d'une des touches du clavier.
	 */
	public void keyTyped(KeyEvent e) {
		if (e.getSource()== this.ecranParametres.getJoueur1()){
			String nomJoueur = this.ecranParametres.getJoueur1().getText();
			if (nomJoueur != null && e.getKeyChar()!=KeyEvent.CHAR_UNDEFINED){
				this.ecranParametres.getCommenceJ1().setText(this.ecranParametres.getJoueur1().getText()+e.getKeyChar());
			}
		}
		if (e.getSource()== this.ecranParametres.getJoueur2()){
			String nomJoueur = this.ecranParametres.getJoueur2().getText();
			if (nomJoueur != null && e.getKeyChar()!=KeyEvent.CHAR_UNDEFINED){
				this.ecranParametres.getCommenceJ2().setText(this.ecranParametres.getJoueur2().getText()+e.getKeyChar());
			}
		}
		
	}
	
	//Pas de redéfinition des autres méthodes
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	//====================================================================================================	
}