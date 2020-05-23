package br.com.Patrick.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  BufferedImage layer = new BufferedImage(getWIDTH(), getHEIGHT(), BufferedImage.TYPE_INT_RGB);
	private static JFrame frame;
	
	public static Cenario cenario;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	private static int WIDTH = 240;
	private static int HEIGHT = 120;
	private static int SCALE = 3;
	
	public Game(){
		this.setPreferredSize(new Dimension(getWIDTH() * SCALE, getHEIGHT() * SCALE));
		this.addKeyListener(this);
		cenario = new Cenario();
		player = new Player(100, getHEIGHT() - 15);
		enemy = new Enemy(100, 5);
		ball = new Ball(100, getHEIGHT()/2 - 1);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		new Thread(game).start();
	}
	
	
	public void tick() {
		cenario.tick();
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWIDTH(), getHEIGHT());
		cenario.render(g);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, getWIDTH() * SCALE, getHEIGHT() * SCALE, null);
		bs.show();
	}
	
	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
			//player.left = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
			//player.right = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

}
