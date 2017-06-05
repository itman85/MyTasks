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
    TasksListFragment tasksListFragment;
    private int mode = 1;//1: add, 2: delete
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupView();
    }

    private void setupView(){
        tasksListFragment = new TasksListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame,tasksListFragment);
        fragmentTransaction.commit();

        addTaskBtn.setOnClickListener(v->{
            if(this.mode == 1) {
                Intent intent = new Intent(MainActivity.this,TaskDetailActivity.class);
                startActivity(intent);
            } else if(this.mode == 2) {
               tasksListFragment.removeTaskAction();
            }

        });

    }

    public void changeFloatButtonState(int mode){
        this.mode = mode;
        if(this.mode == 1)
            addTaskBtn.setImageResource(R.drawable.ic_add);
        else if(this.mode == 2)
            addTaskBtn.setImageResource(R.drawable.ic_delete);
    }
}
