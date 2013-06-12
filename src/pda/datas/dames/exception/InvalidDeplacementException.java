package pda.datas.dames.exception;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Exception lancée lorsque qu'un déplacement parsé n'est pas valide.<br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class InvalidDeplacementException extends Exception{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur de l'exception
	 */
	public InvalidDeplacementException(){
		super("Formattage du deplacement impossible.");
	}
	//====================================================================================================
}