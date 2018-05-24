import java.io.*;
import java.util.*;

public class DataHolder
{

    //Colors for teminal
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    //list of all Attributes types in game
    private List<AttributeType> AllAttributeTypes;

    //list of all current characters in game
    public List<Character> AllCharacters;
    


 /**
     * Loads the game configuration from gameFilename.
     *
     * @param gameFilename Filename of game configuration.
     * @throws IOException If there are IO issues with loading of gameFilename.
     */
    public DataHolder(String configFile) throws IOException{

        //instantiate lists
        AllAttributeTypes = new ArrayList<AttributeType>();
        AllCharacters = new ArrayList<Character>();

        //after the first empty line this switches to true
        Boolean hasFinishedAttributes = false;
        //Character being created
        Character currentChar = new Character();

        //create bufferreader and string to read to
        BufferedReader br = new BufferedReader(new FileReader(configFile));
        String line;
        
        //while reading through file
        while ((line = br.readLine()) != null) {
            
            //if line is empty start saving characters
            if (line.trim().isEmpty())
                hasFinishedAttributes = true;

            //if saving attributes Create attribute else create character
            if (!hasFinishedAttributes)
                CreateAttribute(line);
            else
                currentChar = CreatePlayer(line, currentChar);

        }//end while

        //finish last character
        CreatePlayer("", currentChar);

        //close buffer reader
        br.close();
    }//end constructor


    /**
     * Parses string to create an Attribute
     * 
     * @param line input String to parse
     */
    private void CreateAttribute(String line){

        //split line by spaces
        String[] values =  line.split("\\s+");

                //if there is at least to values it is a valid attribute
                if (values.length > 1)
                    AllAttributeTypes.add(new AttributeType(values));

                //else print err
                else
                    System.err.println("\"" + line +"\" is not a valid Attribute input");
                
    }//end CreateAttribute()

    /**
     * Parses strings until an empty is recieved to create a character
     * 
     *  @param line String to parse
     *  @param currentChar current character being built
     *  @return updated character needs to be returned since character is defined over multiple lines  
     */
    private Character CreatePlayer(String line, Character currentChar){

        //if empty line and character has a name
        if (line.trim().isEmpty() &&  !currentChar.Name.isEmpty()){

            //add to list and return an empty character
            AllCharacters.add(currentChar);
            return new Character();

        //else if character doesn't have a name give it a name and return
        }else if (!line.trim().isEmpty() && currentChar.Name.isEmpty()){
            currentChar.Name = line;

            //return updated character
            return currentChar;

        //else if has a name parse line as attribute
        }else if (!line.trim().isEmpty()){

            //split line by spaces
            String[] newAttribute = line.split("\\s+");

            //if 2 values use to create an attribute
            if (newAttribute.length == 2){
                //create attribute
                Attribute tempAttribute = new Attribute(newAttribute[0],newAttribute[1]);

                //check if that has been defined as a valid attribute and add
                if (isValidAttribute(tempAttribute))
                    currentChar.CharacterAttributes.add(tempAttribute);

                
                    
            //else if not 2 print err
            } else{
                System.err.printf("\n(%s)%s is not a valid attribute%s",line,ANSI_RED,ANSI_RESET);    
            }

            //return updated character
            return currentChar;
        }

        //return same character if something went wrong like multiple gaps were left
        return currentChar;
    }//end CreatePlayer()

    /**
     * Checks if an attribute was defined in config file
     * 
     * @param input Attribute to check
     * @param return If attribute was defin in config file
     */
    public boolean isValidAttribute(Attribute input) {
        
        //loop through all AttributeTypes and checks if input is part of them
        for (AttributeType type : AllAttributeTypes) {

            //if found return true
            if (type.contains(input))
                return true;
        }

        //not found return false
        System.err.printf("\n(%s)%s is not a valid attribute%s",input,ANSI_RED,ANSI_RESET);
        return false;
    }//end isValidAttribute


    /**
     * Gets a Character from a string name
     * 
     * @param Name string to look for
     * @return Character with that Name
     */
    public Character getCharacterFromName(String Name){

        //Loop through all Characters and return if found
        for (Character character : AllCharacters) {
            if (character.Name.equals(Name))
            return character;

        }

        //else return empty
        System.err.printf("\n(%s)%s is not a valid Character%s",Name,ANSI_RED,ANSI_RESET);
        return null;
    }//end getCharacterFromName

    /**
     * Removes all characters which have (or doesn't have) this attribute
     * 
     * @param attribute attribute to filter with
     * @param hasAttribute if true remove characters with this attribute else remove characters without it
     */
    public void RemoveAllOfAttribute(Attribute attribute, Boolean hasAttribute){

        //Create a copy of all Characters to loop through
        Character[] AllCharacterCopy = AllCharacters.toArray(new Character[AllCharacters.size()]);

        //print which characters are being removed
        System.out.printf("%sRemoving: %s",ANSI_GREEN,ANSI_RESET);

        //loop through all charactyers
        for (Character character : AllCharacterCopy) {

            //if meets criterea remove character
            if (character.hasAttribute(attribute) == hasAttribute){
                System.out.printf("%s, ",character.Name);
                AllCharacters.remove(character);

            }
        }

        //print how many characters are remainging
        System.out.print("\n");
        System.out.printf("%sRemaining: %s%s\n",ANSI_GREEN,ANSI_RESET,AllCharacters.size());
    }//end RemoveAllOfAttribute()


}
    
    
    