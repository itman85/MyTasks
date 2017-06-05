package tasks.sample.mysquar.com.mytasks;

import android.app.Application;

import tasks.sample.mysquar.com.mytasks.data.AppDB;

/**
 * Created by phannguyen on 6/2/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDB.init(this);
    }
}
