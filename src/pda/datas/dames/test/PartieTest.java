package pda.datas.dames.test;

import junit.framework.TestCase;

import org.junit.Test;

import pda.datas.dames.Joueur;
import pda.datas.dames.Partie;
import pda.datas.dames.PartieIAvsIA;
import pda.datas.dames.Pion;
import pda.datas.dames.exception.InvalidPlateauSizeException;

public class PartieTest extends TestCase{
	/**
	 * Partie qui va nous servir de test
	 */
	private Partie partieTest;
	
	/**
	 * Test du constructeur de la partie (on ne test que certains aspect, par exemple les test en rapport avec la taille du plateau ne sont pas fait en détail)
	 */
	public void testPartie() {
		try {
			partieTest = new PartieIAvsIA(10,20,0,1);
			fail("Exception attendue");
		} catch (IllegalArgumentException e) {
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		try {
			partieTest = new PartieIAvsIA(12,20,1,1);
			fail("Exception attendue");
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		} catch (InvalidPlateauSizeException e) {
		}
		try {
			partieTest = new PartieIAvsIA(10,20,1,1);
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
	}

	/**
	 * Test de changement du joueur 1
	 */
	public void testSetJ1() {
		//Création d'une partie valide
		try {
			partieTest = new PartieIAvsIA(10,20,1,1);
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		//La couleur du joueur 2 est Noire
		try{
			partieTest.setJ1(new Joueur(null,Pion.PION_NOIR));
			fail("Exception attendue");
		} catch (IllegalArgumentException e) {}
		//Le blanc devrait fonctionner
		try{
			partieTest.setJ1(new Joueur(null,Pion.PION_BLANC));
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		}
	}

	/**
	 * Même test que pour le joueur 1
	 */
	public void testSetJ2() {
		//Création d'une partie valide
		try {
			partieTest = new PartieIAvsIA(10,20,1,1);
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		//La couleur du joueur 2 est Noire
		try{
			partieTest.setJ2(new Joueur(null,Pion.PION_BLANC));
			fail("Exception attendue");
		} catch (IllegalArgumentException e) {}
		//Le blanc devrait fonctionner
		try{
			partieTest.setJ2(new Joueur(null,Pion.PION_NOIR));
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		}
	}

	/**
	 * Test pour savoir si la partie est terminée
	 */
	public void testIsPartieFinie() {
		try {
			//Avec un petit plateau, en trois déplacements la partie devrait être terminée
			partieTest = new PartieIAvsIA(4,2,1,1);
			while(partieTest.faireJouerIA()!=null);
			assertTrue(partieTest.isPartieFinie());
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		try {
			//Avec la disposition actuelle, la partie ne devrait pas être finie
			partieTest = new PartieIAvsIA(4,2,1,1);
			assertFalse(partieTest.isPartieFinie());
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
	}

	/**
	 * Test de la méthode getGagnant lorsqu'il doit y en avoir un et lorsqu'il n'y en a pas.
	 */
	public void testGetGagnant() {
		//On prend une partie non finie : pas de gagnant
		try {
			partieTest = new PartieIAvsIA(4,2,1,1);
			assertNull(partieTest.getGagnant());
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		//On prend une partie finie : un gagnat
		try {
			partieTest = new PartieIAvsIA(4,2,1,1);
			while(partieTest.faireJouerIA()!=null);
			assertNotNull(partieTest.getGagnant());
		} catch (IllegalArgumentException e) {
			fail("Pas d'exception attendue");
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
	}

}
