package tasks.sample.mysquar.com.mytasks.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import tasks.sample.mysquar.com.mytasks.model.Task;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by phannguyen on 6/2/17.
 */
@Dao
public interface TaskDAO {
    @Query("select * from tasks order by id desc")
    List<Task> loadAllTasks();

    @Query("select * from tasks where id = :id")
    Task loadTaskById(String id);

    @Insert(onConflict = REPLACE)
    void insertOrReplaceTask(List<Task> tasks);

    @Delete
    void deleteTask(Task task);

    @Delete
    void deleteTask(List<Task> tasks);
}
