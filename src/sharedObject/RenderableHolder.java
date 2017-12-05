package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	// various image plz check the image first before using (like to find its size /
	// how it looks etc)
	public static Image ship1, ship2, ship3, ship4, ship5, ship6, eBig, eBoss, eEyeball, eBug, eSquid, eWing, bullet,
			bossBullet, roundBulletB, roundBulletY, roundBulletR, roundBulletP, missile, explo1, explo2, explo3, explo4,
			background;
	public static AudioClip bgm, laser;

	public static Image[] playerShip;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ()) {
				return 1;
			}
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		ship1 = new Image(ClassLoader.getSystemResource("player/p1.gif").toString());
		ship2 = new Image(ClassLoader.getSystemResource("player/p2.gif").toString());
		ship3 = new Image(ClassLoader.getSystemResource("player/p3.gif").toString());
		ship4 = new Image(ClassLoader.getSystemResource("player/p4.gif").toString());
		ship5 = new Image(ClassLoader.getSystemResource("player/p5.gif").toString());
		ship6 = new Image(ClassLoader.getSystemResource("player/p6.gif").toString());

		playerShip = new Image[] { ship1, ship2, ship3, ship4, ship5, ship6 };

		eBig = new Image(ClassLoader.getSystemResource("enemy/eBig.gif").toString());
		eBoss = new Image(ClassLoader.getSystemResource("enemy/eBoss.gif").toString());
		eEyeball = new Image(ClassLoader.getSystemResource("enemy/eEyeball.gif").toString());
		eBug = new Image(ClassLoader.getSystemResource("enemy/eBug.gif").toString());
		eSquid = new Image(ClassLoader.getSystemResource("enemy/eSquid.gif").toString());
		eWing = new Image(ClassLoader.getSystemResource("enemy/eWing.gif").toString());

		explo1 = new Image(ClassLoader.getSystemResource("explosion/explosion.gif").toString());
		explo2 = new Image(ClassLoader.getSystemResource("explosion/exMissile.gif").toString());
		explo3 = new Image(ClassLoader.getSystemResource("explosion/exCone.gif").toString());
		explo4 = new Image(ClassLoader.getSystemResource("explosion/exSmall.gif").toString());

		bullet = new Image(ClassLoader.getSystemResource("bullet/bullet.png").toString());
		missile = new Image(ClassLoader.getSystemResource("bullet/missile.gif").toString());
		bossBullet = new Image(ClassLoader.getSystemResource("bullet/bossBullet.gif").toString());
		roundBulletB = new Image(ClassLoader.getSystemResource("bullet/roundBulletB.png").toString());
		roundBulletY = new Image(ClassLoader.getSystemResource("bullet/roundBulletY.png").toString());
		roundBulletR = new Image(ClassLoader.getSystemResource("bullet/roundBulletR.png").toString());
		roundBulletP = new Image(ClassLoader.getSystemResource("bullet/roundBulletP.png").toString());

		background = new Image(ClassLoader.getSystemResource("background/bg2.png").toString());

		bgm = new AudioClip(ClassLoader.getSystemResource("song/Corneria.wav").toExternalForm());
		laser = new AudioClip(ClassLoader.getSystemResource("song/laser.wav").toExternalForm());
		laser.setVolume(0.35);
	}

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		/*
		 * for(IRenderable x: entities){
		 *
		 * might use to check our entity in the future
		 *
		 * 
		 * }
		 */
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public void clear() {
		entities.clear();
	}

}
