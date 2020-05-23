package br.com.Patrick.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Cenario {
	private static int playerPoint = 0, enemyPoint = 0;
	
	
	public void tick() {
		
		if(Game.ball.y >= Game.getHEIGHT()) {
			//Ponto Inimigo
			System.out.println("Ponto");
			enemyPoint += 1;
			new Game();
			return;
		} else if(Game.ball.y <= 0) {
			//Ponto do Jogador
			playerPoint += 1;
			new Game();
			return;
		}
	}
	
	public void render(Graphics g) {
		// Linha central
		g.setColor(Color.gray);
		g.fillRect(0, Game.getHEIGHT() / 2 - 4, Game.getWIDTH(), 1);
		
		//Placar Inimigo
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(enemyPoint), 10, Game.getHEIGHT() / 2 - 5);
		
		//Placar Jogador
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(playerPoint), 220, Game.getHEIGHT() / 2 + 5);
		
		
	}
	
}
