package fr.modele;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Cette classe représente l'intelligence artificielle implentée dans le jeux.<br>
 * L'IA est en fait un joueur et possède donc des caractéristiques communes.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class IA extends Joueur{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Difficulté de l'IA (correspond au nombre de tour évalués)
	 */
	private int difficulte;
	
	/**
	 * Le plateau sur lequel se déroule la partie.
	 */
	private Plateau plateauJeux;
	
	/**
	 * Le nombre de possibilités évaluées (à remettre à zéro à chaque déplacement)
	 */
	private int nbPossibilitesEvaluees;
	
	/**
	 * Formattage du nombre de possibilités
	 */
	private static final DecimalFormat formatPossibilites = new DecimalFormat("###,###,###,###,###");
		
	/**
	 * La couleur des adversaires de l'IA
	 */
	private int couleurOpposante;
	
	/**
	 * Booleen qui sert à savoir si on affiche chaque évaluation ou pas
	 */
	private boolean debug;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur de l'IA.
	 * @param nomP Le nom de l'IA
	 * @param difficulteP La difficulté choisie.
	 * @param plateauJeuxP Le plateau sur lequel se déroule la partie.
	 * @param couleurP La couleur des pions de l'IA
	 * @throws IllegalArgumentException si la couleur n'est pas une couleur valide
	 */
	public IA(String nomP,int difficulteP,Plateau plateauJeuxP,int couleurP)throws IllegalArgumentException{
		super(nomP,couleurP);
		//Recuperation des attributs
		this.difficulte = difficulteP;
		this.plateauJeux = plateauJeuxP;
		this.couleurOpposante=-this.couleur;
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Cette méthode permet de calculer la note d'un déplacement.<br>
	 * Cette note servira à choisir le meilleurs déplacement possible pour un état de plateau donné.
	 * @param etatPlateau Le plateau dans l'état actuel (c'est à dire avant la déplacement à évaluer effectué)
	 * @param niveauPrevision Le niveau de précision auquel l'IA se trouve (correspond au nombre de coups d'avance calculés)
	 * @param deplacementPrecedent Le déplacement qui précede celui que l'on va effectuer, si il n'y a pas de déplacement précedent, mettre null
	 * @param deplacementAEvaluer Le déplacement que l'on veut évaluer, c'est à dire le déplacement pour lequel on veut obtenir une note
	 * @return la note du déplacement à évaluer, basée sur l'évaluation des coups suivants jusqu'au niveau de prévision prévu.
	 */
	public int getNoteDeplacement(Plateau etatPlateau,int niveauPrevision,Deplacement deplacementPrecedent,Deplacement deplacementAEvaluer){
		int note=0;
		//Si on se trouve dans le niveau de prevision
		if(niveauPrevision<this.difficulte){
			if(this.debug){
				System.out.println("====================================");
				System.out.println("Couleur jouée : "+this.couleur+" , adverse : "+this.couleurOpposante);
				System.out.println("Niveau de précision : "+niveauPrevision);
				System.out.println("Déplacement à évaluer : "+deplacementAEvaluer);
				System.out.println("Déplacement précédent : "+deplacementPrecedent);
				System.out.println("Etat du plateau de jeux :\n"+etatPlateau);
				System.out.println("====================================");
			}
			
			this.nbPossibilitesEvaluees++;
			Plateau copiePlateau = etatPlateau.clone();
			//Si le deplacement actuel mange un pion
			if(deplacementAEvaluer.isPriseEffectuee()){
				note++;
			}
			//Si le deplacement precedent existe et qu'il mange un pion
			if(deplacementPrecedent!=null){
				if(deplacementPrecedent.isPriseEffectuee() && deplacementAEvaluer.isPriseEffectuee()){
					note=note+2;
				}else{
					note++;
				}
			}
			
			//On regarde si le pion de depart n'est pas une dame
			boolean pionAuDepart = !(copiePlateau.getCase(deplacementAEvaluer.getPositionXOrigine(), deplacementAEvaluer.getPositionYOrigine()).getPionPose().isDame());
			
			//On joue le deplacement a faire
			copiePlateau.effectuerDeplacement(deplacementAEvaluer,this.couleur);
			
			//Si le deplacement creer une dame
			if(pionAuDepart){
				//Dame a l'arrivee
				if(copiePlateau.getCase(deplacementAEvaluer.getPositionXArrivee(), deplacementAEvaluer.getPositionYArrivee()).getPionPose().isDame()){
					note++;
				}
			}
			
			//On regarde les deplacements adverse
			ArrayList<Deplacement> deplacementsAdverses = copiePlateau.getTousDeplacements(this.couleurOpposante);
			
			//Si le deplacement adverse mange un pion
			if(Plateau.trierDeplacements(deplacementsAdverses)!=deplacementsAdverses){
				note=note-2;
			}
			//On ne garde que les deplacements valides
			deplacementsAdverses = Plateau.trierDeplacements(deplacementsAdverses);
			
			//Test de chaque deplacement adverses
			Plateau copiePlateau2;
			
			if(note>=0){
				//On fait tous les deplacements adverses si la note est correct
				for(int i=0;i<deplacementsAdverses.size();i++){
					copiePlateau2 = copiePlateau.clone();
					//Deplacement fait
					copiePlateau2.effectuerDeplacement(deplacementsAdverses.get(i),this.couleurOpposante);
					//Si le deplacement enemi adverse fait une dame
					if(copiePlateau2.getCase(deplacementsAdverses.get(i).getPositionXArrivee(), deplacementsAdverses.get(i).getPositionYArrivee()).getPionPose().isDame()){
						note--;
					}
					//Tous les deplacements possibles
					ArrayList<Deplacement> nouveauDeplacements = copiePlateau2.getTousDeplacements(this.couleur);
					nouveauDeplacements = Plateau.trierDeplacements(nouveauDeplacements);
					
					//On evalue chacun de ces deplacements
					for(int j=0;j<nouveauDeplacements.size();j++){
						note = note + this.getNoteDeplacement(copiePlateau2, niveauPrevision+1, deplacementAEvaluer, nouveauDeplacements.get(j));
					}
				}
			}
		}
		
		//Retour
		return note;
	}
	
	
	/**
	 * Obtenir le déplacement évaluer comme le meilleurs par l'IA parmis tous les déplacements possibles.<br>
	 * Cette méthode calcule seulement les notes de chaques déplacements possibles (récupérer grâce aux méthodes de la classe Plateau)
	 * puis retourne le déplacement qui obtient la meilleurs note.
	 * @param deplacementPriseMultiple le déplacement effectué sur la prise precedente si il y a une prise multiple.
	 * Cela permet de toujours choisir le déplacement réellement valide.
	 * En effet, lors d'une prise multiple il faut que déplacement suivant (donc la suite de la prise) parte de l'arrivée du déplacement d'avant.<br>
	 * Or si un déplacement qui mange un pion est évalué comme meilleurs et qu'il ne part pas de l'arrivée, alors le déplacement ne sera pas réellement valide.<br>
	 * Cependant, si il n'y a pas de prise multiple, il suffira de mettre le paramètre à null.
	 * @return la déplacement évalué comme étant le meilleurs
	 */
	public Deplacement getMeilleursDeplacement(Deplacement deplacementPriseMultiple){
		long debut = System.currentTimeMillis();
		this.nbPossibilitesEvaluees = 0;
		
		Deplacement meilleursDeplacement=null;
		
		//Recuperation de tous les deplacements possibles
		ArrayList<Deplacement> tousDeplacements = this.plateauJeux.getTousDeplacements(this.couleur);
		//Si on a une prise multiple, alors on évalue que les déplacement partant du déplacements précedent
		if(deplacementPriseMultiple!=null){
			tousDeplacements = this.plateauJeux.getDeplacementsPion(deplacementPriseMultiple.getPositionXArrivee(), deplacementPriseMultiple.getPositionYArrivee());
		}
		//On force la prise (règles officielles)
		tousDeplacements = Plateau.trierDeplacements(tousDeplacements);
		
		//Creation d'un tableau d'entier qui contiendra la note de chaque deplacement
		int[] noteDeplacements = new int[tousDeplacements.size()];
		
		//Pour chaque deplacements, on calcule sa note
		for(int i=0;i<noteDeplacements.length;i++){
			noteDeplacements[i] = this.getNoteDeplacement(this.plateauJeux,0,null,tousDeplacements.get(i));
		}
		
		//On cherche la meilleure note
		int noteMax = noteDeplacements[0];
		int indexNoteMax=0;
		for(int i=0;i<noteDeplacements.length;i++){
			//Trouve le max
			if(noteDeplacements[i]>noteMax){
				noteMax=noteDeplacements[i];
				indexNoteMax=i;
			}
		}
		
		//On retourne le meilleur des deplacements
		meilleursDeplacement = tousDeplacements.get(indexNoteMax);
		
		//Temps
		if(this.debug)System.out.println("\nMeilleur deplacement calculé en "+(System.currentTimeMillis()-debut)+" ms\nNombre de possibilité evaluées : "+formatPossibilites.format(this.nbPossibilitesEvaluees)+"\n");
		
		//Retour
		return meilleursDeplacement;
	}
	//====================================================================================================
	
}