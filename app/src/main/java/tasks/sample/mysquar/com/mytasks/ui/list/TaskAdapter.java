package tasks.sample.mysquar.com.mytasks.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tasks.sample.mysquar.com.mytasks.R;
import tasks.sample.mysquar.com.mytasks.model.Task;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private List<Task> data;

    public TaskAdapter() {
        this.data = new ArrayList<>();
    }

    public void setData(List<Task> dataList){
        data.clear();
        data.addAll(dataList);
        notifyDataSetChanged();
    }
    public void addData(Task task){
        data.add(0,task);
        notifyItemChanged(0);
    }
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_layout,parent,false);
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

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView taskNameTxt;
        Task mItem;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskNameTxt = (TextView)itemView.findViewById(R.id.title);
            taskNameTxt.setOnClickListener(this);
        }

        public void bind(Task item){
            taskNameTxt.setText(item.getName());
            mItem = item;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),mItem.toString(),Toast.LENGTH_SHORT).show();

        }
    }
}
