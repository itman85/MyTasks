package tasks.sample.mysquar.com.mytasks.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import tasks.sample.mysquar.com.mytasks.R;
import tasks.sample.mysquar.com.mytasks.data.TaskReposistory;
import tasks.sample.mysquar.com.mytasks.model.Task;
import tasks.sample.mysquar.com.mytasks.ui.activity.MainActivity;
import tasks.sample.mysquar.com.mytasks.ui.activity.TaskDetailActivity;
import tasks.sample.mysquar.com.mytasks.ui.list.TaskAdapter;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TasksListFragment extends Fragment implements TaskAdapter.TaskItemEvent {

    private static final String TAG = TasksListFragment.class.getSimpleName();
    @BindView(R.id.taskslist)
    RecyclerView tasksList;

    TaskAdapter mTaskAdapter;

    private List<Task> selectedTasks = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_task_list, container, false);
        ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        mTaskAdapter = new TaskAdapter();
        mTaskAdapter.setTaskItemEvent(this);
        tasksList.setAdapter(mTaskAdapter);
        tasksList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        TaskReposistory.getInstance()
                .loadAllTasks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasks -> {
                    mTaskAdapter.setData(tasks);
                }, throwable -> {
                    Log.w(TAG, throwable);
                });

        TaskReposistory.getInstance()
                .observeTask()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(task -> {
                    mTaskAdapter.addData(task);
                }, throwable -> {
                    Log.w(TAG, throwable);
                });
    }

    @Override
    public void onCheckTaskItemEvent(boolean isChecked, Task task) {
        if (isChecked) {
            selectedTasks.add(task);
        } else {
            selectedTasks.remove(task);
        }
        if (selectedTasks.size() > 0)
            ((MainActivity) this.getActivity()).changeFloatButtonState(2);
        else
            ((MainActivity) this.getActivity()).changeFloatButtonState(1);
    }

    @Override
    public void onClickTaskItemEvent(Task task) {
        Intent intent = new Intent(getActivity(),TaskDetailActivity.class);
        intent.putExtra("taskid",task.getId());
        startActivity(intent);
    }

    public void removeTaskAction() {
        TaskReposistory.getInstance().deleteTasks(selectedTasks)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    mTaskAdapter.removeData(selectedTasks);
                    selectedTasks.clear();
                    ((MainActivity) this.getActivity()).changeFloatButtonState(1);
                    Toast.makeText(this.getActivity(), "Delete successfully", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Log.w(TAG, throwable);
                });
    }
}