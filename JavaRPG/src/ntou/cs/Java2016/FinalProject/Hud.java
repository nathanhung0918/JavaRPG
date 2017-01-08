// Contains a reference to the Player.
// Draws all relevant information at the
// bottom of the screen.

package ntou.cs.Java2016.FinalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hud {
	
	private int yoffset;
	
	private BufferedImage bar;
	private BufferedImage flash;
	private BufferedImage recover;
	
	private Player player;
	
	private int HP;
	
	private Font font;
	private Color textColor; 
	
	public Hud(Player p, ArrayList<Diamond> d) {
		
		player = p;
		HP = p.getHP();
		yoffset = GamePanel.HEIGHT;
		
		bar = Content.BAR[0][0];
		flash = Content.ITEMS[0][0];
		recover = Content.ITEMS[0][1];
		
		font = new Font("Arial", Font.PLAIN, 10);
		textColor = new Color(47, 64, 126);
	}
	
	public void draw(Graphics2D g) {
		
		// draw hud
		g.drawImage(bar, 0, yoffset, null);
		
		// draw HP bar
		g.setColor(textColor);
		g.fillRect(8, yoffset + 6, (int)(28.0 * player.getHP() / HP), 4);
		
		// draw diamond amount
		g.setColor(textColor);
		g.setFont(font);
		String s = player.getHP() + "/" + HP;
		Content.drawString(g, s, 40, yoffset + 3);
		if(player.getHP() >= 10) g.drawString("HP", 83, yoffset+12);
		else g.drawString("HP", 75, yoffset+12);
		
		// draw items
		if(player.getItemFlash()) g.drawImage(flash, 100, yoffset, null);
		if(player.getItemRecovery()) g.drawImage(recover, 112, yoffset, null);
		
		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 85, 3);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, minutes + ":" + seconds, 85, 3);
		}
		
		
		
	}
	
}
