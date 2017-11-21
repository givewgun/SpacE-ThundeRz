package input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.scene.input.KeyCode;

public class CharacterInput {
	// TODO read plz
	//use polling technique
	
	/**!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * Gun->I think we have no need to use set to check weather we have pressed the key or not
	 *  because if we pressed the key for some time the spaceship will constantly move 
	 *!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>(); 
	
	public static final char NO_CHARACTER = Character.MIN_VALUE;

	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}

	public static void setKeyPressed(KeyCode keycode,boolean pressed) {
		if(pressed){
			if(!keyPressed.contains(keycode)){
				keyPressed.add(keycode);
			}
		}else{
			keyPressed.remove(keycode);
		}
		System.out.println(keyPressed);
	}
	
	
	//might need when starting/reseting a new game
	public static void clear(){
		keyPressed.clear();
	}

}
