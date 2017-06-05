package tasks.sample.mysquar.com.mytasks.data;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import tasks.sample.mysquar.com.mytasks.model.Task;

/**
 * Created by phannguyen on 6/2/17.
 */

public class TaskReposistory {
    static TaskReposistory _instance;
    private static List<Task> TASKS = Arrays.asList(new Task(1,"Title1", "Description1"),
            new Task(2,"Title2", "Description2"));

    PublishSubject<Task> taskSubject = PublishSubject.create();
    public static TaskReposistory getInstance(){
        if(_instance == null){
            _instance = new TaskReposistory();
        }
        return _instance;
    }

    public Observable<List<Task>> loadAllTasks() {
        return Observable.create(new Observable.OnSubscribe<List<Task>>() {
            @Override
            public void call(Subscriber<? super List<Task>> subscriber) {
                subscriber.onNext(AppDB.getDatabase().taskDAO().loadAllTasks());
            }
        }).flatMap(tasks -> {
            if(tasks ==null || tasks.size()==0){
                AppDB.getDatabase().taskDAO().insertOrReplaceTask(TASKS);
                return Observable.just(TASKS);
            }
            return Observable.just(tasks);
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<Void> saveTask(Task task){
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                AppDB.getDatabase().taskDAO().insertOrReplaceTask(Arrays.asList(task));
                subscriber.onNext(null);
            }
        }).doOnNext(aVoid -> {
            taskSubject.onNext(task);
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<Task> getDataChangeEvent(){
        return taskSubject.observeOn(AndroidSchedulers.mainThread());
    }
}
