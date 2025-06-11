package jp.ac.gifu_u.info.onishi.shiftscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ShiftCreateActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShiftAdapter adapter;
    private Button btnAutoCreateShift;
    private LinearLayout timeLabels;

    private List<ShiftSchedule> shiftSchedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_create);

        recyclerView = findViewById(R.id.recyclerViewShift);
        btnAutoCreateShift = findViewById(R.id.btnAutoCreateShift);
        timeLabels = findViewById(R.id.timeLabels);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 時間ラベルの表示（6時〜22時、2時間おき）
        renderTimeLabels();

        // 仮の従業員データを作成
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

    private void renderTimeLabels() {
        timeLabels.removeAllViews();

        TextView empty = new TextView(this);
        empty.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.WRAP_CONTENT));  // ←名前の幅に合わせる
        empty.setText("");
        timeLabels.addView(empty);

        for (int i = 0; i < 16; i++) {
            TextView label = new TextView(this);
            label.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.WRAP_CONTENT));

            if (i % 2 == 0) {
                int hour = 6 + i;
                label.setText(String.valueOf(hour));
            } else {
                label.setText(""); // 2時間ごとにだけ表示
            }

            timeLabels.addView(label);
        }
    }

    private void autoAssignShifts() {
        // 仮のシフト割り当て：10%の確率で勤務
        for (ShiftSchedule schedule : shiftSchedules) {
            for (int i = 0; i < 16; i++) {
                if (Math.random() < 0.1) {
                    schedule.assign(i, "勤務");
                } else {
                    schedule.assign(i, "");
                }
            }
        }
    }
}
