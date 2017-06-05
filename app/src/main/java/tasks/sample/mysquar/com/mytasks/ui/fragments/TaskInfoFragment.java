package tasks.sample.mysquar.com.mytasks.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import tasks.sample.mysquar.com.mytasks.R;
import tasks.sample.mysquar.com.mytasks.data.TaskReposistory;
import tasks.sample.mysquar.com.mytasks.model.Task;
import tasks.sample.mysquar.com.mytasks.ui.activity.TaskDetailActivity;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TaskInfoFragment extends Fragment  {
    @BindView(R.id.add_task_title)
    EditText titleEdt;
    @BindView(R.id.add_task_description)
    EditText descriptionEdt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_add_task, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String taskid = ((TaskDetailActivity)getActivity()).taskId();
        if(taskid!=null && !"".equals(taskid)){
            TaskReposistory.getInstance().loadTaskById(taskid)
                    .filter(task -> task!=null)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(task -> {
                        titleEdt.setText(task.getName());
                        descriptionEdt.setText(task.getContent());
                    });
        }
    }

    public Observable<Void> actionSaveNewTask() {
        return TaskReposistory.getInstance().saveTask(Task.create(UUID.randomUUID().toString(), titleEdt.getText().toString(), descriptionEdt.getText().toString()))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> actionUpdateTask(String taskId) {
        return TaskReposistory.getInstance().saveTask(Task.create(taskId, titleEdt.getText().toString(), descriptionEdt.getText().toString()))
                .observeOn(AndroidSchedulers.mainThread());
    }

}
