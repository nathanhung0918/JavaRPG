// Possibly redundant subclass of Entity.
// There are two types of items: Recover and Flash.
// Upon collection, informs the Player
// that the Player does indeed have the item.

package ntou.cs.Java2016.FinalProject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Item extends Entity{
	
	private BufferedImage sprite;
	private int type;
	public static final int Flash = 0;
	public static final int Recover = 1;
	
	public Item(TileMap tm) {
		super(tm);
		type = -1;
		width = height = 16;
		cwidth = cheight = 12;
	}
	
	public void setType(int i) {
		type = i;
		if(type == Flash) {
			sprite = Content.ITEMS[1][0];
		}
		else if(type == Recover) {
			sprite = Content.ITEMS[1][1];
		}
	}
	
	public void collected(Player p) {
		if(type == Flash) {
			p.addItemFlash();
		}
		if(type == Recover) {
			p.addItemRecovery();
		}
	}
	
	public void draw(Graphics2D g) {
		setMapPosition();
		g.drawImage(sprite, x + xmap - width / 2, y + ymap - height / 2, null);
	}
	
}
