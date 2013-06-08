package pda.datas.dames;

import pda.datas.dames.exception.InvalidPlateauSizeException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Cette classe représente une partie entre deux joueurs humains.<br>
 * Le jeu se fait tour à tour sur la même machine.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class PartieHumainvsHumain extends Partie{
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur d'une partie humain contre humain
	 * @param largeurPlateauP la largeur du plateau de jeux
	 * @param nombrePionsP le nombre de pions par joueur au lancement de la partie
	 * @param premierJoueurP la couleur qui doit commencer à jouer
	 * @param nomHumain1 le nom du joueur humain 1
	 * @param couleurHumain1 la couleur que le joueur humain 1 a choisi
	 * @param nomHumain2 le nom du joueur humain 2
	 * @param couleurHumain2 la couleur que le joueur humain 2 a choisi
	 * @throws IllegalArgumentException si les paramètres de création du plateau sont incorrects
	 * @throws InvalidPlateauSizeException si il y a un problème avec les joueurs
	 */
	public PartieHumainvsHumain(int largeurPlateauP, int nombrePionsP,int premierJoueurP,String nomHumain1,int couleurHumain1,String nomHumain2,int couleurHumain2) throws InvalidPlateauSizeException,IllegalArgumentException {
		super(largeurPlateauP, nombrePionsP, premierJoueurP);
		//Création joueurs
		Joueur j1 = new Joueur(nomHumain1,couleurHumain1);
		Joueur j2 = new Joueur(nomHumain2,couleurHumain2);
		this.setJ1(j1);
		this.setJ2(j2);
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	//====================================================================================================
	
}