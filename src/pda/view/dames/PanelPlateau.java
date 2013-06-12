package pda.view.dames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pda.datas.dames.Case;
import pda.datas.dames.Deplacement;
import pda.datas.dames.Partie;
import pda.datas.dames.Pion;
import pda.datas.dames.Plateau;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Ce panel est le panel qui va permettre d'afficher le plateau de jeu.<br>
 * Le panel doit être instancié avec une partie créée (donc non null).
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class PanelPlateau extends JPanel{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Largeur d'une case du plateau (à actualiser à chaque affichage)
	 */
	private int largeurCase;
	
	/**
	 * Hauteur d'une case du plateau (à actualiser à chaque affichage)
	 */
	private int hauteurCase;
	
	/**
	 * Décalage dans la largeur (pour centrer si la division n'est pas exacte)
	 */
	private int decalageLargeur;
	
	/**
	 * Décalage dans la hauteur (pour centrer si la division n'est pas exacte)
	 */
	private int decalageHauteur;
	
	/**
	 * Image d'un pion noir
	 */
	private Image pionNoir;
	
	/**
	 * Image d'un pion blanc
	 */
	private Image pionBlanc;

	/**
	 * Image d'une case blanche selectionnée
	 */
	private Image caseBlancheSelectionnee;
	
	/**
	 * Image d'une case blanche
	 */
	private Image caseBlanche;
			
	/**
	 * Image d'une case noire selectionnée
	 */
	private Image caseNoireSelectionnee;
	
	/**
	 * Image d'une case noire
	 */
	private Image caseNoire;
	
	/**
	 * Image d'une dame blanche
	 */
	private Image dameBlanche;
	
	/**
	 * Image d'une dame noire
	 */
	private Image dameNoire;
	
	/**
	 * Plateau affiché dans le panel
	 */
	private Plateau plateauJeux;
	
	/**
	 * Pion qui se fait déplacer
	 */
	private Pion pionDeplace;
	
	/**
	 * Pour savoir si il y a un déplacement en cours
	 */
	private boolean deplacementEnCours;
	
	/**
	 * Point de déplacement du pion
	 */
	private Point pointDeplacement;
	
	/**
	 * Nombre de point intermédiaire dans l'animation de déplacement (d'une case à une autre)
	 */
	private final static double INTERVALLE_DEPLACEMENT=60.0;
	
	/**
	 * L'écran de jeu actuel (celui dans lequel le JPanel est affiché)
	 */
	private EcranJeu ecranActuel;
	
	/**
	 * La partie actuellement joué dans l'écran de jeu.
	 */
	private Partie partieJouee;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur du panel de jeux.<br>
	 * Charge les textures du plateau et prend la référence de l'écran de jeu.
	 * @param ecranActuelP l'écran de jeu dans lequel est affiché le panel plateau.
	 */
	public PanelPlateau(EcranJeu ecranActuelP){
		//Récupération de l'écran
		this.ecranActuel = ecranActuelP;
		//Chargement textures
		try{
			this.pionNoir = ImageIO.read(new File("./data/img/dames/pion_noir.png"));
			this.pionBlanc = ImageIO.read(new File("./data/img/dames/pion_blanc.png"));
			this.caseBlanche = ImageIO.read(new File("./data/img/dames/case_blanche.png"));
			this.caseNoire = ImageIO.read(new File("./data/img/dames/case_noire.png"));
			this.dameBlanche = ImageIO.read(new File("./data/img/dames/dame_blanche.png"));
			this.dameNoire = ImageIO.read(new File("./data/img/dames/dame_noire.png"));
			this.caseBlancheSelectionnee = ImageIO.read(new File("./data/img/dames/case_blanche_selec.png"));
			this.caseNoireSelectionnee = ImageIO.read(new File("./data/img/dames/case_noire_selec.png"));
		}catch(Exception e){
			System.err.println("Erreur de chargement d'image : "+e.getMessage());
		}
		//Récupération du plateau
		this.plateauJeux = this.ecranActuel.getPartieJouee().getPlateau();
		this.partieJouee = this.ecranActuel.getPartieJouee();
	}
	//====================================================================================================
	
	
	//================================================JEU================================================
	/**
	 * Redéfiniton de la méthode paintComponent du JPanel pour pouvoir
	 * afficher les images pour les pions ainsi que pour les cases.
	 */
	public void paintComponent(Graphics g){
		//Fond blanc
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		/*
		 * Ces calculs servent à centrer la grille par rapport au panel
		 */
		//Calcul de la taille d'une case
		this.largeurCase = this.getWidth()/this.plateauJeux.getTaille();
		this.hauteurCase = this.getHeight()/this.plateauJeux.getTaille();
		//Calcul du décalage
		this.decalageLargeur = this.getWidth()%this.plateauJeux.getTaille()/2;
		this.decalageHauteur = this.getHeight()%this.plateauJeux.getTaille()/2;
		
		/*
		 * Dessin de chaque case du plateau avec le pion quelle contient
		 */
		//Dessin du plateau et des pions
		for(int y=0;y<this.plateauJeux.getTaille();y++){
			for(int x=0;x<this.plateauJeux.getTaille();x++){
				Image textureCase=null;
				//Determine la texture en fonction de la couleur
				if(this.plateauJeux.getCase(x,y).getCouleur()==Case.CASE_NOIRE){
					if(this.plateauJeux.getCase(x, y).isSelectionnee())textureCase=this.caseNoireSelectionnee;
					else textureCase=this.caseNoire;
				}
				else{
					if(this.plateauJeux.getCase(x, y).isSelectionnee())textureCase=this.caseBlancheSelectionnee;
					else textureCase=this.caseBlanche;
				}
				//Dessine
				g.drawImage(textureCase,this.decalageLargeur+x*this.largeurCase,this.decalageHauteur+y*this.hauteurCase,this.largeurCase,this.hauteurCase,null);
				
				//Dessin des pions
				if(this.plateauJeux.getPion(x, y)!=null){
					Pion pionPose = this.plateauJeux.getPion(x, y);
					Image texturePion = null;
					//Determine la texture en fonction de la dame et de la couleur
					if(!pionPose.isDame()){
						if(pionPose.getCouleur()==Pion.PION_BLANC)texturePion=this.pionBlanc;
						else texturePion=this.pionNoir;
					}else{
						if(pionPose.getCouleur()==Pion.PION_BLANC)texturePion=this.dameBlanche;
						else texturePion=this.dameNoire;
					}
					//Dessine
					g.drawImage(texturePion,this.decalageLargeur+x*this.largeurCase,this.decalageHauteur+y*this.hauteurCase,this.largeurCase,this.hauteurCase,null);
				}
				
				//Dessin du pion en déplacement
				if(this.deplacementEnCours && this.pionDeplace!=null && this.pointDeplacement!=null){
					Image texturePion = null;
					//Determine la texture en fonction de la dame et de la couleur
					if(!this.pionDeplace.isDame()){
						if(this.pionDeplace.getCouleur()==Pion.PION_BLANC)texturePion=this.pionBlanc;
						else texturePion=this.pionNoir;
					}else{
						if(this.pionDeplace.getCouleur()==Pion.PION_BLANC)texturePion=this.dameBlanche;
						else texturePion=this.dameNoire;
					}
					//Dessine
					g.drawImage(texturePion,this.pointDeplacement.x,this.pointDeplacement.y,this.largeurCase,this.hauteurCase,this);
				}
				
				//Dessine les coordonnées de la case(sert lors des test)
				//g.drawString(x+","+y,this.decalageLargeur+x*this.largeurCase,this.decalageHauteur+y*this.hauteurCase+10);
			}
		}
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	/**
	 * Méthode qui sert à savoir si une animation de déplacement est en cours.
	 * @return vrai si un déplacement est en cours, sinon retourne faux.
	 */
	public boolean isDeplacementEnCours(){
		return this.deplacementEnCours;
	}
	
	/**
	 * Obtenir les coordonnées d'une case de la grille en fonction des coordonnées d'un clic
	 * sur le JPanel.
	 * @param p le point du clic sur le JPanel (coordonnées absolues)
	 * @return le point qui contient les coordonnées de la case de la grille sur laquelle on a cliqué.<br>
	 * Retourne null si le clic est en dehors de la grille.
	 */
	public Point getCoordonneeGrille(Point p){
		Point pointGrille=null;
		//Calcul coordonnées (opération inverse de celles faîtes pour afficher une case)
		int x = (p.x-this.decalageLargeur)/this.largeurCase;
		int y = (p.y-this.decalageHauteur)/this.hauteurCase;
		//Création point si coordonnées valides
		if(this.plateauJeux.verifierEmplacement(x, y))pointGrille = new Point(x,y);
		return pointGrille;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	/**
	 * Va mettre en évidence sur la grille tous les déplacements possible à partir d'un piont selectionné.<br>
	 * Cette méthode ne deselectionnera pas les cases précedement selectionnées.
	 * @param pionSelec les coordonnées du pion selectionné
	 */
	public void afficherDeplacementPossible(Point pionSelec){
		//Si il y a un pion
		if(this.plateauJeux.getPion(pionSelec.x, pionSelec.y)!=null){
			//Si la couleur est celle jouée
			if(this.plateauJeux.getPion(pionSelec.x, pionSelec.y).getCouleur()==this.partieJouee.getCouleurJouee()){
				//récupération des déplacements possibles et sélection des cases d'arrivées
				ArrayList<Deplacement> deplacementsPossible = this.plateauJeux.getDeplacementsPion(pionSelec.x, pionSelec.y);
				ArrayList<Deplacement> tousDeplacementsTries = Plateau.trierDeplacements(this.plateauJeux.getTousDeplacements(this.partieJouee.getCouleurJouee()));
				for(Deplacement d : deplacementsPossible){
					if(tousDeplacementsTries.contains(d))this.plateauJeux.getCase(d.getPositionXArrivee(), d.getPositionYArrivee()).setSelectionnee(true);
				}
			}
		}
		//Actualise l'affichage
		this.repaint();
	}
	
	/**
	 * Cette méthode va permettre de mettre en évidence tous les pions pour lesquels un déplacement est possible.
	 */
	public void afficherTousPionDeplacables(){
		//On enlève les selections précedentes
		this.plateauJeux.deselectionnerToutesCases();
		
		//Récupération des déplacements possibles
		ArrayList<Deplacement> deplacementsPossible = this.plateauJeux.getTousDeplacements(this.partieJouee.getCouleurJouee());
		deplacementsPossible = Plateau.trierDeplacements(deplacementsPossible);
		
		//On selectionne tous les cases dont partent les déplacements
		for(Deplacement d : deplacementsPossible){
			this.plateauJeux.getCase(d.getPositionXOrigine(), d.getPositionYOrigine()).setSelectionnee(true);
		}
		
		//Actualise l'affichage
		this.repaint();
	}
	//====================================================================================================
	
	
	//=============================================ANIMATION=============================================
	/**
	 * Méthode qui sert à faire une animation de déplacement d'un pion.<br>
	 * Cette méthode est à lancer dans un nouveau Thread pour éviter de figer l'affichage.<br>
	 * <strong>Attention : </strong> le déplacement dont on veut faire l'animation doit être valide et doit avoir déjà été effectué.
	 * @param d Le déplacement dont on veut faire l'animation.
	 * @throws NullPointerException si le déplacement n'est pas valide ou qu'il n'a pas été fait
	 */
	public synchronized void faireAnimationDeplacement(Deplacement d)throws NullPointerException{
		//Début déplacement
		this.deplacementEnCours=true;
		
		//Récupération du pion déplacé (il est à l'arrivée car le déplacement a déjà été fait)
		this.pionDeplace = this.plateauJeux.getPion(d.getPositionXArrivee(), d.getPositionYArrivee());
		
		//Si il y a eu une prise
		if(d.isPriseEffectuee()){
			//Replace le pion pour l'animation
			this.plateauJeux.setPion(d.getPointPionPris().x,d.getPointPionPris().y,d.getPionPris());
		}
		
		//Suppression du pion à l'arrivée (cohérence dans l'animation)
		this.plateauJeux.setPion(d.getPositionXArrivee(), d.getPositionYArrivee(), null);
		
		//Distance du déplacement
		int distance = d.getDistanceDeplacement();
		
		//Calcul des coefficients pour l'animation (on utilise des doubles, sinon l'animation ne sera pas précise)
		double coefX = ((this.decalageLargeur+this.largeurCase*d.getPositionXArrivee()*1.0)-(this.decalageLargeur+this.largeurCase*d.getPositionXOrigine()*1.0))/PanelPlateau.INTERVALLE_DEPLACEMENT/distance;
		double coefY = ((this.decalageHauteur+this.hauteurCase*d.getPositionYArrivee()*1.0)-(this.decalageHauteur+this.hauteurCase*d.getPositionYOrigine()*1.0))/PanelPlateau.INTERVALLE_DEPLACEMENT/distance;
		
		//Création du point de déplacement
		this.pointDeplacement = new Point(this.decalageLargeur+this.largeurCase*d.getPositionXOrigine(),this.decalageHauteur+this.hauteurCase*d.getPositionYOrigine());
		
		//Création des coordonnées temporaires (double pour plus de précision) 
		double nouveauX=this.pointDeplacement.x;
		double nouveauY=this.pointDeplacement.y;
		
		//Boucle de l'animation
		for(int i=0;i<(PanelPlateau.INTERVALLE_DEPLACEMENT*distance);i++){
			//Calcul des nouvelles coordonnées
			nouveauX = nouveauX + coefX;
			nouveauY = nouveauY + coefY;
			//Changement coordonnées
			this.pointDeplacement.x = (int)(nouveauX);
			this.pointDeplacement.y = (int)(nouveauY);
			this.repaint();
			//Pause et réaffichage
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.err.println("Erreur de Thread dans la boucle de l'animation de déplacement : "+e.getMessage());
			}
		}
					
		//Remise du pion à l'arrivée
		this.plateauJeux.setPion(d.getPositionXArrivee(), d.getPositionYArrivee(),this.pionDeplace);
		//Si il y a eu une prise
		if(d.isPriseEffectuee()){
			//On enlève le pion pris
			this.plateauJeux.setPion(d.getPointPionPris().x,d.getPointPionPris().y,null);
		}
		//Réaffichage
		this.repaint();
		
		//Fin déplacement
		this.deplacementEnCours=false;
	}
	//====================================================================================================
	
}