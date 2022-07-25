package com.example.crashlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.dianping.logan.SendLogRunnable;
import com.example.crashlib.crash.CrashReport;
import com.example.crashlib.crash.TestJavaCrash;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("bugly");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TestJavaCrash.testJavaCrash();
//        CrashReport.INSTANCE.testNativeCrash();
        testLogan();

    }

    /**
     * Logan日志库使用
     */
    public void testLogan() {


        //https://blog.csdn.net/zhongwei123/article/details/109209747
        LoganConfig config = new LoganConfig.Builder()

                .setCachePath(getApplicationContext().getFilesDir().getAbsolutePath())

                .setPath(getApplicationContext().getExternalFilesDir(null).getAbsolutePath() + File.separator + "logan_v1")

                .setEncryptKey16("0123456789012345".getBytes())

                .setEncryptIV16("0123456789012345".getBytes())

                .setMaxFile(10)

                .setDay(3)

                .build();
        Logan.init(config);

        Logan.w("test logan", 2);



        //自定义上传文件
        Logan.s(new String[]{"2022-7-18"}, new RealSendLogRunnable());

    }

    public static class RealSendLogRunnable extends SendLogRunnable {

        @Override
        public void sendLog(File logFile) {
            // logFile: After the pretreatment is going to upload the log file
            // Must Call finish after send log
            finish();
            if (logFile.getName().contains(".copy")) {
                logFile.delete();
            }
        }
    }
}