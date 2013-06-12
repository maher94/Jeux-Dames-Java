package pda.datas.dames;

import java.io.Serializable;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Cette classe répresente un pion du jeux.<br>
 * Un pion peut être une dame ou un pion normal, cela dépend du booleen qui indique si le pion est une dame ou non.<br>
 * Le pion possède également un entier qui réprésente sa couleur.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class Pion implements Serializable{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Entier qui représente la couleur noire/foncée (-1)
	 */
	public final static int PION_NOIR=-1; 
	
	/**
	 * Entier qui représente la couleur blanche/claire (1)
	 */
	public final static int PION_BLANC=1;
	
	/**
	 * Texte qui représente la couleur blanche 
	 */
	private final static String BLANC="Blanc";
	
	/**
	 * texte qui représente la couleur noire
	 */
	private final static String NOIR="Noir";
	
	/**
	 * Couleur du pion
	 */
	private int couleur;
	
	/**
	 * Si le pion est une dame
	 */
	private boolean dame;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur d'un pion.
	 * @param couleurP La couleur du pion. 
	 * @throws IllegalArgumentException Si la couleur n'est pas valide.
	 */
	public Pion(int couleurP)throws IllegalArgumentException{
		//Si la couleur est valide
		if(couleurP==Pion.PION_BLANC || couleurP == Pion.PION_NOIR){
			this.couleur = couleurP;
		}
		//Si la couleur n'est pas valide
		else{
			throw new IllegalArgumentException("Couleur du pion non valide");
		}
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Sert à retourner la couleur du pion
	 * @return Retourne la couleur du pion
	 */
	public int getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Sert à savoir si le pion est une dame ou pas.
	 * @return Retourne un booléen qui définie si le pion est une dame ou pas
	 */
	public boolean isDame(){
		return this.dame;
	}
	
	/**
	 * Sert à retourner une chaîne de caractère correspondant à la couleur blanche
	 * @return Retourne un String "Blanc"
	 */
	public static String getCouleurBlanc(){
		return Pion.BLANC;
	}
	
	/**
	 * Sert à retourner une chaîne de caractère correspondant à la couleur noir
	 * @return Retourne un String "Noir"
	 */
	public static String getCouleurNoir(){
		return Pion.NOIR;
	}
	
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	/**
	 * Sert à définir la couleur d'un pion
	 * @param couleurP Le couleur du pion
	 * @throws IllegalArgumentException Si la couleur n'est pas valide
	 */
	public void setCouleur(int couleurP)throws IllegalArgumentException {
		//Si la couleur est valide
		if(couleurP==Pion.PION_BLANC || couleurP == Pion.PION_NOIR){
			this.couleur = couleurP;
		}
		//Si la couleur n'est pas valide
		else{
			throw new IllegalArgumentException("Couleur du pion non valide");
		}
	}
	
	/**
	 * Sert à changer le statut d'un pion en dame
	 * @param dameP Booléen qui défini si un pion est aussi une dame ou pas
	 */
	public void setDame(boolean dameP){
		this.dame=dameP;
	}
	/**
	 * Cette méthode transforme le pion en dame.<br>
	 * Elle a le même effet que setDame(true) mais permet d'être plus lisible.
	 */
	public void promotion(){
		this.dame=true;
	}
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Méthode qui permet d'obtenir une représentation d'un Pion sous forme de chaîne.<br>
	 * @return Retourne la chaîne de caractère qui représente un Pion.
	 */
	public String toString(){
		String pionChaine = null;
		//Représentation en fonction de la couleur
		switch(this.couleur){
			case Pion.PION_BLANC:
				if(this.dame)pionChaine="[O]";
				else pionChaine=" O ";
			break;
			case Pion.PION_NOIR:
				if(this.dame)pionChaine="[X]";
				else pionChaine=" X ";
			break;
		}
		//Retour
		return pionChaine;
	}
	
	/**
	 * Méthode qui permet de retourner une chaîne qui décrit la couleur du pion.
	 * @param couleur la couleur à décrire
	 * @return la chaîne qui représente la couleur
	 */
	public static String getNomCouleur(int couleur){
		String nomCouleur="";
		if(couleur == Pion.PION_BLANC){
			nomCouleur= Pion.BLANC;
		}
		if(couleur==Pion.PION_NOIR){
			nomCouleur= Pion.NOIR;
		}
		return nomCouleur;
	}
	//====================================================================================================
	
}