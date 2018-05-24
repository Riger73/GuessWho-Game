import java.util.*;

/**
 * Class to hold data about characters
 */
public class Character{

    //Character Name
    public String Name;
    //List of Attributes
    public List<Attribute> CharacterAttributes;

    /**
     * Constructer
     */
    public Character(){
        Name = "";
        CharacterAttributes = new ArrayList<Attribute>();
    }

    /**
     * return character as a readable string
     */
    public String toString(){

        String retVal = DataHolder.ANSI_GREEN + Name + ": \n" + DataHolder.ANSI_RESET;

        for (Attribute attribute : CharacterAttributes) {
            retVal += attribute.toString() + "\n";
        }
        return retVal;
    
    }

    /**
     * returns if a character has that attribute
     */
    public boolean hasAttribute(Attribute attribute){
        for (Attribute characterAttribute : CharacterAttributes) {
            if (attribute.equals(characterAttribute))
                return true;
        }

        return false;
    }

    /**
     * overload for has Attribute
     */
    public boolean hasAttribute(String Name, String Value){   
        return hasAttribute(new Attribute(Name,Value));
    }

    /**
     * Converts character to guess
     */
    public Guess toGuess(){
        return new Guess(Guess.GuessType.Person,"",Name);
    }

}