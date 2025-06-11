package jp.ac.gifu_u.info.onishi.shiftscheduler;

public class ShiftSchedule {
    private String workerName;
    private String[] assignedTimes; // 6:00〜22:00、15分刻み（64コマ）

    public ShiftSchedule(String workerName) {
        this.workerName = workerName;
        this.assignedTimes = new String[16]; // 64コマ
        for (int i = 0; i < 16; i++) {
            assignedTimes[i] = ""; // 最初は空
        }
    }

    public String getWorkerName() {
        return workerName;
    }

    public void assign(int index, String shift) {
        assignedTimes[index] = shift;
    }

    public String getAssignment(int index) {
        return assignedTimes[index];
    }
}
