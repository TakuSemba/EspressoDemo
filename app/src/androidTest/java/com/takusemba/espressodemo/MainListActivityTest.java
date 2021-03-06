package com.takusemba.espressodemo;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
public class MainListActivityTest {
	@Rule
	public ActivityTestRule<MainListActivity> activityRule = new ActivityTestRule<>(MainListActivity.class);

	@Test
	public void testClickItem() {
		onView(withId(R.id.text))
				.check(matches(not(isDisplayed())));

		onData(anything())
				.inAdapterView(withId(R.id.list))
				.atPosition(10)
				.perform(click());

		onView(withId(R.id.text))
				.check(matches(withText("10")))
				.check(matches(isDisplayed()));

		onData(withValue(39))
				.inAdapterView(withId(R.id.list))
				.perform(click());

		onView(withId(R.id.text))
				.check(matches(withText("39")))
				.check(matches(isDisplayed()));

		onData(hasToString(startsWith("193")))
				.perform(click());

		onView(withId(R.id.text))
				.check(matches(withText("193")))
				.check(matches(isDisplayed()));
	}

	public static Matcher<Object> withValue(final int value) {
		return new BoundedMatcher<Object, MainListActivity.Item>(MainListActivity.Item.class) {
			@Override
			public void describeTo(Description description) {
				description.appendText("has value " + value);
			}

			@Override
			public boolean matchesSafely(MainListActivity.Item item) {
				return item.toString().equals(String.valueOf(value));
			}
		};
	}
}