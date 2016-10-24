package com.shixia.diudiuma.rx;

import android.content.Context;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by AmosShi on 2016/10/21.
 * Description:
 */

public class RxBus {

    private Action1 mSubscriber = new Action1() {
        @Override
        public void call(Object o) {

        }
    };

    private RxBus(){

    }

    public Observable getDefault(){
        /*return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        })*/
        return null;
    }

    public void regiest(Context context){
        /*mSubscriber.add(new Subscription() {
            @Override
            public void unsubscribe() {

            }

            @Override
            public boolean isUnsubscribed() {
                return false;
            }
        });*/
    }

    public void post(){

    }

    public void unRegiest(Context context){

    }
}
