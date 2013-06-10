package pda.view.dames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pda.datas.dames.Case;
import pda.datas.dames.Deplacement;
import pda.datas.dames.Partie;
import pda.datas.dames.PartieIAvsIA;
import pda.datas.dames.Pion;
import pda.datas.dames.Plateau;
import pda.datas.dames.exception.InvalidPlateauSizeException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class EcranPlateau extends JPanel{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Partie jouée
	 */
	private Partie partieJouee;
	
	/**
	 * Le panel qui affiche le plateau
	 */
	private PanelJeux affichagePlateau;
	
	/**
	 * La panel qui affiche les informations sur la parties
	 */
	private JPanel affichageInfos;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	public EcranPlateau(){
		//
		super(new BorderLayout());
		try {
			this.partieJouee = new PartieIAvsIA(10,20,-1,2);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPlateauSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.affichagePlateau = new PanelJeux();
		this.affichageInfos = new JPanel();
		JButton b = new JButton("Lancer");
		this.affichageInfos.add(b);
		EcranPlateau.this.partieJouee.getPlateau().setPion(2,5, new Pion(Pion.PION_BLANC));
		b.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent clic) {
				final Deplacement d = new Deplacement(1,6,3,4);
				System.out.println("Marche ? "+EcranPlateau.this.partieJouee.getPlateau().effectuerDeplacement(d,Pion.PION_NOIR));
				System.out.println(d.getPointPionPris());
				System.out.println(d.isPriseEffectuee());
				Thread t = new Thread(){
					public void run(){
						System.out.println("Lance le thread");
						EcranPlateau.this.affichagePlateau.faireAnimationDeplacement(d);
					}
				};
				t.start();
			}
			
		});
		this.add(this.affichagePlateau, BorderLayout.CENTER);
		this.add(this.affichageInfos,BorderLayout.NORTH);
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Panel qui afficher le plateau de jeux.<br>
	 * @author Mathieu
	 *
	 */
	public class PanelJeux extends JPanel{
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
		 * Image d'une case blanche
		 */
		private Image caseBlanche;
		
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
		 * Constructeur du panel de jeux.<br>
		 * Charge les textures du plateau et prend la référence du plateau de jeux.<br>
		 * <strong>Attention : </strong>la construction du PanelJeux doit se faire après la récupération de la partie.
		 */
		public PanelJeux(){
			//Chargement textures
			try{
				this.pionNoir = ImageIO.read(new File("./data/img/dames/pion_noir.png"));
				this.pionBlanc = ImageIO.read(new File("./data/img/dames/pion_blanc.png"));
				this.caseBlanche = ImageIO.read(new File("./data/img/dames/case_blanche.png"));
				this.caseNoire = ImageIO.read(new File("./data/img/dames/case_noire.png"));
				this.dameBlanche = ImageIO.read(new File("./data/img/dames/dame_blanche.png"));
				this.dameNoire = ImageIO.read(new File("./data/img/dames/dame_noire.png"));
			}catch(Exception e){
				System.err.println("Erreur de chargement d'image : "+e.getMessage());
			}
			//Récupération du plateau
			this.plateauJeux = EcranPlateau.this.partieJouee.getPlateau();
		}
		
		/**
		 * Redéfiniton de la méthode paintComponent du JPanel pour pouvoir
		 * afficher les images pour les pions ainsi que pour les cases.
		 */
		public void paintComponent(Graphics g){
			//Fond blanc
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			//Calcul de la taille d'une case
			this.largeurCase = this.getWidth()/this.plateauJeux.getTaille();
			this.hauteurCase = this.getHeight()/this.plateauJeux.getTaille();
			//Calcul du décalage
			this.decalageLargeur = this.getWidth()%this.plateauJeux.getTaille()/2;
			this.decalageHauteur = this.getHeight()%this.plateauJeux.getTaille()/2;
			
			//Dessin du plateau et des pions
			for(int y=0;y<this.plateauJeux.getTaille();y++){
				for(int x=0;x<this.plateauJeux.getTaille();x++){
					Image textureCase=null;
					//Determine la texture en fonction de la couleur
					if(this.plateauJeux.getCase(x,y).getCouleur()==Case.CASE_NOIRE)textureCase=this.caseNoire;
					else textureCase=this.caseBlanche;
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
					if(this.deplacementEnCours){
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
					
					g.drawString(x+","+y,this.decalageLargeur+x*this.largeurCase,this.decalageHauteur+y*this.hauteurCase+10);
				}
			}
			
			//System.out.println("Largeur : "+this.largeurCase+"\nHauteur : "+this.hauteurCase+"\nDecal larg "+this.decalageLargeur+"\nDecal haut "+this.decalageHauteur);
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
			return pointGrille;
		}
		
		/**
		 * Méthode qui sert à faire une animation de déplacement d'un pion.<br>
		 * Cette méthode est à lancer dans un nouveau Thread pour éviter de figer l'affichage.<br>
		 * <strong>Attention : </strong> le déplacement dont on veut faire l'animation doit être valide et doit avoir déjà été effectué.
		 * @param d Le déplacement dont on veut faire l'animation.
		 * @throws NullPointerException si le déplacement n'est pas valide ou qu'il n'a pas été fait
		 */
		public void faireAnimationDeplacement(Deplacement d)throws NullPointerException{
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
			double coefX = ((this.decalageLargeur+this.largeurCase*d.getPositionXArrivee()*1.0)-(this.decalageLargeur+this.largeurCase*d.getPositionXOrigine()*1.0))/PanelJeux.INTERVALLE_DEPLACEMENT/distance;
			double coefY = ((this.decalageHauteur+this.hauteurCase*d.getPositionYArrivee()*1.0)-(this.decalageHauteur+this.hauteurCase*d.getPositionYOrigine()*1.0))/PanelJeux.INTERVALLE_DEPLACEMENT/distance;
			
			//Création du point de déplacement
			this.pointDeplacement = new Point(this.decalageLargeur+this.largeurCase*d.getPositionXOrigine(),this.decalageHauteur+this.hauteurCase*d.getPositionYOrigine());
			
			//Création des coordonnées temporaires (double pour plus de précision) 
			double nouveauX=this.pointDeplacement.x;
			double nouveauY=this.pointDeplacement.y;
			
			//Boucle de l'animation
			for(int i=0;i<(PanelJeux.INTERVALLE_DEPLACEMENT*distance);i++){
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
	}
	//====================================================================================================
	
}