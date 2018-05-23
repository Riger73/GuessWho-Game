import java.io.*;
import java.util.*;



/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer implements Player
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
    public RandomGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {

        gameData = new DataHolder(gameFilename);
        chosenCharacter = gameData.getCharacterFronName(chosenName);

        System.out.printf("Player has chosen: \n%s",chosenCharacter);
    } // end of RandomGuessPlayer()


    public Guess guess() {

        //getRandom Character

        if (gameData.AllCharacters.size() == 1){
            return gameData.AllCharacters.get(0).toGuess();
        }else if (gameData.AllCharacters.size() < 1)
            return new Guess(Guess.GuessType.Person, "", "I have no one left to guess");


        Random rand = new Random();

        Attribute[] possibleAttributes = getAttributesInGame();
        Attribute guess = possibleAttributes[(int)rand.nextInt(possibleAttributes.length)];

        return guess.toGuess();
    } // end of guess()


    public boolean answer(Guess currGuess) {

        switch(currGuess.getType()){

            case Attribute:
                return chosenCharacter.hasAttribute(currGuess.getAttribute(),currGuess.getValue());
            case Person:
                return chosenCharacter.Name.equals(currGuess.getValue());
        }


        // placeholder, replace
        return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {
        if (currGuess.getType().equals(Guess.GuessType.Attribute)){
            gameData.RemoveAllOfAttribute(new Attribute(currGuess.getAttribute(), currGuess.getValue()), !answer);
            return false;
        }else{
            return answer;
        }
    } // end of receiveAnswer()


    private Attribute[] getAttributesInGame(){

        List<Attribute> retVal = new ArrayList<Attribute>();

        for (Character character : gameData.AllCharacters) {     
            for (Attribute characterAttribute : character.CharacterAttributes) {
                if (!retVal.contains(characterAttribute))
                    retVal.add(characterAttribute);
            }
        }
        return retVal.toArray(new Attribute[retVal.size()]);
    }

} // end of class RandomGuessPlayer
