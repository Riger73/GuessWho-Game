import java.io.*;
import java.util.*;

/**
 * Binary-search based guessing player.
 * This player is for task C.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class BinaryGuessPlayer implements Player
{

    private DataHolder gameData;
    private Character chosenCharacter;

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

    public BinaryGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {

        //Instantaite board and get chosen chararacter
        gameData = new DataHolder(gameFilename);
        chosenCharacter = gameData.getCharacterFromName(chosenName);

        //print the chosen character
        System.out.printf("Player has chosen: \n%s\n",chosenCharacter);

    } // end of BinaryGuessPlayer()


    /**
     * Gets the playes next guess
     */
    public Guess guess() {

        //if only one character left guess that character
        if (gameData.AllCharacters.size() == 1)
            return gameData.AllCharacters.get(0).toGuess();

        //get All attributes and sort
        Attribute[] AllAttributes = getAttributesInGame();
        int[] AttributeCount = CountAttributes(AllAttributes);

        //pick middle Attribute
        Attribute halfAttribute = getSplitAttribute(AllAttributes, AttributeCount);

        //return attribute as guess
        return halfAttribute.toGuess();
    } // end of guess()


    /**
     * Returns if an input guess is true or false
     * 
     * @param currGuess guess to evaluate
     * @return true if guess is correct else false
     */
	public boolean answer(Guess currGuess) {

        //Switch for character or attribute guesses
        switch(currGuess.getType()){

            //return if chosen play has that attribute
            case Attribute:
                return chosenCharacter.hasAttribute(currGuess.getAttribute(),currGuess.getValue());
            
            //return if chosen player is that person
            case Person:
                return chosenCharacter.Name.equals(currGuess.getValue());
        }//end switch

        //should never get here
        return false;
    } // end of answer()


    /**
     * How player responds to guess answer
     * 
     * @param currGuess the guess this player attempted
     * @param answer response to guess from other player
     * @param return if player has finished with game
     */
	public boolean receiveAnswer(Guess currGuess, boolean answer) {

        //if attribute type guess
        if (currGuess.getType().equals(Guess.GuessType.Attribute)){

            //remove all characters with attributes opposite of the response from the guess
            gameData.RemoveAllOfAttribute(new Attribute(currGuess.getAttribute(), currGuess.getValue()), !answer);

            //if all characters are removed (shouldn't be possible) respond no guesses left
            if (gameData.AllCharacters.size() < 1)
                return true;

            //keep guessing
            return false;
        
        //if player guess
        }else{
            //if false keep guessing, else they finish guessing since they know the character
            return answer;
        }
    } // end of receiveAnswer()

     //returns a list of all attributes left in game
     private Attribute[] getAttributesInGame(){

        //declar retVal as array list
        List<Attribute> retVal = new ArrayList<Attribute>();

        //loops through all attributeson all characters
        for (Character character : gameData.AllCharacters) {     
            for (Attribute characterAttribute : character.CharacterAttributes) {
                if (!retVal.contains(characterAttribute))
                    retVal.add(characterAttribute);
            }
        }
        return retVal.toArray(new Attribute[retVal.size()]);
    }

    /**
     * returns count of all of the attributes in the game
     */
    private int[] CountAttributes(Attribute[] input){
        //array to hold count
        int[] retVal = new int[input.length];

        //loop through all characters and count up the attributes
        for (Character character : gameData.AllCharacters) {
            for (int i = 0; i < input.length; i++)
                if (character.hasAttribute(input[i]))
                    retVal[i]++;
        }
        return retVal;
    }

    /**
     * Finds the attribute splits characters into sizes, or closest
     * 
     * NOTE: both arrays must be the same size
     * 
     * @param allAttributes attributes to check from;
     * @param Count individual count of all attributes to check.
     * @return Attribute which affects half the characters (or closest possible)
     */
    private Attribute getSplitAttribute(Attribute[] allAttributes, int[] count){

        //return value
        Attribute retVal = null;

        

        //Half of current Characters
        int Target = (int)gameData.AllCharacters.size() /2;
        //difference between target and current retVal, starts at all Characters since can't be bigger than this
        int closestDifference = gameData.AllCharacters.size();

        //for each value possible for that attribute
        for (int i = 0; i < count.length; i++){

            //amount of characters with that attribute
            int totalAmount = count[i] ; 
                
            //how far off an even split this is
            int difference = Math.abs(Target - totalAmount);

            //if a better split set this to be best guess
            if (difference < closestDifference){
                closestDifference = difference;
                retVal = allAttributes[i];
            }

            //can't get better than zero so just return this
            if (difference == 0)
                return retVal;
        }//end for

        //return best guess
        return retVal;
    }//end getSplitAttribute()

} // end of class BinaryGuessPlayer
