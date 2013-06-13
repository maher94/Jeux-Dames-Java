package pda.datas.dames.test;

import junit.framework.TestCase;

import org.junit.Test;

import pda.datas.dames.Pion;

public class PionTest extends TestCase{

	/**
	 * Test du constructeur de Pion
	 */
	public void testPion() {
		try {
		Pion pion = new Pion(1);
		
		} catch (IllegalArgumentException e) {
			fail("Il ne doit pas y avoir d'exception");
		}
		
	}

	/**
	 * Test de la méthode qui change la couleur d'un pion
	 */
	public void testSetCouleur() {
		try {
			Pion pion = new Pion(1);
			pion.setCouleur(3);			
			fail("Il ne doit pas y avoir d'exception");
			} catch (IllegalArgumentException e) {}
	}

	/**
	 *Test de la méthode qui change le statut d'un pion en dame 
	 */
	public void testPromotion() {
		Pion pion = new Pion(1);
		pion.promotion();
		assertTrue(pion.isDame());
	}

	/**
	 * Test de la méthode qui retourne une chaine de caractère correspondant à une couleur
	 */
	public void testGetNomCouleur() {
		assertEquals(Pion.getNomCouleur(1),Pion.getCouleurBlanc());
	}
	
}
