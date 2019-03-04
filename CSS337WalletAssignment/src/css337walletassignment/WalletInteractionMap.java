package css337walletassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.HashMap;

import org.json.JSONObject;

public class WalletInteractionMap {
    static final String FILE_NAME = "WalletIdMap.json";
    
    static HashMap<String, Integer> walletMap = new HashMap<String, Integer>();

    public static void initializeMap() {
        try {
            File balanceFile = new File(FILE_NAME);
            if(balanceFile.exists()) {
                String jsonString = new String(Files.readAllBytes(Paths.get(FILE_NAME)));

                JSONObject jsonObject = new JSONObject(jsonString);

                JSONObject jsonMap = (JSONObject)jsonObject.get("walletMap");
                
                Iterator<String> idItrator = jsonMap.keys();
                
                while (idItrator.hasNext()) {
                    String id = idItrator.next();
                    walletMap.put(id, jsonMap.getInt(id));
                }
            }
            else {
                walletMap = new HashMap<String, Integer>();
                
                updateFile();
            }
        }
        catch (IOException ioe) {

        }
    }

    public static boolean updateEntry(String name){
        if (walletMap.containsKey(name)) {
            walletMap.put(name, walletMap.get(name) + 1);
        }
        else {
            walletMap.put(name, 0);
        }

        updateFile();

        return true;
    }

    public static int getCounter(String name){
        /*Iterator itr = walletMap.keySet().iterator();
        while(itr.hasNext()) {
            String key = (String) itr.next();
            if(key == name)
                return walletMap.get(key);
        }*/
        
        if (walletMap.containsKey(name)) {
            return walletMap.get(name);
        }
        else {
            return -1;
        }
    }

    private static void updateFile() {
        JSONObject walletMapJson = new JSONObject();
        walletMapJson.put("walletMap", walletMap);
        
        try (PrintWriter out = new PrintWriter(FILE_NAME)) {
            out.print(walletMapJson.toString());
        }
        catch (FileNotFoundException fnfe) {
            System.out.println(Arrays.toString(fnfe.getStackTrace()));
        }
    }
}
