import java.util.*;
import java.io.*;

/**
 * Class to hold an Attribute Type and Value
 */
public class Attribute{

    //Name of Attribute Type
    public String Name;

    //Value of that Attribute
    public String Value;
    
    /**
     * Constructer to create attribute
     */
    public Attribute(String Name, String Value){
        this.Name= Name;
        this.Value = Value;
    }

    /**
     * Returns attribute as a readable string
     */
    public String toString(){
        return DataHolder.ANSI_GREEN + Name + ": " + DataHolder.ANSI_RESET + Value;
    }

    /**
     * Converts attribute to a string
     */
    public Guess toGuess(){
        return new Guess(Guess.GuessType.Attribute, Name, Value);
    }


    /**
     * Override the equals method to just check Name and value
     */
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Attribute)) {
            return false;
        }
        Attribute other = (Attribute) o;
        return  Objects.equals(Name, other.Name) &&
                Objects.equals(Value, other.Value);
    }

    /**
     * Hash override to match equals override
     */
    @Override
    public int hashCode() {
        return Objects.hash(Name, Value);
    }
}