package fr.modele;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.modele.exception.InvalidDeplacementException;
import fr.modele.exception.InvalidPlateauSizeException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Cette classe représente le jeux qui peut être fait en mode console.<br>
 * La classe ne contient qu'une méthode main qui s'occupe de demander les paramètres aux joueurs et de lancer une partie.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class JeuxConsole {
	/**
	 * La méthode qui permet de jouer en console.
	 * @param args pas d'arguments dispos
	 */
	public static void main(String[] args){
		System.out.println("\tIUT VANNES\nPROJET JEUX DE DAMES - 2013\n");
		System.out.println("Mathieu THEBAUD\nNathan VILLIOT\n");
		
		//Entree console
		Scanner scan = new Scanner(System.in);
		
		//Au debut, aucun des choix n'est fait
		int choixVariante=-1;
		int choixModeJeux=-1;
		
		//Boolean
		boolean choixVarianteCorrect=false;
		boolean choixModeJeuxCorrect=false;
		boolean choixDifficulteeCorrect=false;
		boolean choixJoueurCommenceCorrect=false;
		boolean choixCouleurCorrect=false;
		
		//Valeur des parametres de la partie
		int largeurPlateau = -1;
		int nbPion = -1;
		int difficulte=-1;
		int premierJoueur=-1;
		int couleurJoueur1=-2;
		int couleurJoueur2=-2;
		String nomJoueur1=null;
		String nomJoueur2=null;
		
		/*
		 * CHOIX MODE JEUX
		 */
		do{
			try{
				System.out.println("Quel mode de jeux souhaitez vous faire ?\n(0)-Joueur contre joueur (tour à tour)\n(1)-Joueur contre ordinateur\n(2)-Ordinateur contre ordinateur (démonstration)");
				choixModeJeux = scan.nextInt();
				if(choixModeJeux!=0 && choixModeJeux!=1 && choixModeJeux!=2){
					choixModeJeuxCorrect=false;
					System.out.println("Votre choix doit etre compris entre 0 et 2");
					scan.nextLine();
				}else{
					choixModeJeuxCorrect=true;
				}
			}catch(InputMismatchException e){
				choixModeJeuxCorrect=false;
				System.out.println("Vous devez saisir un chiffre");
				scan.nextLine();
			}
		}while(!choixModeJeuxCorrect);
		
		/*
		 * VARIANTE DE JEUX
		 */
		do{
			try{
				System.out.println("\nQuelle variante voulez vous jouer ?\n(0)-8*8 cases avec 24 pions au depart\n(1)-10*10 cases avec 40 pions au depart (classique)\n(2)-12*12 cases avec 60 pions au depart");
				//Recuperation du choix
				choixVariante=scan.nextInt();
				
				//En fonction du choix
				switch(choixVariante){
				case 0:
					choixVarianteCorrect=true;
					largeurPlateau=8;
					nbPion=12;
					break;
				case 1:
					choixVarianteCorrect=true;
					largeurPlateau=10;
					nbPion=20;
					break;
				case 2:
					choixVarianteCorrect=true;
					largeurPlateau=12;
					nbPion=30;
					break;
				default:
					choixVarianteCorrect=false;
					System.out.println("Votre choix doit etre compris entre 0 et 2");
					scan.nextLine();
				}
				
			}catch(InputMismatchException e){
				choixVarianteCorrect=false;
				System.out.println("Vous devez saisir un chiffre");
				scan.nextLine();
			}
		}while(!choixVarianteCorrect);
		
		/*
		 * DIFFICULTEE IA (si choix 1 ou 2)
		 */
		if(choixModeJeux==1 || choixModeJeux==2){
			do{
				try{
					System.out.println("\nDifficulte de l'ordinateur ?\n(1)-Facile\n(2)-Moyen\n(3)-Difficile (attention, calculs longs)");
					difficulte=scan.nextInt();
					//Verification
					if(difficulte!=1 && difficulte!=2 && difficulte!=3){
						System.out.println("Votre choix doit etre compris entre 1 et 3");
						choixDifficulteeCorrect=false;
						scan.nextLine();
					}else{
						choixDifficulteeCorrect=true;
					}
				}catch(InputMismatchException e){
					choixDifficulteeCorrect=false;
					System.out.println("Vous devez saisir un chiffre");
					scan.nextLine();
				}
			}while(!choixDifficulteeCorrect);
		}
		
		/*
		 * NOM ET COULEUR JOUEUR 1 (si choix 0 ou 1)
		 */
		if(choixModeJeux==0 || choixModeJeux==1){
			do{
				try{
					System.out.println("\nQuel couleur du joueur 1 ?\n(0)-Blancs (O)\n(1)-Noirs (X)");
					couleurJoueur1=scan.nextInt();
					if(couleurJoueur1==0 || couleurJoueur1==1){
						scan.nextLine();
						if(couleurJoueur1==0)couleurJoueur1=Pion.PION_BLANC;
						else couleurJoueur1=Pion.PION_NOIR;
						choixCouleurCorrect=true;
						System.out.println("Nom du joueur ?");
						nomJoueur1 = scan.nextLine();
					}else{
						System.out.println("Votre choix doit etre compris entre 0 et 1");
						choixCouleurCorrect=false;
						scan.nextLine();
					}
				}catch(InputMismatchException e){
					choixCouleurCorrect=false;
					System.out.println("Vous devez saisir un chiffre");
					scan.nextLine();
				}
			}while(!choixCouleurCorrect);
		}
		
		/*
		 * NOM JOUEUR 2 (si choix 0)
		 */
		if(choixModeJeux==0){
			couleurJoueur2 = -couleurJoueur1;
			System.out.println("Nom du deuxième joueur ? ");
			nomJoueur2 = scan.nextLine();
		}
		
		/*
		 * COULEUR QUI COMMENCE
		 */
		do{
			try{
				System.out.println("\nQuel couleur du joueur commence ?\n(0)-Blancs (O)\n(1)-Noirs (X)");
				premierJoueur = scan.nextInt();
				if(premierJoueur==0 || premierJoueur==1){
					if(premierJoueur==0)premierJoueur=Pion.PION_BLANC;
					else premierJoueur=Pion.PION_NOIR;
					choixJoueurCommenceCorrect=true;
				}else{
					System.out.println("Votre choix doit etre compris entre 0 et 1");
					choixJoueurCommenceCorrect=false;
					scan.nextLine();
				}
			}catch(InputMismatchException e){
				choixJoueurCommenceCorrect=false;
				System.out.println("Vous devez saisir un chiffre");
				scan.nextLine();
			}
		}while(!choixJoueurCommenceCorrect);
		
		/*
		 * CREATION DES PARTIES ET JEUX
		 */
		try{
			switch(choixModeJeux){
			//Partie IA vs IA
			case 2:
				PartieIAvsIA iavsia = new PartieIAvsIA(largeurPlateau,nbPion,premierJoueur,difficulte);
				//Joue la partie
				while(!iavsia.isPartieFinie()){
					System.out.println("==================================");
					System.out.println("Au tour de : "+iavsia.getNomJoueurActuel());
					System.out.println("Déplacement choisi : "+iavsia.faireJouerIA());
					System.out.println(iavsia.getPlateau());
				}
				//Affiche les infos de fin
				System.out.println("Partie terminée\nGagnant de la partie : "+iavsia.getGagnant().getNom()+"\nNombre de tours : "+iavsia.getNbTours()+"\nTemps écoulé : "+(iavsia.getTempsEcoule()/1000)+" secondes");
				break;
			//Partie Humain vs IA
			case 1:
				PartieHumainvsIA humvsia = new PartieHumainvsIA(largeurPlateau,nbPion,premierJoueur,nomJoueur1,difficulte,couleurJoueur1);
				//Joue la partie
				while(!humvsia.isPartieFinie()){
					Joueur j = humvsia.getJoueurActuel();
					//Si c'est au joueur humain
					if(j.getClass() == Joueur.class){
						boolean deplacementValide=false;
						do{
							System.out.println("==================================");
							System.out.println("Au tour de : "+humvsia.getNomJoueurActuel());
							System.out.println(humvsia.getPlateau());
							System.out.println("Entrez votre déplacement (x1/y1-x2/y2) : ");
							try {
								Deplacement d = Deplacement.parseDeplacement(scan.nextLine());
								deplacementValide=humvsia.jouerTour(d);
								if(!deplacementValide)System.out.println("Votre déplacement n'est pas valide, recommencez");
							} catch (InvalidDeplacementException e) {
								System.out.println(e.getMessage());
								deplacementValide=false;
							}
						}while(!deplacementValide);
						
					}
					//Si c'est à l'IA
					else{
						System.out.println("==================================");
						System.out.println("Au tour de : "+humvsia.getNomJoueurActuel());
						System.out.println("Déplacement choisi : "+humvsia.faireJouerIA());
						System.out.println(humvsia.getPlateau());
					}
				}
				//Affiche les infos de fin
				System.out.println("Partie terminée\nGagnant de la partie : "+humvsia.getGagnant().getNom()+"\nNombre de tours : "+humvsia.getNbTours()+"\nTemps écoulé : "+(humvsia.getTempsEcoule()/1000)+" secondes");
				break;
				
				
			//Partie Humain vs Humain
			case 0:
				PartieHumainvsHumain humvshum = new PartieHumainvsHumain(largeurPlateau,nbPion,premierJoueur,nomJoueur1,couleurJoueur1,nomJoueur2,couleurJoueur2);
				//Joue la partie
				while(!humvshum.isPartieFinie()){
					boolean deplacementValide=false;
					do{
						System.out.println("==================================");
						System.out.println("Au tour de : "+humvshum.getNomJoueurActuel());
						System.out.println(humvshum.getPlateau());
						System.out.println("Entrez votre déplacement (x1/y1-x2/y2) : ");
						try {
							Deplacement d = Deplacement.parseDeplacement(scan.nextLine());
							deplacementValide=humvshum.jouerTour(d);
							if(!deplacementValide)System.out.println("Votre déplacement n'est pas valide, recommencez");
						} catch (InvalidDeplacementException e) {
							System.out.println(e.getMessage());
							deplacementValide=false;
						}
					}while(!deplacementValide);
				}
				break;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvalidPlateauSizeException e) {
			e.printStackTrace();
		}
		
		//Fermeture du scanner
		scan.close();
	}
	
}