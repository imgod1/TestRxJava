package com.example.gaokang.testrxjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;

    //第一种平常的写法
    private Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello RxJava");
        }
    });

    private Subscriber<String> stringSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.e("main", "Subscriber onCompleted");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Snackbar.make(toolbar, "Subscriber onNext：" + s, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            Log.e("main", "Subscriber onNext：" + s);
        }
    };

    //第二种,比较精简一点的写法,不过监听者使用了方法1的观察者
    //这样的写法会使这个事件源发送一次数据之后就结束了
    //作为他的监听者,会先响应onNext,然后onComplete
    Observable observable1 = Observable.just("hello im just");


    //第三种,使用action
    Action1<String> action1 = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.e("main", "action1 onNext：" + s);
        }
    };

    //第四种,使用匿名Action,较为简单
//    Observable.just("Hello no name").subscribe(new Action1<String>() {
//        @Override
//        public void call(String s) {
//            Log.e("main", "action1 onNext：" + s);
//        }
//    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                stringObservable.subscribe(stringSubscriber);//方法1
//                observable1.subscribe(stringSubscriber);//方法2
//                stringObservable.subscribe(action1);//方法3
                Observable.just("Hello no name").subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("main", "action1 onNext：" + s);
                    }
                });
            }
        });

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }
}
