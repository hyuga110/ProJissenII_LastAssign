package jp.ac.gifu_u.info.onishi.shiftscheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {
    private List<Worker> workerList;
    private Context context;
    private OnWorkerChangedListener listener;

    public WorkerAdapter(List<Worker> workerList, OnWorkerChangedListener listener) {
        this.workerList = workerList;
        this.listener = listener;
    }

    @Override
    public WorkerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_worker, parent, false);
        return new WorkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkerViewHolder holder, int position) {
        Worker worker = workerList.get(position);
        holder.textViewName.setText(worker.getName());
        holder.textViewJob.setText(worker.getJob());

        holder.buttonEdit.setOnClickListener(v -> showEditDialog(position));
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    public class WorkerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewJob;
        Button buttonEdit;

        public WorkerViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewJob = itemView.findViewById(R.id.textViewJob);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
        }
    }

    private void showEditDialog(int position) {
        Worker worker = workerList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("従業員を編集");

        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_worker, null);
        builder.setView(dialogView);

        EditText editName = dialogView.findViewById(R.id.editWorkerName);
        Spinner spinnerType = dialogView.findViewById(R.id.spinnerWorkerType);

        editName.setText(worker.getName());

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(
                context, R.array.worker_types, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterSpinner);

        int typePosition = adapterSpinner.getPosition(worker.getJob());
        spinnerType.setSelection(typePosition);

        builder.setPositiveButton("保存", (dialog, which) -> {
            String newName = editName.getText().toString().trim();
            String newType = spinnerType.getSelectedItem().toString();

            if (!newName.isEmpty()) {
                worker.setName(newName);
                worker.setJob(newType);
                notifyItemChanged(position);

                // saveWorkerList()をコールバック
                if (listener != null) {
                    listener.onWorkerChanged();
                }
            }
        });

        builder.setNegativeButton("キャンセル", null);
        builder.show();
    }

    public interface OnWorkerChangedListener {
        void onWorkerChanged();  // 編集・削除・追加時に呼ぶ
    }

}