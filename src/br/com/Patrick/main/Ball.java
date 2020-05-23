package br.com.Patrick.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x, y;
	public int widht, height;
	public int angle;
	
	public double dx,dy;
	public double speed = 1.4;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.widht  = 4;
		this.height = 4;
		
		this.ball_angle();


	}

	public void ball_angle() {
		this.angle = new Random().nextInt(125) + 45;
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		if(x + (dx * speed) + widht >= Game.getWIDTH()) {
			dx *= -1;
		} else if (x + (dx * speed) < 0) {
			dx *= -1;
		}
				
		Rectangle bounds = new Rectangle((int)(x + (dx * speed)), (int)(y+(dy * speed)), widht, height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.widht, Game.player.height);
		Rectangle boundsEnemy  = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.widht, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			this.ball_angle();
			dy *= -1;
		} else if (bounds.intersects(boundsEnemy)) {
			this.ball_angle();
			if(dy < 0) {
				dy *= -1;
			}
		}
		
		
		this.x += this.dx * speed;
		this.y += this.dy * speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, widht, height);
	}
}
