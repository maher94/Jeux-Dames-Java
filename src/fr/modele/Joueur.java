package fr.modele;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Cette classe représente un joueur.<br>
 * L'objet joueur servira lors des parties.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class Joueur {
	
	
	//============================================ATTRIBUT(S)============================================
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
	public String getNom(){
		return this.nom;
	}
	public int getCouleur(){
		return this.couleur;
	}
	public Deplacement getDernierDeplacement() {
		return this.dernierDeplacement;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	public void setNom(String nomP){
		this.nom = nomP;
	}
	public void setDernierDeplacement(Deplacement dernierDeplacementP) {
		this.dernierDeplacement = dernierDeplacementP;
	}
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	
	//====================================================================================================
	
}