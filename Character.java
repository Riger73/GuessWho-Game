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

}