package jp.ac.gifu_u.info.onishi.shiftscheduler;

import java.util.HashMap;
import java.util.Map;
public class Shift {
    private  Map<String, String> shifts;    // 日付 -> 従業員名

    public Shift() {
        shifts = new HashMap<>();
    }

    public void assignShift(String date, String workerName) {
        shifts.put(date, workerName);
    }

    public String getAssignedWorker(String date) {
        return shifts.get(date);
    }

    public Map<String, String> getAllShifts() {
        return shifts;
    }
}
