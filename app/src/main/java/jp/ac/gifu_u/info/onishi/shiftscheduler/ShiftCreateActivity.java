package jp.ac.gifu_u.info.onishi.shiftscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShiftCreateActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShiftAdapter adapter;
    private Button btnAutoCreateShift;

    private List<ShiftSchedule> shiftSchedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_create);

        recyclerView = findViewById(R.id.recyclerViewShift);
        btnAutoCreateShift = findViewById(R.id.btnAutoCreateShift);
        LinearLayout layoutTimeLabels = findViewById(R.id.layoutTimeLabels);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 2時間ごとにラベル作成 (6:00〜22:00)
        for (int i = 0; i < 64; i += 8) { // 15分 x 8 = 2時間
            TextView timeLabel = new TextView(this);
            timeLabel.setLayoutParams(new LinearLayout.LayoutParams(400, LinearLayout.LayoutParams.WRAP_CONTENT));
            int hour = 6 + (i / 4);
            timeLabel.setText(String.format("%02d", hour));
            timeLabel.setPadding(8, 8, 8, 8);
            layoutTimeLabels.addView(timeLabel);
        }

        // 仮のデータを作成
        shiftSchedules = new ArrayList<>();
        shiftSchedules.add(new ShiftSchedule("田中"));
        shiftSchedules.add(new ShiftSchedule("佐藤"));
        shiftSchedules.add(new ShiftSchedule("高橋"));

        adapter = new ShiftAdapter(shiftSchedules);
        recyclerView.setAdapter(adapter);

        btnAutoCreateShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoAssignShifts();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private  void autoAssignShifts() {
        // ダミーの自動シフト割り当て：ランダムに何個か入れるだけ
        for (ShiftSchedule schedule : shiftSchedules) {
            for (int i = 0; i < 64; i++) {
                if (Math.random() < 0.1) { // 10%の確率でシフトを入れる
                    schedule.assign(i, "勤務");
                }
            }
        }
    }
}
