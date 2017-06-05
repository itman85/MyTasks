package tasks.sample.mysquar.com.mytasks.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import tasks.sample.mysquar.com.mytasks.R;
import tasks.sample.mysquar.com.mytasks.ui.fragments.TaskInfoFragment;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TaskDetailActivity extends AppCompatActivity {
    @BindView(R.id.fab_edit_task_done)
    FloatingActionButton actionTaskBtn;
    TaskInfoFragment taskInfoFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        taskInfoFragment = new TaskInfoFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame,taskInfoFragment);
        fragmentTransaction.commit();

        actionTaskBtn.setImageResource(R.drawable.ic_done);
        actionTaskBtn.setOnClickListener(v->{
            if(taskInfoFragment!=null){
                taskInfoFragment.actionSaveTask()
                .subscribe(aVoid -> Finish());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void Finish(){
        Toast.makeText(this,"Save task successfully",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(() -> finish(),500);

    }
}
