package jp.ac.gifu_u.info.onishi.shiftscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class WorkerListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WorkerAdapter adapter;
    private List<Worker> workerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);

        recyclerView = findViewById(R.id.recyclerViewWorkers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ダミーデータを作成
        workerList = new ArrayList<>();
        Worker w1 = new Worker("田中　太郎", "アルバイト");
        Worker w2 = new Worker("佐藤 花子", "正社員");
        Worker w3 = new Worker("鈴木 一郎", "パート");
        workerList.add(w1);
        workerList.add(w2);
        workerList.add(w3);

        adapter = new WorkerAdapter(workerList);
        recyclerView.setAdapter(adapter);
    }
}
