import java.io.*;
import java.util.*;

public class DataHolder
{

    //Colors for teminal
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public List<AttributeType> AllAttributeTypes; 
    public List<Character> AllCharacters;
    



    public DataHolder(String configFile) throws IOException{

        AllAttributeTypes = new ArrayList<AttributeType>();
        AllCharacters = new ArrayList<Character>();

        BufferedReader br = new BufferedReader(new FileReader(configFile));
        String line;
        Boolean hasFinishedAttributes = false;
        
        Character currentChar = new Character();

        while ((line = br.readLine()) != null) {
            
            if (line.trim().isEmpty()){
                hasFinishedAttributes = true;
            }

            if (!hasFinishedAttributes){
                CreateAttribute(line);
            }else{
                currentChar = CreatePlayer(line, currentChar);
            }


        }//end while

        CreatePlayer("", currentChar);

        br.close();
    }//end constructor


    private void CreateAttribute(String line){
        String[] values =  line.split("\\s+");

                if (values.length > 1){
                    AllAttributeTypes.add(new AttributeType(values));
                }else{
                    System.err.println("\"" + line +"\" is not a valid Attribute input");
                }
    }

    private Character CreatePlayer(String line, Character currentChar){
        if (line.trim().isEmpty() &&  !currentChar.Name.isEmpty()){
                AllCharacters.add(currentChar);

            return new Character();

        }else if (!line.trim().isEmpty() && currentChar.Name.isEmpty()){
            currentChar.Name = line;
            return currentChar;
        }else if (!line.trim().isEmpty()){
            String[] newAttribute = line.split("\\s+");

            if (newAttribute.length == 2){
                Attribute tempAttribute = new Attribute(newAttribute[0],newAttribute[1]);
                if (isValidAttribute(tempAttribute))
                    currentChar.CharacterAttributes.add(tempAttribute);
                else
                    System.err.printf("\n(%s)%s is not a valid attribute%s",tempAttribute,ANSI_RED,ANSI_RESET);         
            } 
            return currentChar;
        }
        return currentChar;
    }

        
    private boolean isValidAttribute(Attribute input) {
        
        for (AttributeType type : AllAttributeTypes) {
            if (type.contains(input))
                return true;
        }
        return false;
    }

    public Character getCharacterFronName(String Name){

        for (Character character : AllCharacters) {
            if (character.Name.equals(Name))
            return character;

        }
        return null;
    }

    public void RemoveAllOfAttribute(Attribute attribute, Boolean hasAttribute){

        Character[] AllCharacterCopy = AllCharacters.toArray(new Character[AllCharacters.size()]);

        System.out.printf("%sRemoving: %s",ANSI_RED,ANSI_RESET);
        for (Character character : AllCharacterCopy) {
            if (character.hasAttribute(attribute) == hasAttribute){
                System.out.printf("%s, ",character.Name);
                AllCharacters.remove(character);

            }
        }
        System.out.print("\n");
        System.out.printf("%sRemaining: %s%s\n",ANSI_RED,ANSI_RESET,AllCharacters.size());
    }


}
    
    
    