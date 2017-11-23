package logic;

import drawing.GameScreen;
import input.CharacterInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Player extends CollidableEntity implements IRenderable {
	private Image playerImage = null;
	GameLogic gameLogic;
	private int bulletDelayTick = 0;
	private double originalHp;

	public Player(GameLogic gameLogic) {
		// TODO Auto-generated constructor stub
		super(1000, 20);
		this.originalHp = this.hp;
		playerImage = RenderableHolder.ship3; // temporary player ship might be further implement in the future

		this.gameLogic=gameLogic;
		
		if (playerImage != null) {
			this.width = playerImage.getWidth();
			this.height = playerImage.getHeight();
			// System.out.println(imageWidth + " " + imageHeight);
			this.x = SceneManager.SCENE_WIDTH / 2 - this.width / 2;
			this.y = (SceneManager.SCENE_HEIGHT - this.height) - 60;
			this.speed = 3;
			this.side = 1;
			this.collideDamage = 10; // test
		} else {
			width = 0;
			height = 0;
		}
	}
	
	private void drawHpBar(GraphicsContext gc) {
		double percentHp = this.hp/this.originalHp;
		if(percentHp >= 0.65) {
			//gc.setFill(Color.LAWNGREEN);
			LinearGradient linearGrad = new LinearGradient(
	                0,   // start X 
	                0,   // start Y
	                0,   // end X
	                1, // end Y
	                true, // proportional
	                CycleMethod.NO_CYCLE, // cycle colors
	                // stops
	                new Stop(0.1f, Color.LAWNGREEN),
	                new Stop(1.0f, Color.GREEN));
	        gc.setFill(linearGrad);
		}
		else if(percentHp >= 0.25) {
			LinearGradient linearGrad = new LinearGradient(0,0,0,1,true,CycleMethod.NO_CYCLE,
	                // stops
	                new Stop(0.1f, Color.YELLOW),
	                new Stop(1.0f, Color.RED));
	        gc.setFill(linearGrad);
		}
		else {
			LinearGradient linearGrad = new LinearGradient(0,0,0,1,true,CycleMethod.NO_CYCLE,
	                // stops
	                new Stop(0.1f, Color.RED),
	                new Stop(1.0f, Color.BLACK));
	        gc.setFill(linearGrad);
		}
		gc.fillRect(SceneManager.SCENE_WIDTH/2 - 200*percentHp, 750, 2*200*percentHp, 20);
		
		
		
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(playerImage, x, y);
		drawHpBar(gc);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (CharacterInput.getKeyPressed(KeyCode.UP)) {
			// System.out.println("GO uppppp");
			forward(true);
		}
		if (CharacterInput.getKeyPressed(KeyCode.DOWN)) {
			// System.out.println("GO DOWNNN");
			forward(false);
		}
		if (CharacterInput.getKeyPressed(KeyCode.RIGHT)) {
			// System.out.println("GO RIGHt");
			turn(true);
		}
		if (CharacterInput.getKeyPressed(KeyCode.LEFT)) {
			// System.out.println("GO Left");
			turn(false);
		}
		if (CharacterInput.getKeyPressed(KeyCode.SPACE)) {
			// shoot a bullet
			// to be further discussed
			
			if(bulletDelayTick%7 == 0) {
				System.out.println("SHOOOOT");
				gameLogic.addPendingBullet(new Bullet(this.x+(this.width/2.0),this.y));
				RenderableHolder.laser.play();
			}
			bulletDelayTick++;
		}

	}

	@Override
	public void onCollision(CollidableEntity others) {
		// TODO Auto-generated method stub
		this.hp -= others.collideDamage;
		// to be further discussed (sound effect etc)
		if (this.hp <= 0) {
			this.destroyed = true;
			this.visible = false;
		}

	}

	private void forward(boolean b) {
		if (b == true) { // move forward
			if (this.y - speed >= 0) {
				this.y -= speed;
			}
		}
		if (b == false) { // move backward
			if (this.y + speed + this.height <= SceneManager.SCENE_HEIGHT) {
				this.y += speed;
			}
		}
	}

	private void turn(boolean b) {
		if (b == true) { // move right
			if (this.x + speed + this.width <= SceneManager.SCENE_WIDTH) {
				this.x += speed;
			}
		}
		if (b == false) { // move left
			if (this.x - speed >= 0) {
				this.x -= speed;
			}
		}
	}

}
