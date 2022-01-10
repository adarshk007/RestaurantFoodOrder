package src;

import java.util.ArrayList;  
import java.util.List;  
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.*;
import java.io.*;

class Driver{

	public static String fileRead() throws java.lang.Exception{
		Scanner sc = new Scanner(new File("input.txt"));
		sc.useDelimiter("\\Z"); 
		String content = sc.next();
		return content;
	}

	public static HashMap<String,List<String>> generateMap() {
		HashMap<String,List<String>> newMap = new HashMap<String,List<String>>();
		newMap.put("distance",new ArrayList<String>());
		newMap.put("orderId",new ArrayList<String>());
		newMap.put("meals",new ArrayList<String>());
		return newMap;
	}

	public static String[] getArr(String d){
    	return d.split(",");
	}

	public static String removeBracket(String d){
    	return d.replaceAll("[\\[\\]\\\"]","");
	}

    public static void main (String[] args) throws java.lang.Exception
	{
		//File read
		String content = fileRead();

		//Data formatting
		List<HashMap<String, List<String>>> data = new ArrayList<>();
		JSONArray list = new JSONArray(content);
		for(Object i: list){
			HashMap<String, List<String>> newMap = generateMap();
			String mealsArr[] = getArr( 
				removeBracket(((JSONObject)i).optString("meals"))
			);
			newMap.get("distance").add(((JSONObject)i).optString("distance"));
			newMap.get("orderId").add(((JSONObject)i).optString("orderId"));
			newMap.put("meals",Arrays.asList(mealsArr));
			data.add(newMap);
		}

		// Order started
		OrdersService order = new OrdersService();
		order.takeOrders(data);
	}
}