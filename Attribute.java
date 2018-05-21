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
}