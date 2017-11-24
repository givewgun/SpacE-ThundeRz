package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

import drawing.GameScreen;
import game.GameMain;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class GameLogic {

	private Queue<Bullet> pendingEnemyBullet;
	private Queue<Bullet> pendingPlayerBullet;

	private List<Entity> gameObjectContainer;
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;

	private GameScreen canvas;
	private boolean isGameRunning;

	private Player player;
	private EBig ebig;
	private EBoss eboss;
	private EBug ebug;

	public GameLogic(GameScreen canvas) {
		this.gameObjectContainer = new ArrayList<Entity>();

		RenderableHolder.getInstance().add(new Background());
		player = new Player(this); /////////////////////////////////////
		addNewObject(player);
		ebig = new EBig();
		addNewObject(ebig);
		eboss = new EBoss(this);
		addNewObject(eboss);
		ebug = new EBug(
				ThreadLocalRandom.current().nextDouble(SceneManager.SCENE_WIDTH - RenderableHolder.eBug.getWidth()));
		addNewObject(ebug);
		this.canvas = canvas;

		pendingEnemyBullet = new ConcurrentLinkedQueue<>();
		pendingPlayerBullet = new ConcurrentLinkedQueue<>();

	}

	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void startGame() {
		this.isGameRunning = true;
		new Thread(this::gameLoop, "Game Loop Thread").start();
	}

	public void stopGame() {
		this.isGameRunning = false;
		this.gameObjectContainer.clear();
		this.pendingEnemyBullet.clear();
		this.pendingPlayerBullet.clear();
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
		// need to check collide in the gameObjectContainer, but how ?
		// to be further discussed
		if (!pendingEnemyBullet.isEmpty()) {
			gameObjectContainer.add(pendingEnemyBullet.poll());

		}
		if (!pendingPlayerBullet.isEmpty()) {
			gameObjectContainer.add(pendingPlayerBullet.poll());

		}
		for (Entity i : gameObjectContainer) {
			i.update();
		}
		for (Entity i : gameObjectContainer) {
			for (Entity j : gameObjectContainer) {
				if (i != j && ((CollidableEntity) i).collideWith((CollidableEntity) j)) {
					((CollidableEntity) i).onCollision((CollidableEntity) j);
				}
			}
		}
		int i = 0;
		while (i < gameObjectContainer.size()) {
			if (gameObjectContainer.get(i).isDestroyed()) {
				gameObjectContainer.remove(i);
			} else {
				i++;
			}
		}
		if (player.isDestroyed()) {
			GameMain.stopGame();
		}
	}

	public void addPendingBullet(Bullet a) {
		if (a.side == 1) {
			pendingPlayerBullet.add(a);
		} else if (a.side == -1) {
			pendingEnemyBullet.add(a);
		}
		canvas.addPendingBullet(a);
	}
}
