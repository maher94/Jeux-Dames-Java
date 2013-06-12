package pda.datas.dames.exception;


/**
 * <strong>Projet IUT Vannes 2013 - Jeu de dames</strong><br>
 * Exception lancée lorsque la taille d'un plateau n'est pas valide :<br>
 * Une taille n'est pas valide lorsque :<br>
 * <ul>
 * <li>Le nombre de case noire est égale ou inférieur au nombre de pion total</li>
 * <li>Le nombre de pion ne forme pas des lignes entières</li>
 * </ul>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class InvalidPlateauSizeException extends Exception{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Numéro de sérialisation
	 */
	private static final long serialVersionUID = 1L;
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur de l'exception
	 * @param exMessage Message de l'exception
	 */
	public InvalidPlateauSizeException(String exMessage){
		super(exMessage);
	}
	//====================================================================================================
}