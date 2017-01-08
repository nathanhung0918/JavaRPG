// The only subclass the fully utilizes the
// Entity superclass (no other class requires
// movement in a tile based map).
// Contains all the gameplay associated with
// the Player.

package ntou.cs.Java2016.FinalProject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends Entity {
	
	// sprites
	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] upSprites;
	private BufferedImage[] downBoatSprites;
	private BufferedImage[] leftBoatSprites;
	private BufferedImage[] rightBoatSprites;
	private BufferedImage[] upBoatSprites;
	
	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWNBOAT = 4;
	private final int LEFTBOAT = 5;
	private final int RIGHTBOAT = 6;
	private final int UPBOAT = 7;
	
	// gameplay
	private int numDiamonds;
	private int totalDiamonds;
	private boolean hasBoat;
	private boolean hasAxe;
	private boolean onWater;
	private long ticks;
	
	//add
	private int stage;//關卡
	private int hp;//血量
	private int skillPoint;//能力點
	private int hpPoint;//hp點數
	private int recoveryRate;//回復點數
	private int speedPoint;//速度點數
	private boolean shieldUsing;//使用護盾
	private String playerName;//玩家名稱
	//道具控制
	private boolean itemFlash;
	private boolean itemRecovery;
	
	private Random random = new Random();
	
	private boolean cheatMode;
	
	public Player(TileMap tm) {
		
		super(tm);
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		moveSpeed = 5;
		
		numDiamonds = 0;
		
		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];
		downBoatSprites = Content.PLAYER[4];
		leftBoatSprites = Content.PLAYER[5];
		rightBoatSprites = Content.PLAYER[6];
		upBoatSprites = Content.PLAYER[7];
		
		animation.setFrames(downSprites);
		animation.setDelay(10);
		ticks = 30*61;
		//add
		hp = 10;
		skillPoint = 5;
		//playerName = name;
		recoveryRate = 0;
		speedPoint = 1;
		shieldUsing = false;
		itemFlash = false;
		itemRecovery = false;
		hpPoint = 0;
		
		cheatMode = false;
	}
	public Player(TileMap tm,int skillPoint,int recoveryRate,int speedPoint,int hpPoint) {
		
		super(tm);
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		moveSpeed = 2;
		
		numDiamonds = 0;
		
		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];
		downBoatSprites = Content.PLAYER[4];
		leftBoatSprites = Content.PLAYER[5];
		rightBoatSprites = Content.PLAYER[6];
		upBoatSprites = Content.PLAYER[7];
		
		animation.setFrames(downSprites);
		animation.setDelay(10);
		ticks = 30*61;
		//add
		hp = 10;
		this.skillPoint = skillPoint;
		//playerName = name;
		this.recoveryRate = skillPoint;
		this.speedPoint = speedPoint;
		shieldUsing = false;
		itemFlash = false;
		itemRecovery = false;
		this.hpPoint = hpPoint;
		
		cheatMode = false;
	}
	
	private void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}
	
	public void collectedDiamond() { numDiamonds++; }
	public int numDiamonds() { return numDiamonds; }
	public int getTotalDiamonds() { return totalDiamonds; }
	public void setTotalDiamonds(int i) { totalDiamonds = i; }
	
	public void gotBoat() { hasBoat = true; tileMap.replace(22, 4); }
	public void gotAxe() { hasAxe = true; }
	public boolean hasBoat() { return hasBoat; }
	public boolean hasAxe() { return hasAxe; }
	
	// Used to update time.
	public long getTicks() { return ticks; }
	
	// Keyboard input. Moves the player.
	public void setDown() {
		super.setDown();
	}
	public void setLeft() {
		super.setLeft();
	}
	public void setRight() {
		super.setRight();
	}
	public void setUp() {
		super.setUp();
	}
	
	// Keyboard input.
	// If Player has axe, dead trees in front
	// of the Player will be chopped down.
	public void setAction() {
		if(hasAxe) {
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 21) {
				tileMap.setTile(rowTile - 1, colTile, 1);
				JukeBox.play("tilechange");
			}
			if(currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
				tileMap.setTile(rowTile + 1, colTile, 1);
				JukeBox.play("tilechange");
			}
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
				tileMap.setTile(rowTile, colTile - 1, 1);
				JukeBox.play("tilechange");
			}
			if(currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
				tileMap.setTile(rowTile, colTile + 1, 1);
				JukeBox.play("tilechange");
			}
		}
	}
	
	public void update() {
		
		ticks--;
		
		// check if on water
		boolean current = onWater;
		if(tileMap.getIndex(ydest / tileSize, xdest / tileSize) == 4) {
			onWater = true;
		}
		else {
			onWater = false;
		}
		// if going from land to water
		if(!current && onWater) {
			JukeBox.play("splash");
		}
		
		// set animation
		if(down) {
			if(onWater && currentAnimation != DOWNBOAT) {
				setAnimation(DOWNBOAT, downBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, 10);
			}
		}
		if(left) {
			if(onWater && currentAnimation != LEFTBOAT) {
				setAnimation(LEFTBOAT, leftBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, 10);
			}
		}
		if(right) {
			if(onWater && currentAnimation != RIGHTBOAT) {
				setAnimation(RIGHTBOAT, rightBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, 10);
			}
		}
		if(up) {
			if(onWater && currentAnimation != UPBOAT) {
				setAnimation(UPBOAT, upBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != UP) {
				setAnimation(UP, upSprites, 10);
			}
		}
		
		// update position
		super.update();
		
	}
	
	// Draw Player
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
	//set var
	public void setName(String name){
		playerName = name;
	}
	public void setStage(int s){
		stage = s;
	}
	public void setSkillPoint(int s){
		skillPoint = s;
	}
	public void setHpPoint(int s){
		hpPoint = s;
	}
	public void setRecoveryRate(int s){
		recoveryRate = s;
	}
	public void setSpeedPoint(int s){
		speedPoint = s;
	}
	
	//add
	public int getStage() {
		return stage;
	}
	public int getSkillPoint() {
		return skillPoint;
	}
	public int getHpPoint() {
		return hpPoint;
	}
	public int getRecoveryRate() {
		return recoveryRate;
	}
	public int getSpeedPoint() {
		return speedPoint;
	}
	public Boolean getShieldUsing() {
		return shieldUsing;
	}
	public String getPlayerName() {
		return playerName;
	}/*
	public Direct getFace() {
		return face;
	}
	*/
	public boolean getItemFlash() {
		return itemFlash;
	}
	public boolean getItemRecovery() {
		return itemRecovery;
	}
	
	//能力
	public void addRecover()//增加回復
	{
		
		if(skillPoint >= 1){
			if(recoveryRate + 1 <= 5) recoveryRate++;
			skillPoint--;
		}
	}
	public void addHP()//增加HP
	{	
		if(skillPoint >= 1){
			if(hpPoint + 1 <= 5) hpPoint++;
			skillPoint--;
		}
	}
	public void addSpeed()//增加速度
	{	
		if(skillPoint >= 1){
			if(speedPoint + 1 <= 5) speedPoint++;
			skillPoint--;
		}
	}
	//加skill point
	public void addPoint(int n)
	{
		skillPoint += n;
	}
	//關卡開始前的重置
	public void reset()
	{
		//x = 5;
		//y = 5;
		//coordinate.setLocation(start);
		//face = Direct.DOWN;
		hp = 10 + hpPoint;
		shieldUsing = false;
		moveSpeed = 2 + speedPoint;
	}
	//受傷扣血
	public void decreaseHP(int damage)
	{	
		if(hp > 0){
			if(shieldUsing) shieldUsing = false;
			else hp -= damage;
		}
	}
	//增加道具
	//增加道具瞬移
	public void addItemFlash()
	{
		itemFlash = true;
	}
	//增加道具回血
	public void addItemRecovery()
	{
		itemRecovery = true;
	}
	//使用道具
	//使用道具瞬移
	public void useFlash()
	{
		
		if(itemFlash || cheatMode)
		{
			
			setTilePosition(1 + random.nextInt(8), 1 + random.nextInt(8));
			
			itemFlash = false;
		}
	}
	//使用道具回血
	public void recover()
	{
		if(itemRecovery || cheatMode)
		{
			addCurrentHP(5);
			itemRecovery = false;
		}
	}
	//增加hp
	public void addCurrentHP(int recover)
	{
		if((hp + recover) > (10 + hpPoint )) hp = 10 + hpPoint; 
		else hp += recover;
	}
	
	//判斷死亡
	public boolean alive()
	{
		if(hp > 0) return true;
		else return false;
	}
	//取得HP
	public int getHP()
	{
		return hp;
	}
	
	public void setCheatMode()
	{
		cheatMode = true;
	}
	public boolean getCheatMode()
	{
		return cheatMode;
	}
}
