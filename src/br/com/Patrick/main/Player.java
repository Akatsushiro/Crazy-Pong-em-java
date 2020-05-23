package br.com.Patrick.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean right, left;
	public int speed = 2;
	public int x, y, widht = 40, height = 10;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(right){
			x += speed;
		} else if (left) {
			x -= speed;
		}
		
		if(x + widht >= Game.getWIDTH()) {
			x = Game.getWIDTH() - widht;
		}else if(x <= 0) {
			x = 0;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, widht, height);
	}
}
