package jp.ac.gifu_u.info.onishi.shiftscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HopeAdapter extends RecyclerView.Adapter<HopeAdapter.HopeViewHolder> {
    private List<HopeSchedule> hopeSchedules;
    private Context context;

    public HopeAdapter(List<HopeSchedule> hopeSchedules) {
        this.hopeSchedules = hopeSchedules;
    }

    @Override
    public HopeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_hope_schedule, parent, false);
        return new HopeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HopeViewHolder holder, int position) {
        HopeSchedule schedule = hopeSchedules.get(position);
        holder.bind(schedule);
    }

    @Override
    public int getItemCount() {
        return hopeSchedules.size();
    }

    class HopeViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        LinearLayout layoutTimeSlots;

        public HopeViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            layoutTimeSlots = itemView.findViewById(R.id.layoutTimeSlots);
        }

        public void bind(HopeSchedule schedule) {
            textViewName.setText(schedule.getWorkerName());

            layoutTimeSlots.removeAllViews(); // クリア

            // 64マスを作成
            for (int i = 0; i < 64; i++) {
                Button btn = new Button(context);
                btn.setLayoutParams(new LinearLayout.LayoutParams(100, 100)); // 幅100、高さ100
                btn.setText(""); // 文字なし
                btn.setBackgroundColor(schedule.getHope(i) ? 0xFF90CAF9 : 0xFFE0E0E0); // ON:青, OFF:グレー
                int index = i;

                btn.setOnClickListener(v -> {
                    boolean current = schedule.getHope(index);
                    schedule.setHope(index, !current);
                    btn.setBackgroundColor(!current ? 0xFF90CAF9 : 0xFFE0E0E0); // 色更新
                });

                layoutTimeSlots.addView(btn);
            }
        }
    }
}
