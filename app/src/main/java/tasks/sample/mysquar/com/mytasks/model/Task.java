package tasks.sample.mysquar.com.mytasks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by phannguyen on 6/2/17.
 */
@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey
    private String id;
    private String name;
    private String content;

    @Ignore
    private boolean isChecked = false;

    public Task() {
    }

    public static Task create(String id, String name, String content) {
        return new Task(id, name, content);
    }

    private Task(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Task){
            return ((Task) obj).getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void copy(Task task){
        this.setContent(task.getContent());
        this.setName(task.getName());
    }
}
