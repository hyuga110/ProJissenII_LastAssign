package jp.ac.gifu_u.info.onishi.shiftscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class EditHopeDayActivity extends  AppCompatActivity {
    private RecyclerView recyclerView;
    private HopeAdapter adapter;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hope_day);

        recyclerView = findViewById(R.id.recyclerViewHope);
        btnSave = findViewById(R.id.btnSave);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ダミーデータ作成
        List<HopeSchedule> schedules = new ArrayList<>();
        schedules.add(new HopeSchedule("小新"));
        schedules.add(new HopeSchedule("土屋"));
        schedules.add(new HopeSchedule("高橋 愛果"));

        adapter = new HopeAdapter(schedules);
        recyclerView.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            // 保存処理（今はダミー）
        });
    }
}
