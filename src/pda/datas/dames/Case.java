package pda.datas.dames;

import java.io.Serializable;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Cette classe représente une case du plateau de dame.<br>
 * Une case se caractérise par le pion qu'elle possède (ou pas) et sa couleur.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class Case implements Serializable{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Entier qui représente la couleur noire/foncée (-1)
	 */
	public final static int CASE_NOIRE=-1; 
	
	/**
	 * Entier qui représente la couleur blanche/claire (1)
	 */
	public final static int CASE_BLANCHE=1;
	
	/**
	 * Pion qui est posé sur la case (si null, cela signifi que la case est vide)
	 */
	private Pion pionPose;
	
	/**
	 * Couleur de la case, representée par un entier
	 */
	private int couleur;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur d'une case.<br>
	 * Par défaut la case est vide (sans pion).
	 * @param couleurP La couleur de la case.
	 * @throws IllegalArgumentException Si la couleur n'est pas une couleur valide.
	 */
	public Case(int couleurP)throws IllegalArgumentException{
		//Si la couleur est valide
		if(couleurP==Case.CASE_BLANCHE || couleurP==Case.CASE_NOIRE){
					this.couleur = couleurP;
		}
		//Si la couleur n'est pas valide
		else{
			throw new IllegalArgumentException("Couleur de la case non valide");
		}
	}
	//====================================================================================================

	
	//============================================ACCESSEUR(S)============================================
	public Pion getPionPose() {
		return this.pionPose;
	}
	public int getCouleur() {
		return this.couleur;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	public void setPionPose(Pion pionPoseP) {
		this.pionPose = pionPoseP;
	}
	public void setCouleur(int couleurP)throws IllegalArgumentException {
		//Si la couleur est valide
		if(couleurP==Case.CASE_BLANCHE || couleurP==Case.CASE_NOIRE){
			this.couleur = couleurP;
		}
		//Si la couleur n'est pas valide
		else{
			throw new IllegalArgumentException("Couleur de la case non valide");
		}
	}
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Méthode qui permet d'obtenir une représentation d'une Case sous forme de chaîne.<br>
	 * @return Retourne la chaîne de caractère qui représente une Case.
	 */
	public String toString(){
		String caseChaine=null;
		//Si la case a un pion pose
		if(this.pionPose != null){
			caseChaine = this.pionPose.toString();
		}
		//Si la case est vide,en fonction de la couleur
		else{
			switch(this.couleur){
				case Case.CASE_BLANCHE:
					caseChaine = " - ";
				break;
				
				case Case.CASE_NOIRE:
					caseChaine = " = ";
				break;
			}
		}
		//Retour
		return caseChaine;
	}
	//====================================================================================================
	
}