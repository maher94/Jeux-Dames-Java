package pda.datas.dames.test;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import pda.datas.dames.Jeu;
import pda.datas.dames.PartieIAvsIA;
import pda.datas.dames.exception.InvalidPlateauSizeException;

public class JeuTest extends TestCase{

	/**
	 * Test de la méthode qui permet de récupérer un tableau de Date
	 */
	public void testDate() {
		
		try {
			Jeu jeu = new Jeu();
			PartieIAvsIA partie = new PartieIAvsIA(10,20,1,1);
			jeu.ajouter(partie);
			assertNotNull((Date)jeu.date()[0]);			
		} catch (IllegalArgumentException e) {
			fail("Il ne devrait pas y avoir d'exception");
		} catch (InvalidPlateauSizeException e) {
			fail("Il ne devrait pas y avoir d'exception");
		}
	}

	/**
	 * Test de la méthode qui retourne la taille de la HashMap
	 */
	public void testTaille() {
		try {
			Jeu jeu = new Jeu();
			PartieIAvsIA partie;
			partie = new PartieIAvsIA(10,20,1,1);
			jeu.ajouter(partie);
			assertEquals(jeu.taille(),1);
		} catch (IllegalArgumentException e) {
			fail("Il ne devrait pas y avoir d'exception");
		} catch (InvalidPlateauSizeException e) {
			fail("Il ne devrait pas y avoir d'exception");
		}
		
		
	}

	/**
	 * Test de la méthode qui supprime les parties de la liste des parties sauvegardée
	 */
	public void testSupprimer() {
		try {
			Jeu jeu = new Jeu();
			PartieIAvsIA partie;
			partie = new PartieIAvsIA(10,20,1,1);
			jeu.ajouter(partie);
			assertEquals(jeu.taille(),1);
			jeu.supprimer(jeu.getDate(0));
			assertEquals(jeu.taille(),0);
		} catch (IllegalArgumentException e) {
			fail("Il ne devrait pas y avoir d'exception");
		} catch (InvalidPlateauSizeException e) {
			fail("Il ne devrait pas y avoir d'exception");
		}
	}

	/**
	 * Test de la méthode qui ajoute une partie à la liste des parties sauvegardée
	 */
	public void testAjouter() {
		try {
			Jeu jeu = new Jeu();
			PartieIAvsIA partie;
			partie = new PartieIAvsIA(10,20,1,1);
			jeu.ajouter(partie);
			assertEquals(jeu.taille(),1);
		} catch (IllegalArgumentException e) {
			fail("Il ne devrait pas y avoir d'exception");
		} catch (InvalidPlateauSizeException e) {
			fail("Il ne devrait pas y avoir d'exception");
		}
	}

	/**
	 * Test de la méthode qui retourne une partie à partir d'une date
	 */
	public void testObtenir() {
		try {
			Jeu jeu = new Jeu();
			PartieIAvsIA partie;
			partie = new PartieIAvsIA(10,20,1,1);
			jeu.ajouter(partie);
			assertEquals(jeu.obtenir(jeu.getDate(0)),partie);
		} catch (IllegalArgumentException e) {
			fail("Il ne devrait pas y avoir d'exception");
		} catch (InvalidPlateauSizeException e) {
			fail("Il ne devrait pas y avoir d'exception");
		}
	}

}
