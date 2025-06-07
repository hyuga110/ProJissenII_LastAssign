package jp.ac.gifu_u.info.onishi.shiftscheduler;

import java.util.ArrayList;
import java.util.List;

public class Worker {
    private String name;
    private String job;
    private List<String> hopeDays;

    public Worker(String name, String job) {
        this.name = name;
        this.job = job;
        this.hopeDays = new ArrayList<>();
    }

    // --- ゲッター & セッター ---
    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public List<String> getHopeDays() {
        return hopeDays;
    }

    public void setHopeDays(List<String> hopeDays) {
        this.hopeDays = hopeDays;
    }

    public void addHopeDay(String day) {
        hopeDays.add(day);
    }
}
