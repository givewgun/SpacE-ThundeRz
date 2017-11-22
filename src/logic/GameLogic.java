package logic;

import java.util.ArrayList;
import java.util.List;

import drawing.GameScreen;
import sharedObject.RenderableHolder;

public class GameLogic {
	
	private List<Entity> gameObjectContainer;
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;

	private GameScreen canvas;
	private boolean isGameRunning;
	
	private Player player;
	private EBig ebig;
	private EBoss eboss;
	
	public GameLogic(GameScreen canvas){
		this.gameObjectContainer = new ArrayList<Entity>();
	
		RenderableHolder.getInstance().add(new Background());
		player = new Player();
		addNewObject(player);
		ebig = new EBig();
		addNewObject(ebig);
		eboss = new EBoss();
		addNewObject(eboss);
		this.canvas = canvas;

	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		player.update();
		/*
		 * To be further discussed (how to store enemy etc)
		 * 
		if(!mine.isDestroyed() && player.collideWith(E)){
			mine.onCollision(tank);
		}
		*/
	}

	public void startGame() {
		this.isGameRunning = true;
		new Thread(this::gameLoop, "Game Loop Thread").start();
	}

	public void stopGame() {
		this.isGameRunning = false;
	}

	private void gameLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isGameRunning) {
			long elapsedTime = System.nanoTime() - lastLoopStartTime;
			if (elapsedTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;

				updateGame();
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateGame() {
		// TODO fill code
		//need to check collide in the gameObjectContainer, but how ?
		//to be further discussed
		player.update();
		ebig.update();
		eboss.update();
		
		
	}
}
