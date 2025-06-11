package jp.ac.gifu_u.info.onishi.shiftscheduler;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WorkerListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WorkerAdapter adapter;
    private List<Worker> workerList;

    private static final String PREF_NAME = "WorkerPrefs";
    private static final String KEY_WORKERS = "workerList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);

        recyclerView = findViewById(R.id.recyclerViewWorkers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 保存済みのデータを読み込む
        loadWorkerList();

        // コールバック付きのアダプタに変更！
        adapter = new WorkerAdapter(workerList, new WorkerAdapter.OnWorkerChangedListener() {
            @Override
            public void onWorkerChanged() {
                saveWorkerList();  // 編集・削除時に保存
            }
        });
        recyclerView.setAdapter(adapter);

        Button buttonAddWorker = findViewById(R.id.buttonAddWorker);
        buttonAddWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddWorkerDialog();
            }
        });
    }
    private void showAddWorkerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("従業員を追加");

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_worker, null);
        builder.setView(dialogView);

        EditText editName = dialogView.findViewById(R.id.editWorkerName);
        Spinner spinnerType = dialogView.findViewById(R.id.spinnerWorkerType);

        // スピナーに職種の選択肢を追加
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(
                this, R.array.worker_types, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterSpinner);

        builder.setPositiveButton("追加", (dialog, which) -> {
            String name = editName.getText().toString().trim();
            String type = spinnerType.getSelectedItem().toString();
            if (!name.isEmpty()) {
                workerList.add(new Worker(name, type));
                adapter.notifyItemInserted(workerList.size() - 1);
                saveWorkerList();   // データを保存
            }
        });

        builder.setNegativeButton("キャンセル", null);
        builder.show();
    }

    // 保存
    private void saveWorkerList() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        JSONArray jsonArray = new JSONArray();
        for (Worker worker : workerList) {
            try {
                jsonArray.put(worker.toJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        editor.putString(KEY_WORKERS, jsonArray.toString());
        editor.apply();
    }

    // 読込
    private void loadWorkerList() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json = prefs.getString(KEY_WORKERS, null);

        workerList = new ArrayList<>();

        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    workerList.add(Worker.fromJson(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
