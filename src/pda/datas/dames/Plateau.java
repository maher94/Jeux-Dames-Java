package pda.datas.dames;

import java.io.Serializable;
import java.util.ArrayList;

import pda.datas.dames.exception.InvalidPlateauSizeException;

/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class Plateau implements Serializable{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les cases du plateau de jeux, taille variable
	 */
	private Case[][] casesPlateau;
	
	/**
	 * Nombre de pion initial par joueur
	 */
	private int nbPionsPJ=20;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur d'un plateau avec comme paramètres la taille du plateau ainsi que le nombre de pion par joueur.
	 * @param longueur La longueur d'un coté du plateau
	 * @param nbPionsPJP Le nombre de pion initial par joueur
	 * @throws InvalidPlateauSizeException Exception lancée si la taille du plateau et/ou le nombre de pion initial sont incohérents.
	 */
	public Plateau(int longueur,int nbPionsPJP)throws InvalidPlateauSizeException{
		//Si il y a assez de case noire pour mettre tous les pions (pour les deux joueurs)
		if((longueur*longueur/2)>nbPionsPJP*2){
			
			//Si le nombre de pions par joueur forme un nombre exact de ligne (pour éviter les lignes incomplètes)
			if((1.0*nbPionsPJP)%(longueur*1.0/2)==0){
				//Recuperation du nombre de pion
				this.nbPionsPJ = nbPionsPJP;
				
				//Création du tableau
				this.casesPlateau = new Case[longueur][longueur];
				
				//Remplissage du plateau avec une alternance dans la couleurs des cases
				for(int i=0;i<this.casesPlateau.length;i++){
					for(int j=0;j<this.casesPlateau[i].length;j++){
						//Une ligne sur deux
						if(i%2==0){
							//Une colone sur deux
							if(j%2==0){
								this.casesPlateau[i][j] = new Case(Case.CASE_BLANCHE);
							}else{
								this.casesPlateau[i][j] = new Case(Case.CASE_NOIRE);
							}
						}else{
							//Une colone sur deux
							if(j%2==1){
								this.casesPlateau[i][j] = new Case(Case.CASE_BLANCHE);
							}else{
								this.casesPlateau[i][j] = new Case(Case.CASE_NOIRE);
							}
						}
					}
				}
			}
			//Nombre de pions par joueur incoherent
			else{
				throw new InvalidPlateauSizeException("Les pions ne forment pas un nombre entier de ligne");
			}
		}
		//Pas assez de case
		else{
			throw new InvalidPlateauSizeException("Il y a plus de pions que de cases disponibles sur le plateau");
		}
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Sert à retourner une case en fonction de ses coordonnées
	 * @param posX La coordonnée X de la case
	 * @param posY La coordonnée Y de la case
	 * @return Retourne une case
	 */
	public Case getCase(int posX,int posY){
		Case casePlateau=null;
		if(this.verifierEmplacement(posX, posY))casePlateau=this.casesPlateau[posY][posX];
		return casePlateau;
	}
	
	/**
	 * Sert à retourner un pion en fonction de ses coordonnées
	 * @param posX La coordonnée X du pion
	 * @param posY La coordonnée Y du pion
	 * @return Retourne un pion
	 */
	public Pion getPion(int posX,int posY){
		Pion pionPlateau=null;
		if(this.verifierEmplacement(posX, posY))pionPlateau=this.casesPlateau[posY][posX].getPionPose();
		return pionPlateau;
	}
	
	/**
	 * Sert à retourner la taille du plateau
	 * @return Retourne la taille du plateau
	 */
	public int getTaille(){
		return this.casesPlateau.length;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	/**
	 * Sert à poser un pion sur une case
	 * @param posX La coordonnée X de la case où sera posé le pion
	 * @param posY La coordonnée Y de la case où sera posé le pion
	 * @param nouveauPion Le pion qui sera posé
	 */
	public void setPion(int posX,int posY,Pion nouveauPion){
		if(this.verifierEmplacement(posX, posY))this.casesPlateau[posY][posX].setPionPose(nouveauPion);
	}
	//====================================================================================================
	
	
	//============================================DEPLACEMENTS============================================
	/**
	 * Sert à effectuer un déplacement de pion
	 * @param deplacementAFaire Le déplacement à faire
	 * @param couleurPion La couleur du pion  déplacer
	 * @return Retourne un booléen qui indique si la prise a été effectuée ou non 
	 */
	public boolean effectuerDeplacement(Deplacement deplacementAFaire,int couleurPion){
		boolean deplacementEffectue=false;

		//Verification du deplacement
		if(this.verifierDeplacement(deplacementAFaire, couleurPion)){
			deplacementEffectue=true;
			//Si une prise est a faire
			if(deplacementAFaire.isPriseEffectuee()){
				this.effectuerPrise(deplacementAFaire);
			}
			//Si pas de prise, simple changement
			else{
				//Retire le pion a la position d'origine et le recupere
				Pion pionCase = this.getPion(deplacementAFaire.getPositionXOrigine(),deplacementAFaire.getPositionYOrigine());
				this.casesPlateau[deplacementAFaire.getPositionYOrigine()][deplacementAFaire.getPositionXOrigine()].setPionPose(null);
				
				//Met le pion a la position d'arrivee
				this.casesPlateau[deplacementAFaire.getPositionYArrivee()][deplacementAFaire.getPositionXArrivee()].setPionPose(pionCase);
				
				//Si une dame doit etre cree, NOIR
				if(deplacementAFaire.getPositionYArrivee()==0 && couleurPion==Pion.PION_NOIR){
					pionCase.promotion();
				}
				//Si une dame doit etre cree, BLANC
				else if(deplacementAFaire.getPositionYArrivee()==this.casesPlateau.length-1 && couleurPion==Pion.PION_BLANC){
					pionCase.promotion();
				}
			}
		}
		
		//Retour
		return deplacementEffectue;
	}
	
	
	/**
	 * Sert à vérifié la validité d'un déplacement
	 * @param d Le déplacement à vérifier
	 * @param couleurPion Couleur du pion à déplacer
	 * @return Retourne un booléen qui indique si le déplacement est valide ou non
	 */
	private boolean verifierDeplacement(Deplacement d,int couleurPion){
		boolean deplacementBon=false;
		
		//Creation de tous les deplacements possibles
		ArrayList<Deplacement> deplacements = this.getTousDeplacements(couleurPion);
		
		//Trie des deplacements
		deplacements = Plateau.trierDeplacements(deplacements);
		
		//On verifie la validite (le deplacement doit etre dans la liste)
		if(deplacements.contains(d)){
			//Deplacement ok
			deplacementBon=true;
			//Si on mange un pion, on modifie le deplacement qu'on devait verifier
			if(deplacements.get(deplacements.indexOf(d)).isPriseEffectuee()){
				d.setPriseEffectuee(true);
			}
		}
		
		//Retour
		return deplacementBon;
	}
	
	
	/**
	 * Sert à retourner la liste de tous les déplacements possibles pour une couleur donnée
	 * @param couleurPion Couleur des pions pour lequel il faut récupérer les déplacements
	 * @return Retourne une ArrayList avec tous les déplacements possibles d'un couleur
	 */
	public ArrayList<Deplacement> getTousDeplacements(int couleurPion){
		ArrayList<Deplacement> tousDeplacementsPossibles = new ArrayList<Deplacement>(50);
		
		//Parcours de tous les pions du plateau
		for(int i=0;i<this.casesPlateau.length;i++){
			for(int j=0;j<this.casesPlateau.length;j++){
				//Cases noire
				if(this.casesPlateau[i][j].getCouleur()==Case.CASE_NOIRE){
					//Pion existant et de la couleur voulue
					if(this.getPion(j, i)!=null){
						if(this.getPion(j, i).getCouleur() == couleurPion){
							//Recuperation des deplacements et ajout a la liste
							tousDeplacementsPossibles.addAll(this.getDeplacementsPion(j, i));
						}
					}
				}
			}
		}
		
		//Retour
		return tousDeplacementsPossibles;
	}
	
	/**
	 * Sert à retourner une liste de tous les déplacements possibles  
	 * @param positionX La coordonnées X du pion pour lequel il faut récupérer les déplacements possibles
	 * @param positionY La coordonnées Y du pion pour lequel il faut récupérer les déplacements possibles
	 * @return Retourne une ArrayList avec tous les déplacements possibles pour un pion donné
	 */
	public ArrayList<Deplacement> getDeplacementsPion(int positionX,int positionY){
		ArrayList<Deplacement> deplacementsPion =  new ArrayList<Deplacement>(6);
		
		Pion actuel = this.getPion(positionX, positionY);
		
		//Si c'est une DAME
		if(actuel.isDame()){
			//La couleur d'une dame n'influence pas l'orientation, car une dame peut se deplacer dans toutes les directions.
			
			//Verification deplacement-SE
			this.ajouterDeplacementsDame(deplacementsPion, positionX, positionY, Deplacement.SE);
			
			//Verification deplacement-SO
			this.ajouterDeplacementsDame(deplacementsPion, positionX, positionY, Deplacement.SO);
			
			//Verification deplacement-NE
			this.ajouterDeplacementsDame(deplacementsPion, positionX, positionY, Deplacement.NE);
			
			//Verification deplacement-NO
			this.ajouterDeplacementsDame(deplacementsPion, positionX, positionY, Deplacement.NO);
		}
		//Si c'est un PION
		else{
			/*
			 * PION NOIR
			 */
			if(actuel.getCouleur()==Pion.PION_NOIR){
				//Verification de l'orientation NO
				this.ajouterDeplacementsPion(deplacementsPion, positionX, positionY, Deplacement.NO);
				
				//Verification de l'orientation NE
				this.ajouterDeplacementsPion(deplacementsPion, positionX, positionY, Deplacement.NE);
				
			}
			
			/*
			 * PION BLANC
			 */
			if (actuel.getCouleur()== Pion.PION_BLANC){
				//Verification de l'orientation SO
				this.ajouterDeplacementsPion(deplacementsPion, positionX, positionY, Deplacement.SO);
					
				//Verification de l'orientation SE
				this.ajouterDeplacementsPion(deplacementsPion, positionX, positionY, Deplacement.SE);
			}
		}
	
		//Retour
		return deplacementsPion;
	}
	
	
	/**
	 * Sert à ajouter à l'ArrayList des déplacements possibles tous les déplacements possibles pour un pion de type dame 
	 * @param listeDeplacement La liste des déplacements possibles
	 * @param positionX La coordonnées X de la dame
	 * @param positionY La coordonnées Y de la dame
	 * @param orientation Définie l'orientation dans lequel les déplacements doivent être trouvés
	 */
	private void ajouterDeplacementsDame(ArrayList<Deplacement> listeDeplacement,int positionX,int positionY,int orientation){
		boolean continuer=true;
		int x=positionX,y=positionY;
		
		int coefX=0,coefY=0;
		//Determine les coef en fonction de l'orientation
		switch(orientation){
		case Deplacement.NE:
			coefX=1;
			coefY=-1;
			break;
		case Deplacement.NO:
			coefX=-1;
			coefY=-1;
			break;
		case Deplacement.SE:
			coefX=1;
			coefY=1;
			break;
		case Deplacement.SO:
			coefX=-1;
			coefY=1;
			break;
		}
		
		//Verification deplacement
		while(continuer){
			if(this.getPion(x,y)==null){
				Deplacement d =new Deplacement(positionX,positionY,x,y);
				listeDeplacement.add(d);
			}
			//Si on trouve un pion adverse
			else if(this.getPion(x,y).getCouleur()!=this.getPion(positionX,positionY).getCouleur()){
				//On ne parcourera pas la suite avec la boucle principale
				continuer=false;
				
				//Si la case d'apres est vide et dans le tableau
				if(this.verifierEmplacement(x+coefX,y+coefY) && this.getPion(x+coefX,y+coefY)==null){
					x=x+coefX;
					y=y+coefY;
					
					//On parcours ce qu'il y a apres, tant que c'est vide et que les coordonnees sont bonnes on continue et ajoute un deplacement.
					boolean continuerbis=true;
					while(continuerbis){			
						//On verifie que c'est vide
						if(this.getPion(x,y)==null){
							Deplacement d = new Deplacement(positionX,positionY,x,y);
							d.setPriseEffectuee(true);
							listeDeplacement.add(d);
						}
						//Si ce n'est pas vide, on ne pourra pas aller plus loin
						else{
							continuerbis=false;
						}
						x=x+coefX;
						y=y+coefY;
						continuerbis=continuerbis&&this.verifierEmplacement(x, y);
					}
				}
			}
			//Si un pion, mais pas celui de depart
			else if(this.casesPlateau[y][x]!= this.casesPlateau[positionY][positionX]){
				continuer=false;
			}
			
			x=x+coefX;
			y=y+coefY;
			continuer=continuer&&this.verifierEmplacement(x, y);
		}
	}
	
	
	/**
	 * Sert à ajouter à l'ArrayList des déplacements possibles tous les déplacements possibles pour un pion
	 * @param listeDeplacement La liste des déplacements possibles
	 * @param positionX La coordonnées X de la dame
	 * @param positionY La coordonnées Y de la dame
	 * @param orientation Définie l'orientation dans lequel les déplacements doivent être trouvés
	 */
	private void ajouterDeplacementsPion(ArrayList<Deplacement> listeDeplacement,int positionX,int positionY,int orientation){
		int coefX=0,coefY=0;
		//Determine les coef en fonction de l'orientation
		switch(orientation){
		case Deplacement.NE:
			coefX=1;
			coefY=-1;
			break;
		case Deplacement.NO:
			coefX=-1;
			coefY=-1;
			break;
		case Deplacement.SE:
			coefX=1;
			coefY=1;
			break;
		case Deplacement.SO:
			coefX=-1;
			coefY=1;
			break;
		}
		
		//Verifie et ajoute pour les deplacements vers l'avant
		//Verifie la case immediatement apres est dans le tableau
		if(this.verifierEmplacement(positionX+coefX, positionY+coefY) ){
			//Case vide ?
			if(this.getPion(positionX+coefX,positionY+coefY)==null){
				Deplacement d = new Deplacement(positionX,positionY,positionX+coefX,positionY+coefY);
				listeDeplacement.add(d);
			}
			//Case pleine avec pion enemi
			else if(this.getPion(positionX+coefX,positionY+coefY).getCouleur()!=this.getPion(positionX, positionY).getCouleur()){
				if(this.verifierEmplacement(positionX+2*coefX, positionY+2*coefY)){
					//Case vide ?
					if(this.getPion(positionX+2*coefX, positionY+2*coefY)==null){
						Deplacement d = new Deplacement(positionX,positionY,positionX+2*coefX,positionY+2*coefY);
						d.setPriseEffectuee(true);
						listeDeplacement.add(d);
					}
				}
			}
		}
		//Verifie et ajoute les deplacements vers l'arriere
		//Verifie si la case est dans le tableau
		if(this.verifierEmplacement(positionX-coefX, positionY-coefY)){
			//Si la case est pleine et avec un pion "enenmie"
			if(this.getPion(positionX-coefX,positionY-coefY)!= null){
				if(this.getPion(positionX-coefX,positionY-coefY).getCouleur()!=this.getPion(positionX,positionY).getCouleur()){
					//Case plus loin dans le tableau
					if(this.verifierEmplacement(positionX-2*coefX,positionY-2*coefY)){
						//Case vide
						if(this.getPion((positionX-2*coefX),(positionY-2*coefY))== null){
							Deplacement d = new Deplacement(positionX,positionY,positionX-2*coefX,positionY-2*coefY);
							d.setPriseEffectuee(true);
							listeDeplacement.add(d);
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Sert à trier les déplacements de la liste, en fonction de si une prise est effectuées ou non
	 * @param tousDeplacements La liste de tous les déplacements possibles
	 * @return Retourne une liste avec seulement les déplacements qui effectu une prise
	 */
	public static ArrayList<Deplacement> trierDeplacements(ArrayList<Deplacement> tousDeplacements){
		ArrayList<Deplacement> deplacementsTries =  new ArrayList<Deplacement>(50);
		
		//Parcours de la liste et recherche des deplacements qui effectuent une prise
		for(int i=0;i<tousDeplacements.size();i++){
			//Si on effectue une prise, ajout a la liste
			if(tousDeplacements.get(i).isPriseEffectuee()){
				deplacementsTries.add(tousDeplacements.get(i));
			}
		}
		
		//Si on n'a trouve aucun deplacement qui effectue une prise
		if(deplacementsTries.size()==0){
			deplacementsTries=tousDeplacements;
		}
		
		//Retour
		return deplacementsTries;
	}
	
	/**
	 * Sert à effectuer une prise
	 * @param d Déplacement pour laquel une prise doit être effectuée
	 */
	private void effectuerPrise(Deplacement d){
		//Recuperation de la couleur du pion
		int couleurPionJoueur = this.getPion(d.getPositionXOrigine(),d.getPositionYOrigine()).getCouleur();
		
		//Determine l'orientation
		int orientation = Deplacement.getOrientationDeplacement(d);
		
		//Variable qui servent au parcours
		int x=d.getPositionXOrigine(),y=d.getPositionYOrigine();
		
		//Parcours en fonction de l'orientation, pour trouver le pion adverse a enlever
		switch(orientation){
		
			//Orientation nord ouest
			case Deplacement.NO:
				while(x!=d.getPositionXArrivee() && y!=d.getPositionYArrivee()){
					//Si on trouve le pion a supprimer
					if(this.getPion(x, y)!=null && this.getPion(x, y).getCouleur()!=couleurPionJoueur){
						this.casesPlateau[y][x].setPionPose(null);
					}
					y--;
					x--;
				}
			break;
			
			//Orientation nord est
			case Deplacement.NE:
				while(x!=d.getPositionXArrivee() && y!=d.getPositionYArrivee()){
					//Si on trouve le pion a supprimer
					if(this.getPion(x, y)!=null && this.getPion(x, y).getCouleur()!=couleurPionJoueur){
						this.casesPlateau[y][x].setPionPose(null);
					}
					x++;
					y--;
				}
			break;
			
			//Orientation sud est
			case Deplacement.SE:
				while(x!=d.getPositionXArrivee() && y!=d.getPositionYArrivee()){
					//Si on trouve le pion a supprimer
					if(this.getPion(x, y)!=null && this.getPion(x, y).getCouleur()!=couleurPionJoueur){
						this.casesPlateau[y][x].setPionPose(null);
					}
					x++;
					y++;
				}
			break;
			
			//Orientation sud ouest
			case Deplacement.SO:
				while(x!=d.getPositionXArrivee() && y!=d.getPositionYArrivee()){
					//Si on trouve le pion a supprimer
					if(this.getPion(x, y)!=null && this.getPion(x, y).getCouleur()!=couleurPionJoueur){
						this.casesPlateau[y][x].setPionPose(null);
					}
					y++;
					x--;
				}
			break;
			
		}
		
		//Recuperation du pion de depart et mise a l'emplacement final. Creation d'une dame si besoin
		Pion nouveauPion = this.getPion(d.getPositionXOrigine(),d.getPositionYOrigine());
		
		//Retire le pion a la position d'origine et le recupere
		this.casesPlateau[d.getPositionYOrigine()][d.getPositionXOrigine()].setPionPose(null);
		
		//Met le pion a la position d'arrivee
		this.casesPlateau[d.getPositionYArrivee()][d.getPositionXArrivee()].setPionPose(nouveauPion);
		
		//Si une dame doit etre cree (cote noir)
		if(d.getPositionYArrivee()==0 && couleurPionJoueur==Pion.PION_NOIR){
			nouveauPion.promotion();
		}
		else if(d.getPositionYArrivee()==this.getTaille()-1 && couleurPionJoueur==Pion.PION_BLANC){
			nouveauPion.promotion();
		}
	}
	//====================================================================================================

	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Méthode qui permet d'obtenir un clone du plateau (nouvelle instance avec même contenu).<br>
	 * Cette méthode est utilisée par l'IA pour calculer les coups d'avances.
	 * @return Retourne une instance du plateau actuel avec la même disposition des cases et des pions.
	 */
	public Plateau clone(){
		Plateau clone=null;
		try {
			clone = new Plateau(this.casesPlateau.length,this.nbPionsPJ);
			for(int i=0;i<this.casesPlateau.length;i++){
				for(int j=0;j<this.casesPlateau.length;j++){
					clone.casesPlateau[i][j] = new Case(this.casesPlateau[i][j].getCouleur());
					if(this.casesPlateau[i][j].getPionPose()!=null){
						clone.casesPlateau[i][j].setPionPose(new Pion(this.casesPlateau[i][j].getPionPose().getCouleur()));
						clone.casesPlateau[i][j].getPionPose().setDame(this.casesPlateau[i][j].getPionPose().isDame());
					}
				}
			}
		} catch (InvalidPlateauSizeException e) {
			System.out.println(e.getMessage());
		}
		return clone;
	}
	
	/**
	 * Méthode qui place les pions par défaut.<br>
	 * Les pions blanc sont placés en haut et les noirs en bas.
	 */
	public void placerPions(){
		/*
		 * Placement des pions blancs
		 */
		int nbPionsBlancRestants=this.nbPionsPJ;
		int posY=0,posX=0;
		//Placement
		while(nbPionsBlancRestants>0 && posY<this.casesPlateau.length){
			//Parcours de la ligne
			while(posX<this.casesPlateau[posY].length && nbPionsBlancRestants>0){
				//Si on est sur une case noire, on place un pion
				if(this.casesPlateau[posY][posX].getCouleur()==Case.CASE_NOIRE){
					this.casesPlateau[posY][posX].setPionPose(new Pion(Pion.PION_BLANC));
					nbPionsBlancRestants--;
				}
				posX++;
			}
			posX=0;
			posY++;
		}
		
		/*
		 * Placement des pions noirs
		 */
		int nbPionsNoirsRestants=this.nbPionsPJ;
		posY=this.casesPlateau.length-1;
		posX=0;
		//Placement
		while(nbPionsNoirsRestants>0 && posY>=0){
			//Parcours de la ligne
			while(posX<this.casesPlateau[posY].length && nbPionsNoirsRestants>0){
				//Si on est sur une case noire, on place un pion
				if(this.casesPlateau[posY][posX].getCouleur()==Case.CASE_NOIRE){
					this.casesPlateau[posY][posX].setPionPose(new Pion(Pion.PION_NOIR));
					nbPionsNoirsRestants--;
				}
				posX++;
			}
			posX=0;
			posY--;
		}
	}
	
	/**
	 * Méthode qui permet de vérifier si un emplacement est sur le plateau.
	 * @param posX Position x de l'emplacement
	 * @param posY Position y de l'emplacement
	 * @return Retourne vrai si les coordonnées sont valides, sinon retourne faux.
	 */
	public boolean verifierEmplacement(int posX,int posY){
		boolean coordOk=posX>=0 && posY>=0 && posX<this.casesPlateau.length && posY<this.casesPlateau.length;
		//Retour
		return coordOk;
	}
	
	/**
	 * Méthode qui permet d'obtenir une représentation d'un Plateau sous forme de chaîne.<br>
	 * @return Retourne la chaîne de caractère qui représente un Plateau.
	 */
	public String toString(){
		String plateauChaine="  ";
		
		//Affiche la lettre de la colone
		for(int i=(int)'A';i<(int)'A'+this.casesPlateau.length;i++){
			plateauChaine = plateauChaine+" "+(char)i+" ";
		}
		
		//Saut de ligne
		plateauChaine = plateauChaine+"\n";
		
		//Affiche chaque case
		for(int i=0;i<this.casesPlateau.length;i++){
			plateauChaine=plateauChaine+""+i+" ";
			for(int j=0;j<this.casesPlateau[i].length;j++){
				plateauChaine = plateauChaine+this.casesPlateau[i][j];
			}
			plateauChaine = plateauChaine+"\n";
		}
		
		//Retour
		return plateauChaine;
	}
	
	/**
	 * Méthode qui sert à compter le nombre de pion sur le plateau qui
	 * possèdent la couleur passée en paramètre.
	 * @param couleurP La couleur des pions à compter.
	 * @return Retourne le nombre de pion de cette couleur.
	 */
	public int getNombrePion(int couleurP){
		int nbPion=0;
		//Parcours de toutes les cases du plateau
		for(int i=0;i<this.casesPlateau.length;i++){
			for(int j=0;j<this.casesPlateau.length;j++){
				//Si une case possede un pion
				if(this.getPion(i, j)!=null){
					//Si la couleur est celle voulue
					if(this.getPion(i, j).getCouleur()==couleurP){
						nbPion++;
					}
				}
			}
		}
		//Retour
		return nbPion;
	}
	
	/**
	 * Savoir si il reste des déplacements à effectuer sur le plateau.
	 * @return Retourne vrai si il reste des déplacement à effectuer pour les deux couleurs, retourne faux sinon.
	 */
	public boolean deplacementPossible(){
		//Verification du reste de pion et des deplacements possibles.
		boolean partieFinie=this.getTousDeplacements(Pion.PION_BLANC).size()>0 && this.getTousDeplacements(Pion.PION_NOIR).size()>0;
		
		//Retour
		return partieFinie;
	}
	//====================================================================================================
	
}