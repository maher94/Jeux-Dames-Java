package pda.control.dames;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pda.datas.dames.Deplacement;
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
			
			Plateau plateau = this.ecranJeu.getPartieJouee().getPlateau();
			//Récupération du point de la grille selectionné
			Point pointGrille = this.ecranJeu.getAffichagePlateau().getCoordonneeGrille(clic.getPoint());
			
			//Si le point existe
			if(pointGrille!=null){
				
				//Le fonctionnement du déplacement se faire en fonction du mode de jeux 
				
				//Partie humain contre humain
				if(this.partieJouee instanceof PartieHumainvsHumain){
					//Si on clic sur une case avec un pion de la couleur dont la personne joue
					if(plateau.getPion(pointGrille.x,pointGrille.y)!=null && plateau.getPion(pointGrille.x, pointGrille.y).getCouleur() == this.partieJouee.getCouleurJouee()){
						//Définition du point de départ
						this.departDeplacement = pointGrille;
						//Affichage des déplacements possibles
						this.ecranJeu.getAffichagePlateau().afficherDeplacementPossible(pointGrille);
					}
					
					//Si on clic sur une case sans pion
					if(plateau.getPion(pointGrille.x,pointGrille.y)==null && this.departDeplacement!=null){
						this.arriveeDeplacement=pointGrille;
						
						//On essaye de faire un déplacemeent
						Deplacement deplacementIndique=new Deplacement(this.departDeplacement.x,this.departDeplacement.y,this.arriveeDeplacement.x,this.arriveeDeplacement.y);
						
						
						//Peut importe le fonctionement, on réinitialise
						this.departDeplacement = null;
						this.arriveeDeplacement =null;
						plateau.deselectionnerToutesCases();
					}
				}
				
				//Partie humain contre IA
				if(this.partieJouee instanceof PartieHumainvsIA){
					PartieHumainvsIA partie = (PartieHumainvsIA)this.partieJouee;
					//Si c'est le joueur qui joue
					if(this.partieJouee.getJoueurActuel()==partie.getJoueurHumain()){
						this.ecranJeu.getAffichagePlateau().afficherTousPionDeplacables();
						
						//Si on clic sur une case avec un pion de la couleur dont la personne joue
						if(plateau.getPion(pointGrille.x,pointGrille.y)!=null && plateau.getPion(pointGrille.x, pointGrille.y).getCouleur() == this.partieJouee.getCouleurJouee()){
							//Définition du point de départ
							this.departDeplacement = pointGrille;
							//Affichage des déplacements possibles
							this.ecranJeu.getAffichagePlateau().afficherDeplacementPossible(pointGrille);
						}
						
						//Si on clic sur une case sans pion
						if(plateau.getPion(pointGrille.x,pointGrille.y)==null && this.departDeplacement!=null){
							this.arriveeDeplacement=pointGrille;
							
							//On essaye de faire un déplacemeent
							Deplacement deplacementIndique=new Deplacement(this.departDeplacement.x,this.departDeplacement.y,this.arriveeDeplacement.x,this.arriveeDeplacement.y);
							
							//On essaye de faire jouer
							if(partie.jouerTour(deplacementIndique)){
								//Si ça marche, on fait l'animation
								AnimationDeplacement animJoueur = new AnimationDeplacement(deplacementIndique);
								animJoueur.start();
								//Attend la fin du Thread
								try {
									animJoueur.join();
								} catch (InterruptedException e) {
									System.err.println("Problème Thread d'animation d'un déplacement "+e.getMessage());
								}
							}
							
							//Peut importe le fonctionement, on réinitialise
							this.departDeplacement = null;
							this.arriveeDeplacement =null;
							plateau.deselectionnerToutesCases();
						}
					}
					//Si c'est le tour de l'IA
					while(this.partieJouee.getJoueurActuel()!=partie.getJoueurHumain()){
						Deplacement deplacementIA = partie.faireJouerIA();
						//Animation
						AnimationDeplacement animIA = new AnimationDeplacement(deplacementIA);
						animIA.start();
						try{
						animIA.join();
						} catch (InterruptedException e) {
							System.err.println("Problème Thread d'animation d'un déplacement "+e.getMessage());
						}
					}
				}
				
				
				
			}
		}
	}

	//Pas de redefinition des méthodes
	public void mouseEntered(MouseEvent clic) {}

	public void mouseExited(MouseEvent clic) {}

	public void mousePressed(MouseEvent clic) {}

	public void mouseReleased(MouseEvent clic) {}
	
	//Thread de lancement de l'animation
	public class AnimationDeplacement extends Thread{
		private Deplacement deplacementAAnimer;
		public AnimationDeplacement(Deplacement d){
			deplacementAAnimer=d;
		}
		public void run(){
			ControleurJeu.this.ecranJeu.getAffichagePlateau().faireAnimationDeplacement(deplacementAAnimer);
		}
	}
	//====================================================================================================
}