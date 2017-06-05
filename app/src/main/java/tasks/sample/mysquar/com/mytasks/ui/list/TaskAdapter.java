package tasks.sample.mysquar.com.mytasks.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tasks.sample.mysquar.com.mytasks.R;
import tasks.sample.mysquar.com.mytasks.model.Task;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    public interface TaskItemEvent {
        void onCheckTaskItemEvent(boolean isChecked, Task task);
        void onClickTaskItemEvent(Task task);
    }

    private List<Task> data;

    private TaskItemEvent mTaskItemEvent;

    public TaskAdapter() {
        this.data = new ArrayList<>();
    }

    public void setTaskItemEvent(TaskItemEvent taskItemEvent) {
        this.mTaskItemEvent = taskItemEvent;
    }

    public void setData(List<Task> dataList) {
        data.clear();
        data.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addData(Task task) {
        int i = data.indexOf(task);
        if(i>=0){
            data.get(i).copy(task);
            notifyItemChanged(i);
        }else{
            data.add(0, task);
//        notifyItemChanged(0);
            notifyDataSetChanged();
        }
    }

    public void removeData(List<Task> taskList){
        boolean isDirty = false;
        for(Task task:taskList){
            isDirty = data.remove(task) || isDirty;
        }
        if(isDirty){
            notifyDataSetChanged();
        }
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_layout, parent, false);
        return new TaskViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

        TextView taskNameTxt;
        CheckBox taskCheckbox;
        Task mItem;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskNameTxt = (TextView) itemView.findViewById(R.id.title);
            taskCheckbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            taskNameTxt.setOnClickListener(this);
            taskCheckbox.setOnCheckedChangeListener(this);
        }

        public void bind(Task item) {
            taskNameTxt.setText(item.getName());
            taskCheckbox.setChecked(item.isChecked());
            mItem = item;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), mItem.toString(), Toast.LENGTH_SHORT).show();
            mTaskItemEvent.onClickTaskItemEvent(mItem);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mTaskItemEvent.onCheckTaskItemEvent(isChecked, mItem);
        }
    }
}
