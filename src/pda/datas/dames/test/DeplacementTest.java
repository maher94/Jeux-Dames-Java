package pda.datas.dames.test;

import junit.framework.TestCase;

import org.junit.Test;

import pda.datas.dames.Deplacement;
import pda.datas.dames.exception.InvalidDeplacementException;

public class DeplacementTest extends TestCase{

	/**
	 * Test de la méthode qui permet de calculer la distance d'un déplacement
	 */
	public void testGetDistanceDeplacement() {
		//Test distance simple
		Deplacement d = new Deplacement(1,1,2,2);
		assertEquals(d.getDistanceDeplacement(), 1);
		//Test distance plus complexe
		d = new Deplacement(0,0,-5,-5);
		assertEquals(d.getDistanceDeplacement(),7);
	}

	/**
	 * Test de la méthode equals, cette méthode ne doit normalement comparer que les coordonnées et non la prise ou le dernier déplacement
	 */
	public void testEqualsObject() {
		//Deplacement qui doit être égal
		Deplacement d = new Deplacement(5,4,3,5);
		d.setPriseEffectuee(true);
		d.setPionPris(null);
		Deplacement d1 = new Deplacement(5,4,3,5);
		d.setPriseEffectuee(false);
		assertTrue(d.equals(d1));
		//Déplacement qui ne doivent pas être égaux
		d1 = new Deplacement(5,4,8,6);
		d1.setPriseEffectuee(true);
		assertFalse(d.equals(d1));
	}

	/**
	 * Méthode qui sert à test l'orientation d'un déplacement.<br>
	 * On ne testera cette méthode qu'avec des déplacements en diagonales (car il est notifier que la méthode ne retourne des résultats
	 * valides que dans ce cas là )
	 */
	public void testGetOrientationDeplacement() {
		//Nord ouest
		Deplacement no = new Deplacement(1,1,0,0);
		assertEquals(Deplacement.getOrientationDeplacement(no),Deplacement.NO);
		//Nord est
		Deplacement ne = new Deplacement(1,1,2,0);
		assertEquals(Deplacement.getOrientationDeplacement(ne),Deplacement.NE);
		//Sud ouest
		Deplacement so = new Deplacement(1,1,0,2);
		assertEquals(Deplacement.getOrientationDeplacement(so),Deplacement.SO);
		//Sud est
		Deplacement se = new Deplacement(1,1,2,2);
		assertEquals(Deplacement.getOrientationDeplacement(se),Deplacement.SE);
	}

	/**
	 * Test de la méthode qui parse un déplacement.<br>
	 * On va tenter le parsing avec des textes valides, et avec des textes non valides.
	 */
	public void testParseDeplacement() {
		try {
			Deplacement d = Deplacement.parseDeplacement("A/5-B/6");
			assertTrue(d.equals(new Deplacement(0,5,1,6)));
		} catch (InvalidDeplacementException e) {
			fail("Pas d'exception attendue");
		}
		try {
			Deplacement d = Deplacement.parseDeplacement("8/5-26");
			fail("Exception attendue");
		} catch (InvalidDeplacementException e) {}
		try {
			Deplacement d = Deplacement.parseDeplacement("8-5/B/6");
			fail("Exception attendue");
		} catch (InvalidDeplacementException e) {}
		try {
			Deplacement d = Deplacement.parseDeplacement("8/B-4/C");
			fail("Exception attendue");
		} catch (InvalidDeplacementException e) {}
		
	}

}
