package ntou.cs.Java2016.FinalProject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class LoadState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage diamond;
	private int currentOption = 0;
	private String[] tempOptions;
	private static String fileName="";
	
	private String[] options={"","","","back"};
	
	public LoadState(GameStateManager gsm) {
		super(gsm);		
	}
	
	public void init() {
		bg = Content.MENUBG[0][0];
		diamond = Content.DIAMOND[0][0];
		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.load("/SFX/menuoption.wav", "menuoption");
		
		File f = new File("saves");
		File [] f1 = f.listFiles();
		for(int i =0;i<f.listFiles().length;i++)
		{
			String temp = f1[i].getName();
			temp = temp.replace(".txt", "");
			options[i]=temp;
		}
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		if(options[0]!= "")
		{
			Content.drawString(g, options[0], 65, 90);
		}
		else
		{
			Content.drawString(g, "new story", 65, 90);
		}
		if(options[1]!= "")
		{
			Content.drawString(g, options[1], 65, 100);
		}
		else
		{
			Content.drawString(g, "new story", 65, 100);
		}
		if(options[2]!= "")
		{
			Content.drawString(g, options[2], 65, 110);
		}
		else
		{
			Content.drawString(g, "new story", 65, 110);
		}
		Content.drawString(g, options[3], 65, 120);
		
		if(currentOption == 0) g.drawImage(diamond, 50, 86, null);
		else if(currentOption == 1) g.drawImage(diamond, 50, 96, null);
		else if(currentOption == 2)	g.drawImage(diamond,50,106,null);
		else if(currentOption == 3)	g.drawImage(diamond,50,116,null);

	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			JukeBox.play("menuoption");
			currentOption++;
		}
		if(Keys.isPressed(Keys.UP) && currentOption > 0) {
			JukeBox.play("menuoption");
			currentOption--;
		}
		if(Keys.isPressed(Keys.ENTER)) {
			JukeBox.play("collect");
			selectOption();
		}
	}
	public void setFileName(int i){
		 fileName = options[i];		
	}
	public static String getFileName(){
		return fileName;		
	}
	
	private void selectOption() {
		
		if(currentOption == 0) {
			setFileName(0);
			FileReader fr;
			try {
				fr = new FileReader("saves\\"+options[0]+".txt");
				BufferedReader br = new BufferedReader(fr);
				try {
					int stage = Integer.valueOf(br.readLine());
					
					switch(stage){
					case 2: gsm.setState(GameStateManager.PLAY2);
						break;
					case 3:gsm.setState(GameStateManager.PLAY3);
						break;
					case 4:gsm.setState(GameStateManager.PLAY4);
						break;
					default:gsm.setState(GameStateManager.PLAY);
						break;
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentOption == 1) {
			setFileName(1);
			FileReader fr;
			try {
				fr = new FileReader("saves\\"+options[1]+".txt");
				BufferedReader br = new BufferedReader(fr);
				try {
					int stage = Integer.valueOf(br.readLine());
					
					switch(stage){
					case 2: gsm.setState(GameStateManager.PLAY2);
						break;
					case 3:gsm.setState(GameStateManager.PLAY3);
						break;
					case 4:gsm.setState(GameStateManager.PLAY4);
						break;
					default:gsm.setState(GameStateManager.PLAY);
						break;
					}
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(currentOption == 2) {
			setFileName(2);
			FileReader fr;
			try {
				fr = new FileReader("saves\\"+options[2]+".txt");
				BufferedReader br = new BufferedReader(fr);
				try {
					int stage = Integer.valueOf(br.readLine());
					
					switch(stage){
					case 2: gsm.setState(GameStateManager.PLAY2);
						break;
					case 3:gsm.setState(GameStateManager.PLAY3);
						break;
					case 4:gsm.setState(GameStateManager.PLAY4);
						break;
					default:gsm.setState(GameStateManager.PLAY);
						break;
					}
					
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(currentOption == 3) {
			gsm.setState(GameStateManager.MENU);
		}
		
		
	}
	
}
