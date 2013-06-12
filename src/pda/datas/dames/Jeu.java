package pda.datas.dames;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;

import pda.datas.dames.exception.InvalidPlateauSizeException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class Jeu implements Serializable{
	

	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Liste des partie, avec pour clé la date de la partie
	 */
	private HashMap<Date,Partie> listePartie;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Initialise la liste des parties
	 */
	public Jeu() {
		this.listePartie = new HashMap<Date,Partie>();
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Enumération des parties
	 * @return Retourne un tableau de date (sous forme d'objet, car pas de cast possible)
	 */
	public Object[] date(){
		return this.listePartie.keySet().toArray();	
	}
	

	/**
	 * Sert à retourner la taille de la HashMap
	 * @return Retourne un entier correspondant à la taille de la HashMap
	 */
	public int taille(){
		return this.listePartie.size();
	}

	/**
	 * Sert à supprimer une partie de la liste
	 * @param date Date de la partie, qui correspond à la clé
	 * @throws IllegalArgumentException Si la date est null
	 * @throws NoSuchElementException Si la date n'existe pas dans la liste
	 */
	public void supprimer(Date date) throws IllegalArgumentException,NoSuchElementException {
		System.out.println(date);
		if(date ==null){throw new IllegalArgumentException("La date n'existe pas");}
		this.listePartie.remove(date);
		this.sauver();
	}


	/**
	 * Sert à ajouter une parties à la liste
	 * @param partie Partie à ajouter
	 * @throws IllegalArgumentException Si la partie est null
	 */
	public void ajouter(Partie partie) throws IllegalArgumentException {
		Date date = new Date();
		if(partie==null){throw new IllegalArgumentException("L'objet partie n'existe pas");}
		partie.stopperPartie();
		this.listePartie.put(date,partie);	
		this.sauver();
		
	}

	/**
	 * Sert à charger la liste des parties
	 * @return Un objet de type Jeu contenant la liste des parties
	 */
	public static Jeu charger(){
		Jeu jeu =null;
		File save = new File("sauvegardeDames.out");
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(save));
			jeu = (Jeu) ois.readObject();
			ois.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println(e.getMessage());}
	 	 catch(IOException e){
	 		 e.printStackTrace();
	 		 System.out.println(e.getMessage());}
		 catch(ClassNotFoundException e){
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }

		return jeu;
	}

	/**
	 * Sert à enregistrer la liste des parties
	 */
	public void sauver(){
		File save = new File("sauvegardeDames.out");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save));
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());}
		  catch (IOException e) {
			  e.printStackTrace();
			  System.err.println(e.getMessage());}
	}

	//====================================================================================================
	



	
	public static void main(String args[]){
		PartieIAvsIA part1 = null;
		PartieIAvsIA part2 = null;
		PartieIAvsIA part4 = null;
		PartieIAvsIA part5 = null;
		PartieIAvsIA part6 = null;
		PartieIAvsIA part7 = null;
		PartieIAvsIA part8 = null;
		PartieIAvsIA part9 = null;
		PartieIAvsIA part10 = null;
		PartieIAvsIA part11= null;
		PartieIAvsIA part12= null;
		PartieIAvsIA part13= null;
		PartieIAvsIA part14= null;
		PartieIAvsIA part15= null;
		PartieIAvsIA part16= null;
		PartieIAvsIA part17= null;
		PartieIAvsIA part18= null;
		PartieIAvsIA part19= null;
		PartieIAvsIA part20= null;
		try {
			part1 = new PartieIAvsIA(10,20,1,1);
			part2 = new PartieIAvsIA(10,20,1,1);
			part4 = new PartieIAvsIA(10,20,1,1);
			part5 = new PartieIAvsIA(10,20,1,1);
			part6 = new PartieIAvsIA(10,20,1,1);
			part7 = new PartieIAvsIA(10,20,1,1);
			part8 = new PartieIAvsIA(10,20,1,1);
			part9 = new PartieIAvsIA(10,20,1,1);
			part10 = new PartieIAvsIA(10,20,1,1);
			part11 = new PartieIAvsIA(10,20,1,1);
			part12= new PartieIAvsIA(10,20,1,1);
			part13= new PartieIAvsIA(10,20,1,1);
			part14= new PartieIAvsIA(10,20,1,1);
			part15= new PartieIAvsIA(10,20,1,1);
			part16= new PartieIAvsIA(10,20,1,1);
			part17= new PartieIAvsIA(10,20,1,1);
			part18= new PartieIAvsIA(10,20,1,1);
			part19= new PartieIAvsIA(10,20,1,1);
			part20= new PartieIAvsIA(10,20,1,1);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPlateauSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Jeu jeu = new Jeu();
		jeu.charger();
		try {
			System.out.println(jeu.taille());
			jeu.ajouter(part1);
			Thread.sleep(10);
			System.out.println(jeu.taille());
			jeu.ajouter(part2);
			Thread.sleep(10);
			System.out.println(jeu.taille());
			jeu.ajouter(part4);
			System.out.println(jeu.taille());
			jeu.ajouter(part5);
			jeu.ajouter(part6);
			jeu.ajouter(part7);
			jeu.ajouter(part8);
			jeu.ajouter(part9);
			jeu.ajouter(part10);
			jeu.ajouter(part11);
			jeu.ajouter(part12);
			jeu.ajouter(part13);
			jeu.ajouter(part14);
			jeu.ajouter(part15);
			jeu.ajouter(part16);
			jeu.ajouter(part17);
			jeu.ajouter(part18);
			jeu.ajouter(part19);
			jeu.ajouter(part20);
			jeu.sauver();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object o : jeu.date()){
			Date d = (Date)o;
			System.out.println(d);
		}
	
		
	}
	
	public HashMap<Date, Partie> getdates(){
		return this.listePartie;	
		
	}
}

		
		
	