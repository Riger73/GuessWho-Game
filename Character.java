import java.util.*;

public class Character{


    public String Name;
    public List<Attribute> CharacterAttributes;

    public Character(){
        Name = "";
        CharacterAttributes = new ArrayList<Attribute>();
    }

    public String toString(){

        String retVal = DataHolder.ANSI_GREEN + Name + ": \n" + DataHolder.ANSI_RESET;

        for (Attribute attribute : CharacterAttributes) {
            retVal += attribute.toString() + "\n";
        }
        return retVal;
    
    }

    public boolean hasAttribute(Attribute attribute){
        for (Attribute characterAttribute : CharacterAttributes) {
            if (attribute.equals(characterAttribute))
                return true;
        }

        return false;
    }

    public boolean hasAttribute(String Name, String Value){   
        return hasAttribute(new Attribute(Name,Value));
    }

    public Guess toGuess(){
        return new Guess(Guess.GuessType.Person,"",Name);
    }

}