package pda.datas.dames;

import pda.datas.dames.exception.InvalidPlateauSizeException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Cette classe représente une partie IA vs IA<br>
 * La partie se joue sans aucune intervention de l'utilisateur (ou juste le signal pour passer au tour suivant).<br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class PartieIAvsIA extends Partie{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;
	//====================================================================================================
	

	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur d'une partie IA vs IA
	 * @param largeurPlateauP la largeur du plateau de jeux
	 * @param nombrePionsP le nombre de pions par joueur au lancement de la partie
	 * @param premierJoueurP la couleur qui doit commencer à jouer
	 * @param difficulte la difficulté de l'IA (niveau de précision, voir classe IA)
	 * @throws IllegalArgumentException si les paramètres de création du plateau sont incorrects
	 * @throws InvalidPlateauSizeException si il y a un problème avec les joueurs
	 */
	public PartieIAvsIA(int largeurPlateauP,int nombrePionsP,int premierJoueurP,int difficulte) throws IllegalArgumentException, InvalidPlateauSizeException{
		super(largeurPlateauP, nombrePionsP, premierJoueurP);
		//Création des joueurs
		IA j1 = new IA("Ordinateur 1 (O)", difficulte,this.getPlateau(),Pion.PION_BLANC);
		IA j2 = new IA("Ordinateur 2 (X)",difficulte,this.getPlateau(),Pion.PION_NOIR);
		this.setJ1(j1);
		this.setJ2(j2);
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Cette méthode va faire jouer l'IA pour un tour.<br>
	 * Il se peut que l'IA joue deux fois de suite si il y a une prise multiple. (pour plus de détails, voir la méthode tourSuivant() et jouerTour(...) de Partie) 
	 * @return le déplacement qui vient d'être joué par l'IA.
	 */
	public Deplacement faireJouerIA(){
		Deplacement deplacementAFaire=null;
		if(!this.isPartieFinie()){
			//Récupération de l'IA
			IA j = (IA)this.getJoueurActuel();
			//Si est en train de faire une prise multiple
			if(this.isPriseMultiple()){
				deplacementAFaire = j.getMeilleursDeplacement(this.getJoueurActuel().getDernierDeplacement());
			}else{
				deplacementAFaire = j.getMeilleursDeplacement(null);
			}
			//Fait le déplacement
			this.jouerTour(deplacementAFaire);
		}
		return deplacementAFaire;
	}
	//====================================================================================================
	
}