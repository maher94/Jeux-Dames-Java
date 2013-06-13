package pda.datas.dames.test;

public class LanceurTest {

	/**
	 * Méthode qui va lancer tous les test JUnit les uns à la suite des autres
	 * @param args aucun disponnibles
	 */
	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		System.out.println("Lancement de tous les test JUnit du modele");
		
		//Les test
		System.out.println("Test de Case :");
		junit.textui.TestRunner.run(CaseTest.class);
		
		System.out.println("Test de Pion :");
		junit.textui.TestRunner.run(PionTest.class);
			
		System.out.println("Test de Deplacement :");
		junit.textui.TestRunner.run(DeplacementTest.class);
		
		System.out.println("Test de Plateau :");
		junit.textui.TestRunner.run(PlateauTest.class);
		
		System.out.println("Test de Jeu : ");
		junit.textui.TestRunner.run(JeuTest.class);
		
		System.out.println("Test de Joueur :");
		junit.textui.TestRunner.run(JoueurTest.class);
		
		System.out.println("Test de Partie :");
		junit.textui.TestRunner.run(PartieTest.class);
		
		//Affichage du temps
		System.out.println("Tests termines en "+(System.currentTimeMillis()-debut)+" ms");
	}

}
