package pda.datas.dames;

import java.io.Serializable;
import java.util.ArrayList;

import pda.datas.dames.exception.InvalidPlateauSizeException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Classe abstraite qui va permettre aux joueurs de jouer une partie.<br>
 * La classe est abstraite car plusieurs type de partie peuvent être disponnible.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public abstract class Partie implements Serializable{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
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
		this.lancerPartie();
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Obtenir le plateau sur lequel est jouée la partie
	 * @return le plateau de la partie
	 */
	public Plateau getPlateau(){
		return this.plateauJeux;
	}
	
	/**
	 * Obtenir le joueur 1
	 * @return le joueur 1
	 */
	public Joueur getJ1() {
		return this.j1;
	}
	/**
	 * Obtenir le joueur 2
	 * @return le joueur 2
	 */
	public Joueur getJ2() {
		return this.j2;
	}
	/**
	 * Connaitre la couleur actuellement jouée dans le tour
	 * @return la couleur jouée
	 */
	public int getCouleurJouee() {
		return this.couleurJouee;
	}
	/**
	 * Connaître le nombre de tours joués
	 * @return le nombre de tour
	 */
	public int getNbTours() {
		return this.nbTours;
	}
	
	/**
	 * Connaître le temps écoulé depuis le début de la partie
	 * @return le temps écoulé depuis le début
	 */
	public long getTempsEcoule() {
		if(this.tempsDepart!=0)this.tempsEcoule = tempsEcoule + (System.currentTimeMillis()-this.tempsDepart);
		this.tempsDepart = System.currentTimeMillis();
		return this.tempsEcoule;
	}
	
	/**
	 * Savoir le tour suivant est la suite d'une prise
	 * @return vrai si il y a une prise multiple
	 */
	public boolean isPriseMultiple() {
		return this.priseMultiple;
	}
	
	/**
	 * Obtenir le nom du joueur qui doit jouer
	 * @return le nom du joueur qui doit jouer
	 */
	public String getNomJoueurActuel(){
		return this.getJoueurActuel().getNom();
	}
	
	/**
	 * Obtenir le nombre de pion restant pour la couleur passée en paramètre
	 * @param couleur la couleur dont on veut le nombre de pion restants
	 * @return le nombre de pion restants
	 */
	public int getPionRestant(int couleur){
		return this.plateauJeux.getNombrePion(couleur);
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	/**
	 * Changer le joueur 1
	 * @param j1P le nouveau joueur 1
	 * @throws IllegalArgumentException si la couleur du joueur 1 n'est pas différente de celle du joueur 2
	 */
	public void setJ1(Joueur j1P)throws IllegalArgumentException {
		if(this.j2!=null && this.j2.getCouleur()==j1P.getCouleur())throw new IllegalArgumentException("Les couleurs de joueur 1 et de joueur 2 doivent être différentes");
		this.j1 = j1P;
	}
	/**
	 * Changer le joueur 2
	 * @param j2P le nouveau joueur 2
	 * @throws IllegalArgumentException si la couleur du joueur 2 n'est pas différente de celle du joueur 1
	 */
	public void setJ2(Joueur j2P)throws IllegalArgumentException{
		if(this.j1!=null && this.j1.getCouleur()==j2P.getCouleur())throw new IllegalArgumentException("Les couleurs de joueur 1 et de joueur 2 doivent être différentes");
		this.j2 = j2P;
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
	 * Obtenir le joueur gagnant de la partie.<br>
	 * Attention à n'appeler cette méthode qu'en fin de partie. 
	 * @return le joueur à qui il reste encore des déplacements à éffectuer (donc qui a encore des pions ou n'est pas bloqué).<br>
	 * <strong>ATTENTION : </strong>Retourne null si la partie n'est pas encore finie.
	 */
	public Joueur getGagnant(){
		Joueur gagnant = null;
		if(this.isPartieFinie()){
			if(this.plateauJeux.getTousDeplacements(this.j1.getCouleur()).size()==0 && this.plateauJeux.getTousDeplacements(this.j2.getCouleur()).size()!=0){
				gagnant = this.j2;
			}else if(this.plateauJeux.getTousDeplacements(this.j2.getCouleur()).size()==0 && this.plateauJeux.getTousDeplacements(this.j1.getCouleur()).size()!=0){
				gagnant=this.j1;
			}else{
				gagnant=null;
			}
		}
		//Retour
		return gagnant;
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
			if(depl.getPositionXOrigine()==this.getJoueurActuel().getDernierDeplacement().getPositionXArrivee() && depl.getPositionYOrigine()==this.getJoueurActuel().getDernierDeplacement().getPositionYArrivee()){
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
	
	/**
	 * Cette méthode va faire jouer l'IA pour un tour.<br>
	 * Si la partie ne possède pas de joueur IA, cette méthode ne ferra rien.
	 * Il se peut que l'IA joue deux fois de suite si il y a une prise multiple. (pour plus de détails, voir la méthode tourSuivant() et jouerTour(...) de Partie)
	 * <strong>ATTENTION : </strong> il faut être sûr d'appeler cette méthode quand c'est vraiment le tour de l'IA sinon elle retournera null.
	 * @return le déplacement qui vient d'être joué par l'IA.
	 */
	public Deplacement faireJouerIA(){
		Deplacement deplacementAFaire=null;
		if(!this.isPartieFinie() && this.getJoueurActuel() instanceof IA){
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
	//=================================================================================================
	
	
	//=======================================SAUVEGARDE/CHARGEMENT=======================================
	/**
	 * Cette méthode permet d'obtenir un temps de partie valide.<br>
	 * Il faudra l'appeler à chaque début de partie ou après chaque chargement.
	 */
	public void lancerPartie(){
		this.tempsDepart = System.currentTimeMillis();
	}
	//====================================================================================================	
}