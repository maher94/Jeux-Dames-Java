package pda.datas.dames.test;

import junit.framework.TestCase;

import org.junit.Test;

import pda.datas.dames.Case;
import pda.datas.dames.Pion;

public class CaseTest extends TestCase{

	/**
	 * Test du constructeur d'une case
	 */
	public void testCase() {
		//Mauvaise couleur
				try {
					Case c = new Case(3);
					fail("Exception etait attendue");
				} catch (IllegalArgumentException e) {}
				
				//Bonne couleur
				try {
					Case c = new Case(Case.CASE_BLANCHE);
					assertNotNull(c);
				} catch (IllegalArgumentException e) {
					fail("Il ne devait pas y avoir d'exception");
				}
			}

	

	/**
	 * Test de la méthode GetPionPose
	 */
	public void testGetPionPose() {
		try {
			Case c = new Case(Case.CASE_BLANCHE);
			assertNull(c.getPionPose());
			c.setPionPose(new Pion(Pion.PION_BLANC));
			assertNotNull(c.getPionPose());
		} catch (IllegalArgumentException e) {
			fail("Il ne devait pas y avoir d'exception");
		}

	}

	/**
	 * Test de la méthode SetPionPose
	 */
	public void testSetPionPose() {
		try {
			Case c = new Case(Case.CASE_BLANCHE);
			assertNull(c.getPionPose());
			Pion p =new Pion(Pion.PION_BLANC);
			c.setPionPose(p);
			assertEquals(c.getPionPose(),p);
		} catch (IllegalArgumentException e) {
			fail("Il ne devait pas y avoir d'exception");
		}
	}

	

	/**
	 * test de la méthode qui change la couleur d'une case
	 */
	public void testSetCouleur() {
		try {
			Case c = new Case(Case.CASE_BLANCHE);
			c.setCouleur(Case.CASE_NOIRE);
			assertEquals(c.getCouleur(),Case.CASE_NOIRE);
		} catch (IllegalArgumentException e) {
			fail("Il ne devait pas y avoir d'exception");
		}
		try{
			Case c=new Case(3);
			fail("Exception etait attendue");
		}catch(IllegalArgumentException e){}

	}

}
