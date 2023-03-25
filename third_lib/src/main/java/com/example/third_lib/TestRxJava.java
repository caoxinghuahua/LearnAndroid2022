package com.example.third_lib;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class TestRxJava {
    Disposable disposable;

    private final String TAG = this.getClass().getSimpleName();
    private static TestRxJava instance;

    private TestRxJava() {

    }

    public static TestRxJava getInstance() {
        if (instance == null) {
            synchronized (TestRxJava.class) {
                if (instance == null) {
                    instance = new TestRxJava();
                }
            }
        }

        return instance;
    }

    public void test() {
        String[] strArray = new String[]{"A", "B", "C"};
        disposable = Observable.fromArray(strArray).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

                Log.d("myObserver", "Consumer accept:" + s);
            }
        });
        //kotlin中不能.subscribe
        Observable.fromArray(strArray).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("myObserver", "onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("myObserver", "onNext:" + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("myObserver", "onError");

            }

            @Override
            public void onComplete() {
                Log.d("myObserver", "onComplete ");

            }
        });

    }


    public void testFlatMap() {
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 采用flatMap（）变换操作符
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });


    }


    public void testBuffer() {
        //  缓存被观察者发送的事件
        Observable.just(1, 2, 3, 4)
                .buffer(2, 1)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.d(TAG, "buffer accept size:" + integers.size());
                        for (int num : integers) {
                            Log.d(TAG, "buffer accept num:" + num);
                        }
                    }
                });
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept size:2
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept num:1
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept num:2
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept size:2
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept num:2
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept num:3
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept size:2
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept num:3
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept num:4
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept size:1
//        2022-09-07 16:52:02.527 4470-4470/com.example.third_lib D/TestRxJava: buffer accept num:4
    }

    /**
     * https://blog.csdn.net/carson_ho/article/details/78455349
     */
    public void testConcatAndMerge() {

        //concat<=4    concatArray>4  合并顺序执行
        Observable.concat(Observable.just(1, 2), Observable.just(3, 4), Observable.just(5, 6))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d(TAG, "ConcatAndMerge concat onNext num:" + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //merger<=4 mergerArray

        Observable.merge(Observable.intervalRange(0, 3, 2, 2, TimeUnit.SECONDS),
                Observable.intervalRange(20, 3, 3, 2, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Log.d(TAG, "ConcatAndMerge  merger onNext num:" + aLong);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "ConcatAndMerge  merger onComplete ");
                    }
                });


        //zip  对位合并事件

        Observable.zip(Observable.just(1, 3, 5, 7), Observable.just(0, 2, 4, 6, 8), new BiFunction<Integer, Integer, String>() {

            @NonNull
            @Override
            public String apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                return "zip结果:" + (integer + integer2);
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "ConcatAndMerge  zip onNext result:" + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        //delay  //do事件，事件的生命周期中操作

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    emitter.onNext("测试delay操作符");
                    emitter.onError(new Throwable("发生错误了"));
                }

        )
//                .delay(3, TimeUnit.SECONDS)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "ConcatAndMerge  doOnNext s:" + s);

                    }
                })
                .doAfterNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "ConcatAndMerge  doAfterNext s:" + s);

                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "ConcatAndMerge  doOnError thowable:" + throwable.toString());

                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "ConcatAndMerge  doAfterTerminate");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "ConcatAndMerge  doFinally");
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d(TAG, "ConcatAndMerge  delay onNext result:" + s);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "ConcatAndMerge  delay onError :" + e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        2022-09-08 10:37:45.703 5528-5528/com.example.third_lib D/TestRxJava: ConcatAndMerge  doOnNext s:测试delay操作符
//        2022-09-08 10:37:45.703 5528-5528/com.example.third_lib D/TestRxJava: ConcatAndMerge  delay onNext result:测试delay操作符
//        2022-09-08 10:37:45.703 5528-5528/com.example.third_lib D/TestRxJava: ConcatAndMerge  doAfterNext s:测试delay操作符
//        2022-09-08 10:37:45.703 5528-5528/com.example.third_lib D/TestRxJava: ConcatAndMerge  doOnError thowable:java.lang.Throwable: 发生错误了
//        2022-09-08 10:37:45.703 5528-5528/com.example.third_lib D/TestRxJava: ConcatAndMerge  delay onError :java.lang.Throwable: 发生错误了
//        2022-09-08 10:37:45.703 5528-5528/com.example.third_lib D/TestRxJava: ConcatAndMerge  doFinally
//        2022-09-08 10:37:45.703 5528-5528/com.example.third_lib D/TestRxJava: ConcatAndMerge  doAfterTerminate


    }

    /**
     * 过滤操作符
     */
    public void testFilter() {
        //filter
        Observable.just(1, 2, 3, 4, 5)
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(@NonNull Integer integer) throws Exception {
//                        return integer > 3;
//                    }
//                })
//                .take(2)
//                .firstElement()
//                .elementAt(6, 9)
                .elementAtOrError(6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "testFilter  accept result:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "testFilter  error result:" + throwable.toString());

                    }
                });
    }

    public void clear() {
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
    }
}
