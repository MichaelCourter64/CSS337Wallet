import java.util.*;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class WalletIntractionMap {
	static JSONObject obj = new JSONObject();
	static HashMap <String, Long> WalletMap = new HashMap<String, Long>();
	
	//Default Constructor
	public WalletIntractionMap() {
		obj.put("WalletMap", new HashMap<String, Long>());
		obj.toString();
	}
	
	public static boolean updateEntry(String name){
		Iterator itr = WalletMap.keySet().iterator();
		while(itr.hasNext()) {
			String key = (String) itr.next();
			if(key == name)
				WalletMap.put(key, WalletMap.get(key)+1);
			return true;
		}
		WalletMap.put(name, (long) 0);
		return true;
	}
	
	public static long getCounter(String name){
		Iterator itr = WalletMap.keySet().iterator();
		while(itr.hasNext()) {
			String key = (String) itr.next();
			if(key == name)
				return WalletMap.get(key);
		}
		return -1;
	}

}
