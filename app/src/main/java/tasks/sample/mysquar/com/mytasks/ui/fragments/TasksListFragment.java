package tasks.sample.mysquar.com.mytasks.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import tasks.sample.mysquar.com.mytasks.R;
import tasks.sample.mysquar.com.mytasks.data.TaskReposistory;
import tasks.sample.mysquar.com.mytasks.ui.list.TaskAdapter;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TasksListFragment extends Fragment {

    @BindView(R.id.taskslist)
    RecyclerView tasksList;

    TaskAdapter mTaskAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_task_list,container,false);
        ButterKnife.bind(this,root);
        init();
        return root;
    }

    private void init(){
        mTaskAdapter = new TaskAdapter();
        tasksList.setAdapter(mTaskAdapter);
        tasksList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        TaskReposistory.getInstance().loadAllTasks().subscribe(tasks -> {
            mTaskAdapter.setData(tasks);
        });

        TaskReposistory.getInstance().getDataChangeEvent().subscribe(
                task -> {
                    mTaskAdapter.addData(task);
                }
        );
    }



}
