package com.android.bahukhandi.aneesha.parsing.json_parsing;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by aneesha.bahukhandi on 31/05/17
 */

@RunWith(AndroidJUnit4.class)
public class ParsingInstrumentationTest {

    @Rule
    public ActivityTestRule<ParsingComparison> mActivityRule =
            new ActivityTestRule<>(ParsingComparison.class);

    @Test
    public void gsonParser() {
        // Type text and then press the button.
        onView(withId(R.id.tv_parse_gson_section_header))
                .perform(click());
        onView(withId(R.id.btn_gson))
                .perform(click());
    }
}
