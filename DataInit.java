/*
 * Initialises and loads data values
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataInit{
    //Array to store attribute types.
    protected static String[] attributes = {"hairLength", "glasses", "facialHair", "eyeColor", "pimples",
	    		"hat", "hairColor", "noseShape", "faceShape"};
    private static List<String> attList = Arrays.asList(attributes);
	    
    public static Person[] LoadData(String gameFilename) throws IOException{
        // Declare/Initialise variables
        int lines = 0;
        int counter = 0;
        String line;
        Person[] people;
        // Instantiate file input stream
        File config = new File(gameFilename);
        // Checks for data file to initialise
        if(!(config.exists())){
            System.out.println("[!] Game file not found!");
            return null;
        }
        // Instantiate buffered readers
        BufferedReader confData = new BufferedReader(new FileReader(config));
        BufferedReader loadData = new BufferedReader(new FileReader(config));
        // Counts lines to match attributes
        while((line = confData.readLine()) != null){
            if(lines % 11 == 0 & lines != 11){		
                counter++;
            }
            lines++;
        }

        // Initialise empty array for character data
        people = new Person[counter];
        // Clears used values
        boolean cleared = false;
        counter = 0;

        // Reading and storing the characters;
        while((line = loadData.readLine()) != null){
            if(cleared == true){	
                String[] temp;
                // Init attributes
                String name = line;	
					
                temp = (loadData.readLine()).split("\\s+");
                String hairlength = temp[1];
                
                temp = (loadData.readLine()).split("\\s+");
                String glasses = temp[1];
                
                temp = (loadData.readLine()).split("\\s+");
                String facialhair = temp[1];	
                
                temp = (loadData.readLine()).split("\\s+");
                String eyecolor = temp[1];	

                temp = (loadData.readLine()).split("\\s+");
                String pimples = temp[1];	

                temp = (loadData.readLine()).split("\\s+");
                String hat = temp[1];	

                temp = (loadData.readLine()).split("\\s+");
                String haircolor = temp[1];	

                temp = (loadData.readLine()).split("\\s+");
                String noseshape = temp[1];

                temp = (loadData.readLine()).split("\\s+");
                String faceshape = temp[1];	

                Person person = new Person(name, hairlength, glasses, facialhair, eyecolor, pimples, hat, 
                		haircolor, noseshape, faceshape);

                people[counter] = person;
                counter++;
                  
                loadData.readLine();
            }else {
                for(int i = 0; i < 9; i++){
                    loadData.readLine();
                }
                cleared = true;
            }
        }
        // Close data streams
        confData.close();
        loadData.close();
        
        return people;
    }

    public static HashMap<String, ArrayList<String>> LoadValues(String gameFilename) throws IOException {
        // Declare/initialise variable
        String line;
        String key = null;
        boolean players = false;
        HashMap<String, ArrayList<String>> map;
        // Instantiating file stream
        File config = new File(gameFilename);
        
        // Checks checks for file
        if(!(config.exists())){
            System.out.println("[LOADER] Config file: " + gameFilename + " not found."); 
        }
        map = new HashMap<String, ArrayList<String>>();
        // Instantiate buffer reader
        BufferedReader confData = new BufferedReader(new FileReader(config));
        while((line = confData.readLine()) != null){		
            String[] tokens = line.split(" ");
            ArrayList<String> valueList = new ArrayList<String>();
            for(String token : tokens) {
            // Validate and assign key to token.
            if(attList.contains(token)) {
                key = token;
            }
            //Stop loader if player exists already else add new player token to list.
            else if(token.equals("P1")) {
                players = true;
                break;
            } else {
                valueList.add(token);
            }
        }
        if(players == true) {
            break;
        }
        //Checks for key and then inserts key in empty spot
        if(!map.containsKey(key)) {
            map.put(key, valueList);
        }
    }
        confData.close();
        return map;
    }   
}
