package com.takusemba.espressodemo;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by takusemba on 16/01/21.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpTest extends ActivityInstrumentationTestCase2<SignUpActivity> {

    String userId = "TakuSemba";
    String password = "111aaa";

    private SignUpActivity activity;

    public SignUpTest() {
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
        // Type text and then press the button.
        onView(withId(R.id.user_id))
                .perform(replaceText(userId));
        onView(withId(R.id.password))
                .perform(replaceText(password), closeSoftKeyboard());

        onView(withId(R.id.signup_button)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.hello_world))
                .check(matches(withText("Hello World, " + userId + "!!")));
    }

    @Test
    public void checkErrorMessage() {

        onView(withId(R.id.user_id)).perform(replaceText(""));
        onView(withId(R.id.password)).perform(replaceText(password), closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.error_message)).check(matches(withText(activity.getString(R.string.error_user_id_is_empty))));

        onView(withId(R.id.user_id)).perform(replaceText("TakuSemba"));
        onView(withId(R.id.password)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.error_message)).check(matches(withText(activity.getString(R.string.error_pw_is_empty))));

        onView(withId(R.id.user_id)).perform(replaceText("TakuSemba"));
        onView(withId(R.id.password)).perform(replaceText("1a"), closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.error_message)).check(matches(withText(activity.getString(R.string.error_pw_is_less_than_4))));

        onView(withId(R.id.user_id)).perform(replaceText("TakuSemba"));
        onView(withId(R.id.password)).perform(replaceText("aaaa"), closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.error_message)).check(matches(withText(activity.getString(R.string.error_pw_needs_numbers))));

        onView(withId(R.id.user_id)).perform(replaceText("TakuSemba"));
        onView(withId(R.id.password)).perform(replaceText("1111"), closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.error_message)).check(matches(withText(activity.getString(R.string.error_pw_needs_characters))));

    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
