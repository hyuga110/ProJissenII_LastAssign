package jp.ac.gifu_u.info.onishi.shiftscheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Worker {
    private String name;
    private String job;
    private Map<String, List<Integer>> shiftHopes;

    public Worker(String name, String job) {
        this.name = name;
        this.job = job;
        this.shiftHopes = new HashMap<>();
    }

    // --- ゲッター & セッター ---
    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Map<String, List<Integer>> getShiftHopes() {
        return shiftHopes;
    }

    public void setShiftHopes(Map<String, List<Integer>> shiftHopes) {
        this.shiftHopes = shiftHopes;
    }

    public void addHope(String date, int hourIndex) {
        if (!shiftHopes.containsKey(date)) {
            shiftHopes.put(date, new ArrayList<>());
        }
        if (!shiftHopes.get(date).contains(hourIndex)) {
            shiftHopes.get(date).add(hourIndex);
        }
    }

    public void removeHope(String date, int hourIndex) {
        if (shiftHopes.containsKey(date)) {
            shiftHopes.get(date).remove((Integer) hourIndex);
        }
    }

    // --- JSON 変換 ---
    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("job", job);

        // shiftHopes を JSON に変換
        JSONObject hopesJson = new JSONObject();
        for (String date : shiftHopes.keySet()) {
            JSONArray hourArray = new JSONArray(shiftHopes.get(date));
            hopesJson.put(date, hourArray);
        }
        obj.put("hopes", hopesJson);

        return obj;
    }

    public static Worker fromJson(JSONObject obj) throws JSONException {
        Worker worker = new Worker(obj.getString("name"), obj.getString("job"));

        if (obj.has("hopes")) {
            JSONObject hopesJson = obj.getJSONObject("hopes");
            Iterator<String> keys = hopesJson.keys();
            while (keys.hasNext()) {
                String date = keys.next();
                JSONArray hoursArray = hopesJson.getJSONArray(date);
                List<Integer> hours = new ArrayList<>();
                for (int i = 0; i < hoursArray.length(); i++) {
                    hours.add(hoursArray.getInt(i));
                }
                worker.getShiftHopes().put(date, hours);
            }
        }

        return worker;
    }
}