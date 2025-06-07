package jp.ac.gifu_u.info.onishi.shiftscheduler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnWorkerInfo;
    private Button btnCreateShift;
    private Button btnSavedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ボタンをIDで取得
        btnWorkerInfo = findViewById(R.id.btnWorkerInfo);
        btnCreateShift = findViewById(R.id.btnCreateShift);
        btnSavedData = findViewById(R.id.btnSavedData);

        //クリックリスナー設定
        btnWorkerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WorkerListActivity.class);
                startActivity(intent);
            }
        });

        btnCreateShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShiftCreateActivity.class);
                startActivity(intent);
            }
        });

        btnSavedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SavedShiftListActivity.class);
                startActivity(intent);
            }
        });
    }
}