package tasks.sample.mysquar.com.mytasks.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import tasks.sample.mysquar.com.mytasks.R;
import tasks.sample.mysquar.com.mytasks.ui.fragments.TasksListFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab_add_task)
    FloatingActionButton addTaskBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupView();
    }

    private void setupView(){
        TasksListFragment tasksListFragment = new TasksListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame,tasksListFragment);
        fragmentTransaction.commit();

        addTaskBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,TaskDetailActivity.class);
            startActivity(intent);

        });

    }
}
