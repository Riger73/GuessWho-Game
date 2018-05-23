import java.util.*;
import java.io.*;

public class Attribute{
    public String Name;
    public String Value;
    
    public Attribute(String Name, String Value){
        this.Name= Name;
        this.Value = Value;
    }

    public String toString(){
        return DataHolder.ANSI_GREEN + Name + ": " + DataHolder.ANSI_RESET + Value;
    }

    public Guess toGuess(){
        return new Guess(Guess.GuessType.Attribute, Name, Value);
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(Name, Value);
    }
}