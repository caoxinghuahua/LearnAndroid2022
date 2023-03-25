package com.example.third_lib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val TAG = "myObserver"

    var testRxJava: TestRxJava? = null
    var subscription: Subscription? = null
    var disposableList = ArrayList<Disposable>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var clickFlowable = findViewById<Button>(R.id.getFlowableBt)
        clickFlowable.setOnClickListener({
            subscription?.request(10)
        })

        //功能防抖
        var filterBt = findViewById<Button>(R.id.filterBt)
        RxView.clicks(filterBt)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe { Log.d(TAG, "click") }

        //联想搜索优化
        var searchEt = findViewById<EditText>(R.id.searchEt)
        RxTextView.textChanges(searchEt)
                .debounce(1, TimeUnit.SECONDS).skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CharSequence> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: CharSequence) {
                        Log.d(TAG, "要搜索的内容:$t")
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }

                })

//        createObservable()
//        createFlowable()
//        testMultiple()
//        learnOperator()
        testJava()
    }


    fun testJava() {
//      TestRxJava.getInstance().testFlatMap()
//      TestRxJava.getInstance().testBuffer()
//        TestRxJava.getInstance().testConcatAndMerge()
        TestRxJava.getInstance().testFilter()
    }


    /**
     *创建observable的几种方式
     * */
    private fun createObservable() {


        //create
//        Observable.create(ObservableOnSubscribe<Any> {
//             it.onNext("A")
//        })
        var myObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "myObserver onSubscribe")
            }

            override fun onNext(t: String) {
                Log.d(TAG, "myObserver onNext:$t")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "myObserver onError")

            }

            override fun onComplete() {
                Log.d(TAG, "myObserver onComplete")

            }

        }
        Observable.create(ObservableOnSubscribe<String> {
            //发射器发送事件
            emitter ->
            Log.d(TAG, "Observable subscribe")
            emitter.onNext("A")
            emitter.onComplete()

        }).subscribe(myObserver)

        //just 内部是调用fromArray实现
//        Observable.just("A", "B", "C")
//                .subscribe(myObserver)
        val array = arrayOf("D", "E", "F")


        var disposable = Observable.just("D").subscribe(object : Consumer<String> {
            override fun accept(t: String?) {
                Log.d(TAG, "Consumer accept:$t")
            }

        })
        disposableList.add(disposable)
//       Observable.fromArray(array).subscribe(object : Consumer<String> {
//            override fun accept(t: String?) {
//                Log.d(TAG, "Consumer accept:$t")
//            }
//
//        })


    }

    /**
     * 背压实现
     * https://www.jianshu.com/p/ceb48ed8719d
     * */
    fun createFlowable() {
//       syncCreate()
//        asyncCreate()

    }

    fun syncCreate() {
        val subscriber = object : Subscriber<String> {
            override fun onSubscribe(s: Subscription?) {
                Log.d(TAG, "myObserver subscriber onSubscribe")
                //通过观察者来控制流速
                s?.request(10)

            }

            override fun onNext(t: String?) {
                Log.d(TAG, "myObserver subscriber onNext:$t")
                Thread.sleep(500)
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG, "myObserver subscriber onError:$t")
            }

            override fun onComplete() {
                Log.d(TAG, "myObserver subscriber onComplete")
            }

        }

        //同步订阅
        Flowable.create(FlowableOnSubscribe<String> { emitter ->
            //同步订阅通过观察者的反馈控制被观察者发送事件数量,异步订阅由于处在不同线程所以不能
            val num = emitter.requested()
            for (i in 0 until num) {
                emitter.onNext("$i")
                Log.d(TAG, "myObserver subscriber Flowable")

                Thread.sleep(100);
            }


        }, BackpressureStrategy.ERROR)
                .subscribe(subscriber)
    }

    fun asyncCreate() {
        //异步订阅
        val subscriberAsync = object : Subscriber<String> {
            override fun onSubscribe(s: Subscription?) {
                Log.d(TAG, "myObserver subscriberAsync onSubscribe")
                //通过观察者来控制流速
//                s?.request(10)
                subscription = s
            }

            override fun onNext(t: String?) {
                Log.d(TAG, "myObserver subscriberAsync onNext:$t")
                Thread.sleep(500)
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG, "myObserver subscriberAsync onError:$t")
            }

            override fun onComplete() {
                Log.d(TAG, "myObserver subscriberAsync onComplete")
            }

        }
        Flowable.create(FlowableOnSubscribe<String> { emitter ->
            for (i in 0 until 200) {
                emitter.onNext("$i")
                Log.d(TAG, "myObserver subscriberAsync Flowable")
                Thread.sleep(100);
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriberAsync)

    }

    /**
     * 多个被观察者
     * */
    private fun testMultiple() {
        var multiObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "myObserver Multiple onSubscribe")
            }

            override fun onNext(t: String) {
                Log.d(TAG, "myObserver Multiple onNext:$t")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "myObserver Multiple onError")

            }

            override fun onComplete() {
                Log.d(TAG, "myObserver Multiple onComplete")

            }

        }
        //先observable1->第二个observable(遍历产生3个存入queue)1发送事件时依次遍历2中各事件
        Observable.create(ObservableOnSubscribe<Int> { emitter ->
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onNext(3)
            emitter.onComplete()
        }).flatMap(Function<Int, ObservableSource<String>> {

            var list = ArrayList<String>()
            for (i in 0..2) {
                list.add("我是事件" + it + "拆分后的子事件" + i)
            }
            Observable.fromIterable(list)
        }).subscribe(multiObserver)

        //运行结果
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onSubscribe
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件1拆分后的子事件0
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件1拆分后的子事件1
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件1拆分后的子事件2
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件2拆分后的子事件0
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件2拆分后的子事件1
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件2拆分后的子事件2
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件3拆分后的子事件0
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件3拆分后的子事件1
//        2022-09-07 09:40:08.119 4206-4206/? D/myObserver: myObserver Multiple onNext:我是事件3拆分后的子事件2
//        2022-09-07 09:40:08.120 4206-4206/? D/myObserver: myObserver Multiple onComplete

    }

    fun learnOperator() {
        // 注：timer操作符默认运行在一个新线程上
        // 也可自定义线程调度器（第3个参数）：timer(long,TimeUnit,Scheduler)
//        var newDis: Disposable = Observable.timer(2, TimeUnit.SECONDS)
//                .subscribe {
//                    Log.d(TAG, "${TAG} Consumer accept:$it")
//                }
//        disposableList.add(newDis)
//        var inDis = Observable.interval(2, 1, TimeUnit.SECONDS)
//                .subscribe {
//                    Log.d(TAG, "${TAG} Con sumer accept:$it")
//                }
//        disposableList.add(inDis)


        //变换操作符
        var mapDis = Observable.create(ObservableOnSubscribe<Int> { emitter ->
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onNext(3)
        }).map { it ->
            "map变换$it"
        }.subscribe {
            Log.d(TAG, "${TAG} map accept:$it")
        }
        disposableList.add(mapDis)

    }

    override fun onDestroy() {
        super.onDestroy()
        for (disposable in disposableList) {
            if (disposable?.isDisposed) continue else disposable.dispose()
        }
        testRxJava?.clear()
    }
}


