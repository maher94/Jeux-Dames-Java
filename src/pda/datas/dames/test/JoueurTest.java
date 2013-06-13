package pda.datas.dames.test;

import junit.framework.TestCase;

import org.junit.Test;

import pda.datas.dames.Deplacement;
import pda.datas.dames.Joueur;

public class JoueurTest extends TestCase{

	/**
	 * Test du constructeur de Joueur
	 */
	public void testJoueur() {
		try {
			Joueur joueur = new Joueur("Paul",3);
			fail("Exception etait attendue");
		} catch (IllegalArgumentException e) {}
			
		
	}

	/**
	 * Test de la méthode qui retourne la couleur du joueur
	 */
	public void testGetCouleur() {
		Joueur joueur = new Joueur("Paul",1);
		assertEquals(joueur.getCouleur(),1);
	}

	/**
	 * Test de la méthode qui retourne le dernier déplacement effectué par le joueur
	 */
	public void testGetDernierDeplacement() {
		Joueur joueur = new Joueur("Paul",1);
		Deplacement dep = new Deplacement(0,1,1,0);
		joueur.setDernierDeplacement(dep);
		assertEquals(joueur.getDernierDeplacement(),dep);
	}


	/**
	 * Test de la méthode qui définie le dernier déplacement effectué par le joueur. 
	 */
	public void testSetDernierDeplacement() {
		Joueur joueur = new Joueur("Paul",1);
		joueur.setDernierDeplacement(new Deplacement(0,1,1,0));
		Deplacement dep = new Deplacement(0,1,1,0);
		assertEquals(joueur.getDernierDeplacement(),dep);
	}

}
