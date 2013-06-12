package pda.view.dames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;


/**
 * <strong>Projet IUT Vannes 2013 - Jeux de dames</strong><br>
 * <br>
 * @author Mathieu THEBAUD
 * @author Nathan VILLIOT
 * @version 1.00
 * @since 1.00
 */

public class BoutonJeuDame extends JButton implements MouseListener{
	
	
	//============================================ATTRIBUT(S)============================================
	/**
	 * La texture à afficher du bouton
	 */
	private Image texture;
	
	/**
	 * La texture normal (position "repos")
	 */
	private Image textureNormal;
	
	/**
	 * La texture lorsqu'on clic
	 */
	private Image textureClic;
	
	/**
	 * La texture lorsqu'on met la souris sur le bouton
	 */
	private Image textureOver;
	
	/**
	 * Police utilisée pour le texte du bouton
	 */
	private static final Font POLICE_TEXTE = new Font("Tahoma",Font.PLAIN,12);
	//====================================================================================================

	
	//==========================================CONSTRUCTEUR(S)==========================================
	/**
	 * Creer un bouton personnalisé avec un texte par défaut.
	 * @param texte le texte à afficher
	 */
	public BoutonJeuDame(String texte){
		super(texte);
		//Paramètres
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setOpaque(false);
		//Chargement des images
		try {
			this.textureNormal = ImageIO.read(new File("./data/img/dames/bouton_menu_simple.png"));
			this.textureClic = ImageIO.read(new File("./data/img/dames/bouton_menu_clic.png"));
			this.textureOver = ImageIO.read(new File("./data/img/dames/bouton_menu_over.png"));
		} catch (IOException e) {
			System.err.println("Erreur de chargement du bouton : "+e.getMessage());
			e.printStackTrace();
		}
		//Image par défaut
		this.texture=this.textureNormal;
		this.addMouseListener(this);
	}
	//====================================================================================================
	
	
	//===============================================DESSIN===============================================
	/**
	 * Redessine le composant
	 */
	public void paintComponent(Graphics g){
		//Dessin de la texture
		g.drawImage(this.texture, 0, 0, this.getWidth(), this.getHeight(), this);
		//Dessin du texte centré
		g.setColor(Color.black);
		g.setFont(BoutonJeuDame.POLICE_TEXTE);
		int positionX = (int) (this.getWidth()/2.0-g.getFontMetrics().stringWidth(this.getText())/2.0);
		int positionY = (int) (this.getHeight()/2.0+g.getFontMetrics().getHeight()/2.0);
		g.drawString(this.getText(), positionX, positionY);
	}
	//====================================================================================================
	
	
	//===========================================ECOUTE SOURIS===========================================
	//Methodes qui permettent de changer l'action en fonction du clic
	public void mouseClicked(MouseEvent eP) {}

	public void mouseEntered(MouseEvent eP) {
		this.texture = this.textureOver;
		this.repaint();
	}

	public void mouseExited(MouseEvent eP) {
		this.texture = this.textureNormal;
		this.repaint();
	}

	public void mousePressed(MouseEvent eP) {
		this.texture = this.textureClic;
		this.repaint();
	}

	public void mouseReleased(MouseEvent eP) {
		this.texture = this.textureNormal;
		this.repaint();
	}	
	//====================================================================================================
	
}