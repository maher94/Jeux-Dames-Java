package pda.control.dames;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pda.datas.dames.Deplacement;
import pda.datas.dames.IA;
import pda.datas.dames.Joueur;
import pda.datas.dames.Partie;
import pda.datas.dames.PartieHumainvsHumain;
import pda.datas.dames.PartieHumainvsIA;
import pda.datas.dames.Plateau;
import pda.view.dames.EcranJeu;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class ControleurJeu implements MouseListener{
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Ecran de jeu
	 */
	private EcranJeu ecranJeu;
	
	/**
	 * Partie en cours
	 */
	private Partie partieJouee;
	
	/**
	 * Point de départ du déplacement
	 */
	private Point departDeplacement;
	
	/**
	 * Point d'arrivée du déplacement
	 */
	private Point arriveeDeplacement;
	
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur du controleur de jeux.
	 * @param ecranJeuP l'écran de jeux
	 */
	public ControleurJeu(EcranJeu ecranJeuP){
		this.ecranJeu = ecranJeuP;
		this.partieJouee = this.ecranJeu.getPartieJouee();
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	
	//==========================================ECOUTEUR SOURIS==========================================
	/**
	 * Lorsque l'on clic avec la souris
	 */
	public void mouseClicked(MouseEvent clic) {
		
		//Si le clic vient du plateau de jeu
		if(clic.getSource()==this.ecranJeu.getAffichagePlateau()){
			Plateau plateau = this.partieJouee.getPlateau();
			//Récupération du point de la grille selectionné
			Point pointGrille = this.ecranJeu.getAffichagePlateau().getCoordonneeGrille(clic.getPoint());
			
			//Si le point existe
			if(pointGrille!=null){
				
				//Si c'est au tour du joueur
				if(!(this.partieJouee.getJoueurActuel() instanceof IA)){
					Joueur humain = this.partieJouee.getJoueurActuel();
					//Affiche tous les déplacements possibles
					this.ecranJeu.getAffichagePlateau().afficherTousPionDeplacables();
					
					//Si la case est un possède un pion du joueur
					if(plateau.getPion(pointGrille.x,pointGrille.y)!=null && plateau.getPion(pointGrille.x, pointGrille.y).getCouleur()==humain.getCouleur()){
						this.departDeplacement = pointGrille;
						//Affiche tous les déplacements possible
						this.ecranJeu.getAffichagePlateau().afficherDeplacementPossible(pointGrille);
					}
					//Si la case est vide
					if(plateau.getPion(pointGrille.x, pointGrille.y)==null){
						this.arriveeDeplacement = pointGrille;
					}
					
				}				
				//Après la récupération, on tente de jouer un tour
				JouerTour tour = new JouerTour();
				tour.start();
			}
		}
	}
	
	/**
	 * Méthode qui sert à lancer la partie.<br>
	 * Cette méthode sert lors des parties IA contre IA.
	 */
	public void lancerPartie(){
		JouerTour jeu = new JouerTour();
		jeu.start();
	}
	
	/**
	 * Méthode qui va faire jouer le joueur puis l'IA.<br>
	 * Le tout se fait dans une méthode synchronized pour ne pas avoir de conflit pendant l'animation.
	 */
	public synchronized void jouerTour(){
		//Récupération du joueur actuel
		Joueur j = this.partieJouee.getJoueurActuel();
		
		//Si c'est l'IA qui joue
		if(j instanceof IA){
			Deplacement deplacement = this.partieJouee.faireJouerIA();
			this.ecranJeu.getAffichagePlateau().faireAnimationDeplacement(deplacement);
		}
		//Si c'est un joueur
		else{
			//Si l'arrivée et le départ sont définis
			if(this.departDeplacement!=null && this.arriveeDeplacement!=null){
				Deplacement deplacement = new Deplacement(this.departDeplacement.x,this.departDeplacement.y,this.arriveeDeplacement.x,this.arriveeDeplacement.y);
				boolean marche = this.partieJouee.jouerTour(deplacement);
				System.out.println(deplacement+" marche ? "+marche);
				if(marche){
					//Déselectionne tous les pions
					this.partieJouee.getPlateau().deselectionnerToutesCases();
					this.ecranJeu.getAffichagePlateau().faireAnimationDeplacement(deplacement);
				}
			}
		}
		
		//Si la partie n'est pas fini est que c'est encore le tour de l'IA
		if(!this.partieJouee.isPartieFinie() && this.partieJouee.getJoueurActuel() instanceof IA){
			JouerTour jeu = new JouerTour();
			jeu.start();
		}
	}
	
	//Pas de redefinition des méthodes
	public void mouseEntered(MouseEvent clic) {}

	public void mouseExited(MouseEvent clic) {}

	public void mousePressed(MouseEvent clic) {}

	public void mouseReleased(MouseEvent clic) {}
	
	/**
	 * Thread pour jouer un tour de jeu.<br>
	 * Ce Thread est obligatoire car sinon il y a des problèmes de synchronisation à cause des animations de déplacement.
	 * @author Mathieu THEBAUD
	 *
	 */
	public class JouerTour extends Thread{
		public JouerTour(){
			super("Jouer tour");
		}
		public void run(){
			ControleurJeu.this.jouerTour();
		}
	}
	
	public class LancerAnimation extends Thread{
		Deplacement deplacement;
		public LancerAnimation(Deplacement deplacementP){
			this.deplacement = deplacementP;
		}
		public void run(){
			ControleurJeu.this.ecranJeu.getAffichagePlateau().faireAnimationDeplacement(deplacement);
		}
	}
	//====================================================================================================
}