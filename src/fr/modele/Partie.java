package fr.modele;

import java.util.ArrayList;

import fr.modele.exception.InvalidPlateauSizeException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Classe abstraite qui va permettre aux joueurs de jouer une partie.<br>
 * La classe est abstraite car plusieurs type de partie peuvent être disponnible.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public abstract class Partie {
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Le premier joueur de la partie
	 */
	private Joueur j1;
	
	/**
	 * Le second joueur de la partie
	 */
	private Joueur j2;
	
	/**
	 * Le joueur actuel (correspond en fait à une couleur, c'est donc la couleur qui doit être jouée)
	 */
	private int couleurJouee;
	
	/**
	 * Le nombre de tour joués
	 */
	private int nbTours;
	
	/**
	 * Le temps écoulé (en ms)
	 */
	private long tempsEcoule;
	
	/**
	 * La date de départ de la partie
	 */
	private long tempsDepart;
	
	/**
	 * Si le tour suivant va devoir être la suite d'une prise multiple
	 */
	private boolean priseMultiple;
	
	/**
	 * Le plateau sur lequel se déroule la partie
	 */
	private Plateau plateauJeux;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Le constructeur d'une partie de dame.<br>
	 * Le constructeur va créer le plateau en fonction des paramètres.
	 * @param largeurPlateau la largeur du plateau
	 * @param nombrePions le nombre de pions du plateau
	 * @param premierJoueur la couleur qui sera jouée en premier
	 * @throws InvalidPlateauSizeException si le taille du plateau ou le nombre de pion est invalide.
	 * @throws IllegalArgumentException si le premier joueur ne correspond pas à une couleur
	 */
	public Partie(int largeurPlateau,int nombrePions,int premierJoueur)throws InvalidPlateauSizeException,IllegalArgumentException{
		if(premierJoueur!=Pion.PION_BLANC && premierJoueur!=Pion.PION_NOIR)throw new IllegalArgumentException("Le premier joueur doit correspondre à une couleur valide");
		this.plateauJeux = new Plateau(largeurPlateau,nombrePions);
		this.plateauJeux.placerPions();
		this.couleurJouee = premierJoueur;
		this.tempsDepart = System.currentTimeMillis();
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	public Plateau getPlateau(){
		return this.plateauJeux;
	}
	public Joueur getJ1() {
		return this.j1;
	}
	public Joueur getJ2() {
		return this.j2;
	}
	public int getCouleurJouee() {
		return this.couleurJouee;
	}
	public int getNbTours() {
		return this.nbTours;
	}
	public long getTempsEcoule() {
		return this.tempsEcoule;
	}
	public boolean isPriseMultiple() {
		return this.priseMultiple;
	}
	public Plateau getPlateauJeux() {
		return this.plateauJeux;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	public void setJ1(Joueur j1P)throws IllegalArgumentException {
		if(this.j2!=null && this.j2.getCouleur()==j1P.getCouleur())throw new IllegalArgumentException("Les couleurs de joueur 1 et de joueur 2 doivent être différentes");
		this.j1 = j1P;
	}
	public void setJ2(Joueur j2P)throws IllegalArgumentException{
		if(this.j1!=null && this.j1.getCouleur()==j2P.getCouleur())throw new IllegalArgumentException("Les couleurs de joueur 1 et de joueur 2 doivent être différentes");
		this.j2 = j2P;
	}
	public void setCouleurJouee(int couleurJoueeP) {
		this.couleurJouee = couleurJoueeP;
	}
	public void setNbTours(int nbToursP) {
		this.nbTours = nbToursP;
	}
	public void setTempsEcoule(long tempsEcouleP) {
		this.tempsEcoule = tempsEcouleP;
	}
	public void setPriseMultiple(boolean priseMultipleP) {
		this.priseMultiple = priseMultipleP;
	}
	public void setPlateauJeux(Plateau plateauJeuxP) {
		this.plateauJeux = plateauJeuxP;
	}
	//====================================================================================================
	
	
	//========================================METHODES DE JEUX========================================
	/**
	 * Pour savoir si la partie est finie ou non.<br>
	 * <strong>Infos : </strong> on considère la partie lorsque un des deux joueurs ne peut plus faire de déplacement.<br>
	 * Cela peut se produire dans deux cas :<br>
	 * <ul>
	 * <li>Si les pions sont bloqués par d'autres pions (rare mais possible)</li>
	 * <li>Si tous les pions ont été mangés (cas classique)</li>
	 * </ul>
	 * @return vrai si la partie est finie, sinon retourne faux.
	 */
	public boolean isPartieFinie(){
		return !this.plateauJeux.deplacementPossible();
	}
	
	/**
	 * Pour obtenir le joueur qui doit joueur le tour actuel.<br>
	 * Cela se base sur la couleur actuellement jouée.
	 * @return le joueur qui doit jouer.
	 */
	public Joueur getJoueurActuel(){
		Joueur j = null;
		if(this.j1.getCouleur()==this.couleurJouee)j=this.j1;
		else j=this.j2;
		return j;
	}
	
	/**
	 * Cette méthode va faire passer la partie au tour suivant, c'est à dire
	 * changer le joueur qui doit jouer le prochain tour.<br>
	 * <strong>ATTENTION : </strong>le joueur peut parfois être le même que celui qui a joué le tour d'avant,
	 * en effet si il y a une prise multiple à faire, on laisse au joueur la possibilité de rejouer et change un booleen
	 * pour dire qu'il y a une multiprise. Cela servira à controler si le déplacement suivant est bien celui qui doit suivre la multiprise.
	 */
	private void tourSuivant(){
		//Statistiques de la partie
		this.nbTours++;
		this.tempsEcoule = tempsEcoule + (System.currentTimeMillis()-this.tempsDepart);
		
		//Réinitialise le boolean de la prise multiple
		this.priseMultiple=false;
		
		//Si il y a un dernier deplacement, on vérifie si il y une multiprise.
		if(this.getJoueurActuel().getDernierDeplacement()!=null){
			Deplacement dernierDeplacement = this.getJoueurActuel().getDernierDeplacement();
			//Si le déplacement mangé un pion
			if(dernierDeplacement.isPriseEffectuee()){
				/*
				 * On vérifie si en partant de la case d'arrivée du dernier déplacement on peut manger un pion.
				 */
				ArrayList<Deplacement> deplacementPossible = this.plateauJeux.getDeplacementsPion(dernierDeplacement.getPositionXArrivee(), dernierDeplacement.getPositionYArrivee());
				if(deplacementPossible != Plateau.trierDeplacements(deplacementPossible)){
					//On a bien une prise multiple, on reste sur le même joueur et on le signale
					this.priseMultiple = true;
				}
			}
		}
		//Si il n'y a pas de prise multiple, comportement normal
		if(!this.priseMultiple){
			this.couleurJouee = -this.couleurJouee;
		}
	}
	
	/**
	 * Pour jouer un déplacement.<br>
	 * Le déplacement doit être valide, de plus, si on a une prise multiple, le déplacement doit partie de la case d'arrivée du dernier déplacemnt du joueur actuel.
	 * @param depl le déplacement que l'on veut faire.
	 * @return vrai si le déplacemnt a pu être effectué et donc si on est passé au tour suivant, retourne faux sinon.
	 */
	public boolean jouerTour(Deplacement depl){
		boolean deplacementBon = false;
		//Si on est dans une prise multiple
		if(this.priseMultiple){
			//Vérifie si le déplacement part bien de l'arrivée du dernier et qu'il effectue bien une prise
			if(depl.getPositionXOrigine()==this.getJoueurActuel().getDernierDeplacement().getPositionXArrivee() && depl.getPositionYOrigine()==this.getJoueurActuel().getDernierDeplacement().getPositionYArrivee() && depl.isPriseEffectuee()){
				deplacementBon=this.plateauJeux.effectuerDeplacement(depl, this.couleurJouee);
			}
		}
		//Si on est dans une prise normale
		else{
			deplacementBon = this.plateauJeux.effectuerDeplacement(depl, this.couleurJouee);
		}
		//si le déplacement est bon, on passe au tour suivant et on met le déplacement en dernier déplacement
		if(deplacementBon){
			this.getJoueurActuel().setDernierDeplacement(depl);
			this.tourSuivant();
		}
		//Retour
		return deplacementBon;
	}
	//=================================================================================================
	
	
	//=======================================SAUVEGARDE/CHARGEMENT=======================================
	
	public void sauvegarder(){
		//On ajoute le temps écoulé
		this.tempsEcoule = this.tempsEcoule + (System.currentTimeMillis()-this.tempsDepart);
		//Sauvegarde
	}
	
	
	public static Partie charger(){
		Partie chargee = null;
		//Chargement
		
		//On change le temps de départ
		chargee.tempsDepart = System.currentTimeMillis();
		//retour
		return chargee;
	}
	//====================================================================================================
	
}