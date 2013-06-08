package fr.modele;

import fr.modele.exception.InvalidDeplacementException;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * Cette classe représente un déplacement sur le plateau dame.<br>
 * Le déplacement est caractérisé par une position de départ ainsi qu'une position d'arrivée.<br>
 * Le déplacement possède un booleen qui permet d'inquider si le déplacement effectue une prise ou non.
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class Deplacement {
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * Position x du point d'origine
	 */
	private int positionXOrigine;
	
	/**
	 * Position y du point d'origine
	 */
	private int positionYOrigine;
	
	/**
	 * Position x du point d'arrivée
	 */
	private int positionXArrivee;
	
	/**
	 * Position y du point d'arrivée
	 */
	private int positionYArrivee;
	
	/**
	 * Savoir si le déplacement mange un pion
	 */
	private boolean priseEffectuee;

	/**
	 * Orientation NO d'un déplacement
	 */
	public static final int NO=0;
	
	/**
	 * Orientation NE d'un déplacement
	 */
	public static final int NE=1;
	
	/**
	 * Orientation SE d'un déplacement
	 */
	public static final int SE=2;
	
	/**
	 * Orientation SO d'un déplacement
	 */
	public static final int SO=3;	
	//====================================================================================================
	
	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Constructeur d'un deplacement.
	 * @param posx1 Position x du point d'origine
	 * @param posy1 Position y du point d'origine
	 * @param posx2 Position x du point d'arrivée
	 * @param posy2 Position y du point d'arrivée
	 */
	public Deplacement(int posx1,int posy1,int posx2,int posy2){
		//Recuperation des attributs
		this.positionXOrigine = posx1;
		this.positionYOrigine = posy1;
		this.positionXArrivee = posx2;
		this.positionYArrivee = posy2;
	}
	//====================================================================================================
	
	
	//============================================ACCESSEUR(S)============================================
	public int getPositionXOrigine() {
		return this.positionXOrigine;
	}
	public int getPositionYOrigine() {
		return this.positionYOrigine;
	}
	public int getPositionXArrivee() {
		return this.positionXArrivee;
	}
	public int getPositionYArrivee() {
		return this.positionYArrivee;
	}
	public boolean isPriseEffectuee() {
		return this.priseEffectuee;
	}
	//====================================================================================================
	
	
	//============================================MUTATEUR(S)============================================
	public void setPositionXOrigine(int positionXOrigineP) {
		this.positionXOrigine = positionXOrigineP;
	}
	public void setPositionYOrigine(int positionYOrigineP) {
		this.positionYOrigine = positionYOrigineP;
	}
	public void setPositionXArrivee(int positionXArriveeP) {
		this.positionXArrivee = positionXArriveeP;
	}
	public void setPositionYArrivee(int positionYArriveeP) {
		this.positionYArrivee = positionYArriveeP;
	}
	public void setPriseEffectuee(boolean priseEffectueeP) {
		this.priseEffectuee = priseEffectueeP;
	}
	//====================================================================================================
	
	
	//========================================AUTRE(S) METHODE(S)========================================
	/**
	 * Méthode qui permet d'obtenir une représentation d'un Deplacement sous forme de chaîne.<br>
	 * @return Retourne la chaîne de caractère qui représente un Deplacement.
	 */
	public String toString(){
		//Code ASCII de depart des lettres majuscules
		int codeDepart = (int)'A';
		//Coordonnees de depart
		String deplacementChaine = "("+(char)(codeDepart+this.positionXOrigine)+","+this.positionYOrigine+")";
		//Coordonnees d'arrivee
		deplacementChaine = deplacementChaine+"-->"+"("+(char)(codeDepart+this.positionXArrivee)+","+this.positionYArrivee+")";
		//Retour
		return deplacementChaine;
	}
	
	/**
	 * Redéfinnition de la méthode equals.<br>
	 * On redéfinni cette méthode car on veut comparer un déplacement selon ses coordonnées et <strong>non</strong> selon la prise.<br>
	 * Cette méthode servira à l'utilisation correcte de la méthode <i>contains</i> de la classe ArrayList.<br>
	 * @return Retourne vrai si les deux déplacements sont égaux, sinon retourne faux.
	 */
	public boolean equals(Object o){
		boolean deplacementsEgaux=false;
		//Si l'objet est un deplacement
		if(o instanceof Deplacement){
			Deplacement d = (Deplacement)o;
			//Si les deux objets ont la meme ref.
			if(d==this){
				deplacementsEgaux=true;
			}
			//Sinon on verifie les coordonnees (et PAS la prise)
			else{
				deplacementsEgaux = this.positionXArrivee==d.positionXArrivee && this.positionXOrigine == d.positionXOrigine && this.positionYArrivee==d.positionYArrivee && this.positionYOrigine == d.positionYOrigine;
			}
		}
		//Retour
		return deplacementsEgaux;
	}
	
	/**
	 * Cette méthode sert à calculer l'orientation d'un déplacement.<br>
	 * Elle sera utilisée par la classe Plateau pour determiner l'orientation d'un déplacement lors d'une prise,
	 * et donc determiner la "ligne" à verifier.
	 * @param d Le déplacement dont on veut connaître l'orientation.
	 * @return Retourne l'entier représentant le déplacement.<br>
	 * <strong>ATTENTION :</strong>si le déplacement n'est pas en diagonnale, l'orientation retournée peut être fausse.
	 */
	public static int getOrientationDeplacement(Deplacement d){
		int orientation=-1;
		
		//Orientation au nord (penser a l'inversion, le Y augmente en descendant!)
		if(d.getPositionYOrigine()>d.getPositionYArrivee()){
			//Orientation ouest
			if(d.getPositionXOrigine()>d.getPositionXArrivee()){
				orientation = Deplacement.NO;
			}
			//Orientation est
			else{
				orientation = Deplacement.NE;
			}
		}
		//Orienation au sud
		else{
			//Orientation ouest
			if(d.getPositionXOrigine()>d.getPositionXArrivee()){
				orientation = Deplacement.SO;
			}
			//Orientation est
			else{
				orientation = Deplacement.SE;
			}
		}
		//Retour
		return orientation;
	}
	//====================================================================================================
	
	
	//==============================================PARSING==============================================
	/**
	 * Pour convertir une chaine de caractère en un objet Deplacement.<br>
	 * La chaine doit avoir la forme suivante : <i>x1/y1-x2/y2</i>
	 * La forme de la chaine doit utiliser une lettre majuscule pour les x et un nombre pour les y (voir la méthode toString() du Plateau)
	 * @param texte Le texte qui décris le déplacement.
	 * @return Retourne l'objet déplacement correspondant au texte.
	 * @throws InvalidDeplacementException Si le texte ne correspond pas à un déplacement (mauvais format).
	 */
	public static Deplacement parseDeplacement(String texte)throws InvalidDeplacementException{
		Deplacement deplacementEcrit=null;
		//Suppresion des espaces
		texte = texte.trim();
		//Verification du formatage
		boolean formatCorrect = texte.contains("/") && texte.contains("-") && texte.length()<=9;
		
		//Si formatage pour l'instant correct
		if(formatCorrect){
			//Separation de l'arrivee et du depart
			String[] arriveeEtDepart = texte.split("-");
			//Verification du format
			if(arriveeEtDepart.length==2){
				//Recuperation des coordonnees
				int[] lesX = new int[2];
				int[] lesY = new int[2];
				
				//Parcours
				for(int i=0;i<arriveeEtDepart.length;i++){
					//Separation (si cela ne fonctionne pas => mauvais format)
					try{
						lesX[i]=(int)(arriveeEtDepart[i].split("/")[0].charAt(0))-(int)'A';
						lesY[i]=Integer.parseInt(arriveeEtDepart[i].split("/")[1]);
					
						//Verification des valeurs, si valeur improbable (n�gatives)
						if(lesX[i]<0 || lesY[i]<0){
							throw new InvalidDeplacementException();
						}
					}catch(NumberFormatException e){
						throw new InvalidDeplacementException();
					}
					catch(ArrayIndexOutOfBoundsException e){
						throw new InvalidDeplacementException();
					}
				}
				//Creation du deplacement correspondant
				deplacementEcrit = new Deplacement(lesX[0],lesY[0],lesX[1],lesY[1]);
			}
			//Sinon exception
			else{
				throw new InvalidDeplacementException();
			}
		}
		//Sinon exception
		else{
			throw new InvalidDeplacementException();
		}
		//Retour
		return deplacementEcrit;
	}
	//====================================================================================================
	
}