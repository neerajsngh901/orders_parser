package com.neeraj.orders_parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Service
public class OderService {
    Long id= Long.valueOf(1);
    static JSONArray array = new JSONArray();
    public JSONArray getOderJson(){
        Long b=getCSVDATA(id);
        array.addAll(getJSONDATA(b));
        return array;
    }
    private static Long getCSVDATA(Long id){
        JSONArray jsonArray=new JSONArray();
        try{
        String line = "";
        String splitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader("orders1.csv"));
        Long lineno = Long.valueOf(0);

        while ((line = br.readLine()) != null)
        {
            String[] order = line.split(splitBy);
            if (lineno > 0) {
            jsonArray.add(getResult(Long.valueOf(order[0]), Double.valueOf(order[1]), order[3], "orders1.csv", lineno, id));
               id++;
            }
            lineno++;
        }
        }catch (Exception e){
            System.out.println("Exception:"+e.getMessage());
        }
        array.addAll(jsonArray);
        return id;
    }
    private static JSONArray getJSONDATA(Long id){
        JSONArray jsonArrayList=new JSONArray();
        try{
            JSONParser jsonParser = new JSONParser();
            File file = new File("orders2.json");
            Object object = jsonParser.parse(new FileReader(file));
            JSONArray jsonArray= (JSONArray) object;
            Long linejson= Long.valueOf(0);
            for (Object o : jsonArray) {
                JSONObject person = (JSONObject) o;
                jsonArrayList.add(getResult((Long) person.get("orderId"),(Double) person.get("amount"),(String) person.get("comment"),"orders2.json",linejson,id));
                linejson++;
                id++;
            }
        } catch (Exception e){
            System.out.println("Exception:"+e.getMessage());
        }
        return jsonArrayList;
    }
    private static JSONObject getResult(Long orderId, Double amount, String comment, String filename, Long line, Long id){
        JSONObject item = new JSONObject();
        item.put("orderId", orderId);
        item.put("amount", amount);
        item.put("comment", comment);
        item.put("filename", filename);
        item.put("line", line);
        item.put("result", "OK");
        item.put("id",id);
        return item;

    }

}
