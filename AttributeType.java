import java.util.*;
import java.io.*;

public class AttributeType{


    public String Name;
    public String[] Values;

    public AttributeType(String Name, String[] Values){
        this.Name = Name;
        this.Values = Values;

        System.out.println("Created: " + this.toString());

    }

    public AttributeType(String[] Values){
            this(Values[0],Arrays.copyOfRange(Values, 1, Values.length));
    }

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

    public String toString(){

        String retVal = DataHolder.ANSI_GREEN + Name + ": " + DataHolder.ANSI_RESET;

        for (String value : Values) {
            retVal += value + ", ";
        }
        retVal = retVal.substring(0, retVal.length() - 2);
        return retVal;
    }
}