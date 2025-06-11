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

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder> {
    private List<ShiftSchedule> shiftSchedules;
    private Context context;

    public ShiftAdapter(List<ShiftSchedule> shiftSchedules) {
        this.shiftSchedules = shiftSchedules;
    }

    @Override
    public ShiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_shift_schedule, parent, false);
        return new ShiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShiftViewHolder holder, int position) {
        ShiftSchedule schedule = shiftSchedules.get(position);
        holder.bind(schedule);
    }

    @Override
    public int getItemCount() {
        return shiftSchedules.size();
    }

    class ShiftViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        LinearLayout layoutShiftSlots;

        public ShiftViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewShiftName);
            layoutShiftSlots = itemView.findViewById(R.id.layoutShiftSlots);
        }

        public void bind(ShiftSchedule schedule) {
            textViewName.setText(schedule.getWorkerName());

            layoutShiftSlots.removeAllViews(); // クリア

            // マスを作成
            for (int i = 0; i < 16; i++) {
                Button btn = new Button(context);
                btn.setLayoutParams(new LinearLayout.LayoutParams(49, 80)); // 幅100, 高さ100
                btn.setText(""); // 中身なし
                btn.setBackgroundColor(
                        schedule.getAssignment(i).isEmpty() ? 0xFFE0E0E0 : 0xFF81C784 // 空ならグレー、シフトありなら緑
                );
                layoutShiftSlots.addView(btn);
            }
        }
    }
}
