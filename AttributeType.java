import java.util.*;
import java.io.*;

/**
 * Class to hold all possible values of an Attribute
 */
public class AttributeType{

    //name of group
    public String Name;
    //all possible values
    public String[] Values;

    /**
     * Constructer
     */
    public AttributeType(String Name, String[] Values){
        this.Name = Name;
        this.Values = Values;
    }

    /**
     * Constructer which just uses the first value of the array as the name
     */
    public AttributeType(String[] Values){
            this(Values[0],Arrays.copyOfRange(Values, 1, Values.length));
    }

    /**
     * Checks if an Attribute is part of this group
     */
    public Boolean contains(Attribute input){

        boolean hasValue = false;

        for (String val : Values) {
            if (val.equals(input.Value)){
                hasValue = true;
                break;
            }
        }
        return(Name.equals(input.Name)) && hasValue;
    }

    /**
     * Returns AttributeType as an readable string
     */
    public String toString(){

        String retVal = DataHolder.ANSI_GREEN + Name + ": " + DataHolder.ANSI_RESET;

        for (String value : Values) {
            retVal += value + ", ";
        }
        retVal = retVal.substring(0, retVal.length() - 2);
        return retVal;
    }

}