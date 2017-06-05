package tasks.sample.mysquar.com.mytasks.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import tasks.sample.mysquar.com.mytasks.R;
import tasks.sample.mysquar.com.mytasks.data.TaskReposistory;
import tasks.sample.mysquar.com.mytasks.model.Task;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TaskInfoFragment extends Fragment {
    @BindView(R.id.add_task_title)
    EditText titleEdt;
    @BindView(R.id.add_task_description)
    EditText descriptionEdt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_add_task,container,false);
        ButterKnife.bind(this,root);

        return root;
    }

    public Observable<Void> actionSaveTask(){
        return TaskReposistory.getInstance().saveTask(new Task(System.currentTimeMillis(),titleEdt.getText().toString(),descriptionEdt.getText().toString()));
    }
}
