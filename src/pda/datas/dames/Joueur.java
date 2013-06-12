package pda.datas.dames;

import java.io.Serializable;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Cette classe représente un joueur.<br>
 * L'objet joueur servira lors des parties.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class Joueur implements Serializable{
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le nom du joueur
	 */
	protected String nom;
	
	/**
	 * La couleur jouée par le joueur
	 */
	protected int couleur;
	
	/**
	 * Le dernier déplacement joué par le joueur.<br>
	 * Cet attribut sert dans la partie pour savoir si le joueur effectue une prise multiple.
	 */
	protected Deplacement dernierDeplacement;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur d'un joueur, le joueur possède un nom et une couleur de pion à jouer.
	 * @param nomP le nom du joueur
	 * @param couleurP la couleur du joueur
	 * @throws IllegalArgumentException Si la couleur jouée n'est pas valide (différente de NOIR ou BLANC définient dans la classe Pion )
	 */
	public Joueur(String nomP,int couleurP)throws IllegalArgumentException{
		this.nom = nomP;
		if(couleurP!=Pion.PION_BLANC && couleurP!=Pion.PION_NOIR)throw new IllegalArgumentException("La couleur jouée par le joueur est invalide");
		this.couleur = couleurP;
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Sert à retourner le nom du joueur
	 * @return Retourne le nom du joueur
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Sert à retourner la couleur du joueur
	 * @return Retourne la couleur du joueur
	 */
	public int getCouleur(){
		return this.couleur;
	}
	
	/***
	 *Sert à retourner le dernier déplacement effectué par le joueur 
	 * @return Retourne le dernier déplacement fait par le joueur
	 */
	public Deplacement getDernierDeplacement() {
		return this.dernierDeplacement;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	/**
	 * Sert à définir le nom du joueur
	 * @param nomP Le nom du joueur
	 */
	public void setNom(String nomP){
		this.nom = nomP;
	}
	
	/**
	 * Sert à définir le dernier déplacement effectué par le joueur
	 * @param dernierDeplacementP Le dernier déplacement fait par le joueur
	 */
	public void setDernierDeplacement(Deplacement dernierDeplacementP) {
		this.dernierDeplacement = dernierDeplacementP;
	}
	//====================================================================================================
}