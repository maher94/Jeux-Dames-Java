package fr.utilitaire;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Generateur de classe pour le logiciel.<br>
 * Classe temporaire.<br>
 * @author Mathieu
 *
 */
public class GenerateurJava {
	/**
	 * Nombre de tirets de separation.
	 */
	private static final int nbTiret=100;
	
	/**
	 * Texte pour constructeur
	 */
	private static final String texteConstructeur="CONSTRUCTEUR(S)";
	
	/**
	 * Texte pour les getter
	 */
	private static final String texteGetter="ACCESSEUR(S)";
	
	/**
	 * Texte pour les setter
	 */
	private static final String texteSetter="MUTATEUR(S)";
	
	/**
	 * Texte pour les attributs
	 */
	private static final String texteAttribut="ATTRIBUT(S)";
	
	/**
	 * Texte pour les autres methodes de la classe
	 */
	private static final String texteMethode="AUTRE(S) METHODE(S)";
	
	/**
	 * Texte pour du code supplementaire
	 */
	private static final String texteSupp="";
	
	/**
	 * Caractere de separation
	 */
	private static final String separateur="=";
	
	/**
	 * Generer une classe pour un nom spécifique.
	 * @param name Le nom de la classe.
	 * @return Retourne le texte de la classe
	 */
	public static String generateClass(String name,String pack,boolean codeSpecial){
		//Entete
		String codeClasse="package "+pack+";\n\n\n" +
				"/**\n" +
				" * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>\n" +
				" * <br>\n"+
				" * @author Mathieu THEBAUD\n" +
				" * @author Nathan VILLIOT\n" +
				" * @version 1.00\n" +
				" * @since 1.00\n" +
				" */\n\n" +
				"public class "+name+" {";
		//Futur emplacements
		codeClasse = codeClasse + GenerateurJava.generateEmplacement(GenerateurJava.texteAttribut);
		codeClasse = codeClasse + GenerateurJava.generateEmplacement(GenerateurJava.texteConstructeur);
		codeClasse = codeClasse + GenerateurJava.generateEmplacement(GenerateurJava.texteGetter);
		codeClasse = codeClasse + GenerateurJava.generateEmplacement(GenerateurJava.texteSetter);
		codeClasse = codeClasse + GenerateurJava.generateEmplacement(GenerateurJava.texteMethode);
		if(codeSpecial)codeClasse = codeClasse + GenerateurJava.generateEmplacement(GenerateurJava.texteSupp);
		
		//Fin classe
		codeClasse = codeClasse+"\n\t\n}";
		//Retour
		return codeClasse;
	}
	
	/**
	 * Generer un futur emplacement
	 * @return Retourne le code generé.
	 */
	public static String generateEmplacement(String nom){
		String codeEmplacement="\n\t";
		//Generation
		codeEmplacement=codeEmplacement+"\n\t\n\t//";
		int longueurMoitiee = (GenerateurJava.nbTiret-nom.length())/2;
		for(int i=0;i<longueurMoitiee;i++)codeEmplacement = codeEmplacement + GenerateurJava.separateur;
		codeEmplacement = codeEmplacement+nom;
		for(int i=0;i<longueurMoitiee;i++)codeEmplacement = codeEmplacement + GenerateurJava.separateur;
		codeEmplacement=codeEmplacement+"\n\t\n\t//";
		for(int i=0;i<GenerateurJava.nbTiret;i++)codeEmplacement = codeEmplacement + GenerateurJava.separateur;
		//Retour
		return codeEmplacement;
	}
	
	/**
	 * Générer les méthodes pour une arraylist.
	 * @param nom Nom de l'arraylist
	 * @return
	 */
	public static String generateMethodesArray(String nom,String objet){
		//Majuscule sur premiere letter
		//Premiere lettre
		int prem = nom.codePointAt(0);
		String nomMaj = nom.replaceFirst(""+(char)prem,""+(char)(prem-32));
		//Taille
		String codeMethodes="\tpublic int getTaille" +nomMaj+"() {\n"+
				"\t\treturn this."+nom+".size();\n\t}";
		//Ajout
		codeMethodes = codeMethodes+"\n\tpublic void ajouter"+objet+"("+objet+" nouveau"+objet+") {\n" +
				"\t\tthis."+nom+".add(nouveau"+objet+");\n\t}";
		//Consulter
		codeMethodes = codeMethodes+"\n\tpublic "+objet+" get"+objet+"(int index) {\n" +
				"\t\t" +objet+" objetConsulte=null;\n"+
				"\t\tif(index>=0 && index<this."+nom+".size())objetConsulte=this."+nom+".get(index);\n\t\treturn objetConsulte;\n\t}";
		//Supprimer
		codeMethodes = codeMethodes+"\n\tpublic void supprimer"+objet+"(int index) {\n" +
				"\t\tif(index>=0 && index<this."+nom+".size())this."+nom+".remove(index);\n\t}";
		//Retour
		return codeMethodes;
	}
	
	public static String generateButtonListener(String nom){
		//Premiere lettre
		int prem = nom.codePointAt(0);
		String nomMaj = nom.replaceFirst(""+(char)prem,""+(char)(prem-32));
		String codeEcoute="\tpublic class Ecouteur"+nomMaj+" implements ActionListener {" +
				"\n\t\tpublic void actionPerformed(ActionEvent clic) {" +
				"\n\t\t}" +
				"\n\t}";
		//retour
		return codeEcoute;
	}
	
	public static String generateCommentToString(String nomClasse){
		String commentaire = "\t/**\n" +
				"\t * Méthode qui permet d'obtenir une représentation d'un "+nomClasse+" sous forme de chaîne.<br>\n" +
				"\t * @return Retourne la chaîne de caractère qui représente un "+nomClasse+".\n" +
				"\t */";
		return commentaire;
	}
	
	public static void genererFichier(String pack,String texte,String nom){
		String[] dossierPack = pack.split("\\.");
		System.out.println("Package : "+pack+" Séparation "+dossierPack.length);
		String adresse = "./src";
		for(String nomPack : dossierPack){
			adresse = adresse+"/"+nomPack;
			File dossier  = new File(adresse);
			System.out.println(dossier.getAbsolutePath());
			if(!dossier.exists())dossier.mkdir();
		}
		//Fichier
		File source = new File(adresse+"/"+nom+".java");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(source));
			bw.write(texte);
			bw.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	/**
	 * Generer une classe
	 */
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		boolean continuer=false;
		do{
			//Choix
			System.out.println("(0)-CLASSE\n(1)-EMPLACEMENT\n(2)METHODES LISTE\n(3)BOUTON\n(4)TOSTRING");
			int choix = scan.nextInt();
			scan.nextLine();
			if(choix==0){
				System.out.println("Nom de la classe a generer ?");
				String nom = scan.nextLine();
				System.out.println("Package de la classe ?");
				String pack = scan.nextLine();
				GenerateurJava.genererFichier(pack,GenerateurJava.generateClass(nom,pack,false),nom);
			}
			if(choix==1){
				System.out.println("Nom de l'emplacement ?");
				String nom = scan.nextLine();
				System.out.println(GenerateurJava.generateEmplacement(nom));
			}
			if(choix==2){
				System.out.println("Nom de la liste ?");
				String nom = scan.nextLine();
				System.out.println("Type d'objets contenus dans la liste ?");
				String obj = scan.nextLine();
				System.out.println(GenerateurJava.generateMethodesArray(nom,obj));
			}
			if(choix==3){
				System.out.println("Nom du bouton ?");
				String nom = scan.nextLine();
				System.out.println(GenerateurJava.generateButtonListener(nom));
			}
			if(choix==4){
				System.out.println("Nom de classe ?");
				String nom = scan.nextLine();
				System.out.println(GenerateurJava.generateCommentToString(nom));
			}
			System.out.println("Autre génération ?\no ou n ?");
			continuer = scan.nextLine().contains("o");
		}while(continuer);
		scan.close();
	}
}
