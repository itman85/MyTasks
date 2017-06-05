package tasks.sample.mysquar.com.mytasks.data;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import tasks.sample.mysquar.com.mytasks.model.Task;

import static tasks.sample.mysquar.com.mytasks.data.AppDB.getDatabase;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TaskReposistory {
    static TaskReposistory _instance;

    private PublishSubject<Task> taskSubject = PublishSubject.create();

    public static TaskReposistory getInstance() {
        if (_instance == null) {
            _instance = new TaskReposistory();
        }
        return _instance;
    }

    public Observable<List<Task>> loadAllTasks() {
        return Observable.<List<Task>>create(subscriber -> {
            subscriber.onNext(getDatabase().taskDAO().loadAllTasks());
            subscriber.onCompleted();
        })
//                .flatMap(tasks -> {
//            if (tasks == null || tasks.size() == 0) {
//                AppDB.getDatabase().taskDAO().insertOrReplaceTask(TASKS);
//                return Observable.just(TASKS);
//            }
//            return Observable.just(tasks);
//        })
                .subscribeOn(Schedulers.io());
    }

    public Observable<Void> saveTask(Task task) {
        return Observable.<Void>create(subscriber -> {
            getDatabase().taskDAO().insertOrReplaceTask(Arrays.asList(task));
            subscriber.onNext(null);
            subscriber.onCompleted();
        }).doOnNext(aVoid -> taskSubject.onNext(task))
                .subscribeOn(Schedulers.io());
    }

    public Observable<Void> deleteTask(Task task) {
        return Observable.<Void>create(subscriber -> {
            getDatabase().taskDAO().deleteTask(task);
            subscriber.onNext(null);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

    public Observable<Void> deleteTasks(List<Task> tasks) {
        return Observable.<Void>create(subscriber -> {
            getDatabase().taskDAO().deleteTask(tasks);
            subscriber.onNext(null);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

    public Observable<Task> loadTaskById(String taskId){
        return Observable.<Task>create(subscriber -> {
            Task task = AppDB.getDatabase().taskDAO().loadTaskById(taskId);
            subscriber.onNext(task);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }
    public Observable<Task> observeTask() {
        return taskSubject;
    }
}