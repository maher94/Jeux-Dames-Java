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


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Cette classe représente le jeu dans son ensemble.<br>
 * Le jeu nous permet de conserver des parties enregistrées [et des les meilleurs scores des joueurs].
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
	
	
	//========================================PARTIES SAUVEGARDEES========================================
	/**
	 * Enumération des clefs
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
		this.listePartie.put(date,partie);	
		this.sauver();
		
	}
	
	/**
	 * Permet d'obtenir la partie qui correspond à la date passée en paramètre.
	 * @param date la date de sauvegarde
	 * @return la partie obtenue, ou null si la date n'était pas valide.
	 */
	public Partie obtenir(Date date){
		return this.listePartie.get(date);
	}
	//====================================================================================================
	
	
	//=======================================CHARGEMENT/SAUVEGARDE=======================================
	/**
	 * Sert à charger la liste des parties
	 * @return Un objet de type Jeu contenant la liste des parties, ou null si le chargement a échoué.
	 */
	public static Jeu charger(){
		Jeu jeu =null;
		File save = new File("sauvegardeDames.out");
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(save));
			jeu = (Jeu) ois.readObject();
			ois.close();
		}catch(FileNotFoundException e){
			System.err.println("Erreur de chargement du jeu : "+e.getMessage());
		}catch(IOException e){
	 		System.err.println("Erreur de chargement du jeu : "+e.getMessage());
	 	 }catch(ClassNotFoundException e){
			System.err.println("Erreur de chargement du jeu : "+e.getMessage());
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
			System.err.println("Erreur de sauvegarde du jeu : "+e.getMessage());
		} catch (IOException e) {
			System.err.println("Erreur de sauvegarde du jeu : "+e.getMessage());
		}
	}
	//====================================================================================================
	
	
}