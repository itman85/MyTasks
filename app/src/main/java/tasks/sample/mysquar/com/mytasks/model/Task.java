package tasks.sample.mysquar.com.mytasks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by phannguyen on 6/2/17.
 */
@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey
    private long id;
    private String name;
    private String content;

    public Task(long id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString(){
        return "Task " + name + " : " + content;
    }
}
