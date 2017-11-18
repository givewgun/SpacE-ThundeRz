package input;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CharacterInput {
	// TODO read plz
	//use polling technique
	
	/**!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * Gun->I think we have no need to use set to check weather we have pressed the key or not
	 *  because if we pressed the key for some time the spaceship will constantly move 
	 *!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	
	private static Queue<Character> pressedCharacterQueue = new ConcurrentLinkedQueue<>();
	//private static Set<Character> pressedCharacters = new HashSet<>();
	public static final char NO_CHARACTER = Character.MIN_VALUE;

	public static char pollPressedCharacter() {
		
	Character key = pressedCharacterQueue.poll();
		if(key != null) {
			return key;
		}
		return NO_CHARACTER;
	}

	public static void addPressedCharacter(char c) {
		// TODO fill code
		//if(!pressedCharacters.contains(c)) {
		pressedCharacterQueue.add(c);
			//pressedCharacters.add(c);
		//}

	}
	
	

	
	public static void removePressedCharacter(char c) {
		// TODO fill code
		/*Do we really need to use this method 5555 as 
		 * 
		 * 						       pressing->...->then->release key
		 * will automatically push     key->key->...->then->null          
		 * 
		 * into the queue and the pollPressedCharacter() method
		 * will handle them for us
		 */
		pressedCharacterQueue.remove(c);
	}
	
	//might need when starting/reseting a new game
	public static void clear(){
		pressedCharacterQueue.clear();
	}

}
