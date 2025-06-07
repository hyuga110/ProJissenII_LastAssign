package jp.ac.gifu_u.info.onishi.shiftscheduler;

public class HopeSchedule {
    private String workerName;
    private boolean[] hopes;    // 6:00〜22:00の15分単位 → 64マス

    public HopeSchedule(String workerName) {
        this.workerName = workerName;
        this.hopes = new boolean[64];
    }

    public String getWorkerName() {
        return workerName;
    }

    public boolean getHope(int index) {
        return hopes[index];
    }

    public void setHope(int index, boolean hope) {
        hopes[index] = hope;
    }

    public int getHopeCount() {
        return hopes.length;
    }
}
