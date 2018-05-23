import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Binary-search based guessing player.
 * This player is for task C.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class BinaryGuessPlayer implements Player
{
    /**
     * Loads the game configuration from gameFilename, and also store the chosen
     * person.
     *
     * @param gameFilename Filename of game configuration.
     * @param chosenName Name of the chosen person for this player.
     * @throws IOException If there are IO issues with loading of gameFilename.
     *    Note you can handle IOException within the constructor and remove
     *    the "throws IOException" method specification, but make sure your
     *    implementation exits gracefully if an IOException is thrown.
     */
	

    private Person[] chosenCharacter;
    protected Person chosen;
    private static ArrayList<String> knownAttr = new ArrayList<String>();
    HashMap<String, ArrayList<String>> gameData = new HashMap<String, ArrayList<String>>();
    protected static String[] attributes = { "name", "hairLength", "glasses", "facialHair", "eyeColor",
    "pimples", "hat", "hairColor", "noseShape", "faceShape" };

    
    HashMap<String, Integer> setCnt = new HashMap<String, Integer>();
    
    protected int playerNum = 0;
    
    public BinaryGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {

        gameData = DataInit.LoadValues(gameFilename);
        chosenCharacter = DataInit.LoadData(gameFilename);
        
        for (int i = 0; i < chosenCharacter.length; i++) {
            if ((chosenCharacter[i].get("name")).equals(chosenName)) {
                chosen = chosenCharacter[i];
                chosenCharacter = DataInit.LoadData(gameFilename);

            }
            playerNum++;
        }
        
    } // end of BinaryGuessPlayer()


    public Guess guess() {

        // placeholder, replace
        int players = 0;

        for (int i = 0; i < chosenCharacter.length; i++) {
            if (chosenCharacter[i] != null) {
                players++;
                for (int j = 0; j < attributes.length; j++) {
                    String value = attributes[j];
                    String attribute = chosenCharacter[i].get(value);

                    String keys = value + " " + attribute;
                    if (!setCnt.containsKey(keys)) {
                    	setCnt.put(keys, 1);
                    } else {
                        int cnt = setCnt.get(keys);
                        cnt++;
                        setCnt.put(keys, cnt);
                    }
                }
            }
        }
        if (players != 1) {
        	//Array for attribute occurences.
            int[] attribOccur = new int[players]; 
            int valCnt;
            
            for (String key : setCnt.keySet()) {
            	valCnt = setCnt.get(key);
                for(int i = 0; i < attribOccur.length; i++) {
                    if(attribOccur[i] == 0) {
                        attribOccur[i] = valCnt; 
                        i = attribOccur.length;
                    }
                }
            }
            
            int targ = players/2;
            int difference = Math.abs(attribOccur[0] - targ); 
            int idx = 0;
            
            for(int i = 0; i < attribOccur.length; i++) {
                int similarity = Math.abs(attribOccur[i] - targ);
                if(similarity < difference) {
                    idx = i;
                    difference = similarity;
                }
            }
            for (String key : setCnt.keySet()) {
                if (setCnt.get(key).equals(targ) && !knownAttr.contains(key)) {
                    String[] split = key.split(" ");
                    knownAttr.add(key);
                    setCnt.clear();
                    return new Guess(Guess.GuessType.Attribute, split[0], split[1]);
                }
                
            }

            for (String key : setCnt.keySet()) {
                if (setCnt.get(key).equals(attribOccur[idx]) && !knownAttr.contains(key)) {
                    String[] split = key.split(" ");
                    knownAttr.add(key);
                    setCnt.clear();
                    return new Guess(Guess.GuessType.Attribute, split[0], split[1]);
                }
            }
            
        } else {

            for (int i = 0; i < chosenCharacter.length; i++) {
                if (chosenCharacter[i] != null) {
                    setCnt.clear();
                    return new Guess(Guess.GuessType.Person, "", chosenCharacter[i].get("name"));

                }
            }
        }
            
        return null;
    } // end of guess()


	public boolean answer(Guess currGuess) {

        if (currGuess.getType().equals(Guess.GuessType.Attribute)) {
            if (currGuess.getValue().equals(chosen.get(currGuess.getAttribute()))) {
                return true;
            } else {
                return false;
            }
        } else {
            if (chosen.get("name").equals(currGuess.getValue())) {
                return true;
            } else {
                return false;
            }
        }
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {
        if (currGuess.getType().equals(Guess.GuessType.Attribute)) {
            if (answer == false) {

                for (int i = 0; i < chosenCharacter.length; i++) {
                    if (chosenCharacter[i] != null) {
                        String attribute = currGuess.getAttribute();
                        String value = currGuess.getValue();
                        if ((chosenCharacter[i].get(attribute)).equals(value)) { 
                            chosenCharacter[i] = null;

                        }
                    }
                }
            } else {

                for (int i = 0; i < chosenCharacter.length; i++) {
                    if (chosenCharacter[i] != null) {
                        if (!currGuess.getValue().equals(chosenCharacter[i].get(currGuess.getAttribute()))) {
                            chosenCharacter[i] = null;

                        }
                    }
                }
            }
            return false;
        } else {
            if (answer == true) {
                return true;
            } else {

                for (int i = 0; i < chosenCharacter.length; i++) {
                    if ((chosenCharacter[i].get("name")).equals(currGuess.getValue())) {
                        chosenCharacter[i] = null;

                    }
                    
                }
            }
        }
        return false;
    } // end of receiveAnswer()

} // end of class BinaryGuessPlayer
