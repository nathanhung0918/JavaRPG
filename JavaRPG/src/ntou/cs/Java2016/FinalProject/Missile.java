package ntou.cs.Java2016.FinalProject;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Missile extends Entity {
	
	BufferedImage[] sprites;
	
	private ArrayList<int[]> tileChanges;
	private Direct direct;
	public Missile(TileMap tm,int X, int Y, int injure, Direct direct) {
		
		super(tm);
		
		this.direct = direct;
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		sprites = Content.Fish[0];
		animation.setFrames(sprites);
		animation.setDelay(10);
		
		tileChanges = new ArrayList<int[]>();
		
	}
	public Direct getDirect()
	{
		return direct;
	}
	public void addChange(int[] i) {
		tileChanges.add(i);
	}
	public ArrayList<int[]> getChanges() {
		return tileChanges;
	}
	
	public void update() {
		animation.update();
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}


