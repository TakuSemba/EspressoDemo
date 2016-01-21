package com.takusemba.espressodemo;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by TakuSemba on 16/01/21.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpTestWithoutEspresso extends ActivityInstrumentationTestCase2<SignUpActivity> {

    String userId = "TakuSemba";
    String password = "111aaa";

    private Activity activity;
    private Instrumentation.ActivityMonitor monitor;

    public SignUpTestWithoutEspresso() {
        super(SignUpActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
    }

    @Test
    public void checkSignUp() {
        final EditText userIdText = (EditText) activity.findViewById(R.id.user_id);
        final EditText passwordText = (EditText) activity.findViewById(R.id.password);
        final Button button = (Button) activity.findViewById(R.id.signup_button);

        //UiThreadで処理
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userIdText.setText(userId);
                passwordText.setText(password);
                button.performClick();
            }
        });
        //遷移後のActivityを取得
        monitor = new Instrumentation.ActivityMonitor(MainActivity.class.getName(), null, false);
        getInstrumentation().addMonitor(monitor);
        Activity nextActivity = monitor.waitForActivity();
        TextView textView = (TextView) nextActivity.findViewById(R.id.hello_world);

        // 画面遷移後のTextViewのテキストをチェック
        assertEquals(textView.getText().toString(), "Hello World, " + userId + "!!");
    }

    @Test
    public void checkErrorMessage() {
        final EditText userIdText = (EditText) activity.findViewById(R.id.user_id);
        final EditText passwordText = (EditText) activity.findViewById(R.id.password);
        final Button button = (Button) activity.findViewById(R.id.signup_button);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userIdText.setText("");
                passwordText.setText(password);
                button.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        TextView errorMessage = (TextView) activity.findViewById(R.id.error_message);
        assertEquals(errorMessage.getText().toString(), "ユーザーIDを入力してください。");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

}
