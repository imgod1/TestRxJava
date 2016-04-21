package com.example.gaokang.testrxjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;

    private static Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello RxJava");
        }
    });

    private Subscriber<String> stringSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Snackbar.make(toolbar, "Subscriber onCompleted：", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Snackbar.make(toolbar, "Subscriber onNext：" + s, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            System.out.println("Subscriber onNext：" + s);
        }
    };

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
                stringObservable.subscribe(stringSubscriber);
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }
}
