package pda.datas.dames.test;

import junit.framework.TestCase;
import pda.datas.dames.Deplacement;
import pda.datas.dames.Pion;
import pda.datas.dames.Plateau;
import pda.datas.dames.exception.InvalidPlateauSizeException;

public class PlateauTest extends TestCase{
	/**
	 * Plateau de test
	 */
	private Plateau testPlateau;
	
	/**
	 * Test du constructeur de plateau : avec nombre de pion et taille valide ou sans
	 */
	public void testPlateau() {
		//Test d'un plateau valide
		try {
			testPlateau = new Plateau(10,20);
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		//Test avec plateau dont le nombre case est impair
		try {
			testPlateau = new Plateau(10,21);
			fail("Exception attendue");
		} catch (InvalidPlateauSizeException e) {}
		//Test avec trop de joueur pour le nombre ligne
		try {
			testPlateau = new Plateau(4,4);
			fail("Exception attendue");
		} catch (InvalidPlateauSizeException e) {}
		//Test avec des lignes incomplètes
		try {
			testPlateau = new Plateau(4,1);
			fail("Exception attendue");
		} catch (InvalidPlateauSizeException e) {}
	}

	/**
	 * Test de l'obtention d'un pion du plateau
	 */
	public void testGetPion() {
		//Plateau valide
		try {
			testPlateau = new Plateau(10,20);
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		//Pas de pion
		assertNull(testPlateau.getPion(1, 5));
		//En dehors du plateau
		assertNull(testPlateau.getPion(115, 5120));
		//Placement pions
		testPlateau.placerPions();
		//Pion
		assertNotNull(testPlateau.getPion(1, 0));
	}

	/**
	 * Test de la taille du plateau
	 */
	public void testGetTaille() {
		//Plateau valide
		try {
			testPlateau = new Plateau(10,20);
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		//taille
		assertEquals(10,testPlateau.getTaille());
	}

	/**
	 * Test du changement de pion du plateau
	 */
	public void testSetPion() {
		//plateau valide
		try {
			testPlateau = new Plateau(10,20);
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		//Pas de pion
		testPlateau.setPion(0, 2, null);
		assertNull(testPlateau.getPion(0, 2));
		//Avec pion sur case blanche
		testPlateau.setPion(0, 2, new Pion(Pion.PION_NOIR));
		assertNull(testPlateau.getPion(0, 2));
		//Avec pion sur case noire
		testPlateau.setPion(1,0, new Pion(Pion.PION_NOIR));
		assertNotNull(testPlateau.getPion(1, 0));
	}

	/**
	 * Test de déplacement : ce test se sert de toutes les autres méthodes de déplacement.<br>
	 * Il servira donc comme test pour toutes les différentes méthodes déplacement.
	 */
	public void testEffectuerDeplacement() {
		//Notre plateau de test (10x10 : aucun pion dessus)
		try {
			testPlateau = new Plateau(10,20);
		} catch (InvalidPlateauSizeException e) {
			fail("Pas d'exception attendue");
		}
		
		/*
		 * On rappelle que les pions blancs sont toujours au nord et que les noirs sont toujours au sud
		 */
		//Test avec les pions noirs, la couleur étant un entier, si le test réussi avec les pions noirs, alors il fonctionnera avec les pions blancs
		testPlateau.setPion(1, 6, new Pion(Pion.PION_NOIR));
		//Déplacement normal
		assertTrue(testPlateau.effectuerDeplacement(new Deplacement(1,6,2,5),Pion.PION_NOIR));
		assertTrue(testPlateau.effectuerDeplacement(new Deplacement(2,5,1,4),Pion.PION_NOIR));
		assertFalse(testPlateau.effectuerDeplacement(new Deplacement(1,4,2,5),Pion.PION_NOIR));
		assertFalse(testPlateau.effectuerDeplacement(new Deplacement(1,4,0,5),Pion.PION_NOIR));
		//Prise arrière
		testPlateau.setPion(2,5,new Pion(Pion.PION_BLANC));
		assertTrue(testPlateau.effectuerDeplacement(new Deplacement(1,4,3,6),Pion.PION_NOIR));
		//Deplacement en dehors du plateau
		testPlateau.setPion(1, 0, new Pion(Pion.PION_NOIR));
		assertFalse(testPlateau.effectuerDeplacement(new Deplacement(1,0,0,-1),Pion.PION_NOIR));
		//Déplacement avec dame
		testPlateau.setPion(2, 5, new Pion(Pion.PION_NOIR));
		testPlateau.getPion(2, 5).promotion();
		//arrière
		assertTrue(testPlateau.effectuerDeplacement(new Deplacement(2,5,0,7),Pion.PION_NOIR));
		//avant
		assertTrue(testPlateau.effectuerDeplacement(new Deplacement(0,7,2,5),Pion.PION_NOIR));
		//pas par dessus les autres pions alliés
		assertFalse(testPlateau.effectuerDeplacement(new Deplacement(2,5,4,7),Pion.PION_NOIR));
	}
}
