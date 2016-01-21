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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by takusemba on 16/01/21.
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	String userId = "TakuSemba";
	String password = "111aaa";

	private LoginActivity activity;

	public LoginTest() {
		super(LoginActivity.class);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		injectInstrumentation(InstrumentationRegistry.getInstrumentation());
		activity = getActivity();
	}

	@Test
	public void checkStartButtonText() {
		onView(withId(R.id.error_message)).check(matches(withText("")));
		// Type text and then press the button.
		onView(withId(R.id.user_id))
				.perform(typeText(userId));
		onView(withId(R.id.password))
				.perform(typeText(password), closeSoftKeyboard());

		onView(withId(R.id.login_button)).perform(click());

		// Check that the text was changed.
		onView(withId(R.id.hello_world))
				.check(matches(withText("Hello World, " + userId + "!!")));
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	private Instrumentation mInstrumentation = null;

	private Instrumentation.ActivityMonitor LoginActivity;
	private Instrumentation.ActivityMonitor MainActivity;

	String userId = "TakuSemba";
	String password = "111aaa";

	@Before
	public void setUp() throws Exception {
		super.setUp();
		mInstrumentation = getInstrumentation();
	}

	@Test
	public void testLoginActivity() {

		LoginActivity = mInstrumentation.addMonitor(LoginActivity.class.getName(), null, false);
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClassName(mInstrumentation.getTargetContext(), LoginActivity.class.getName());
		//SplashActivity Start
		mInstrumentation.startActivitySync(intent);
		mInstrumentation.waitForMonitor(LoginActivity);

		assertEquals(1, LoginActivity.getHits());

		// Type text and then press the button.
		onView(withId(R.id.user_id))
				.perform(typeText(userId));
		onView(withId(R.id.password))
				.perform(typeText(password), closeSoftKeyboard());

		onView(withId(R.id.login_button)).perform(click());

		// Check that the text was changed.
		onView(withId(R.id.hello_world))
				.check(matches(withText("Hello World, " + userId + "!!")));
	}
	*/
}
