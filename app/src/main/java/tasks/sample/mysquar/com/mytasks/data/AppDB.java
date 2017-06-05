package tasks.sample.mysquar.com.mytasks.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import tasks.sample.mysquar.com.mytasks.model.Task;

/**
 * Created by phannguyen on 6/2/17.
 */

@Database(entities = {Task.class}, version = 2)
public abstract class AppDB extends RoomDatabase {
    private static AppDB INSTANCE;
    private static Context _context;

    public abstract TaskDAO taskDAO();

    public static AppDB getDatabase() {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(_context.getApplicationContext(),
                            AppDB.class, "tasks_db").build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public static void init(Context context) {
        _context = context;
    }
}
