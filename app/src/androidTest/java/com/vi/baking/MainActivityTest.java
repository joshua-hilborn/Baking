package com.vi.baking;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vi.baking.ui.DetailActivity;
import com.vi.baking.ui.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

   @Rule
   public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;


    @Before
    public void registerIdlingResource(){
        idlingResource = mainActivityActivityTestRule.getActivity().getIdlingResource();
        //Espresso.registerIdlingResources(idlingResource);
        IdlingRegistry.getInstance().register(idlingResource);

    }


    @Test
    public void checkRecyclerViewText_MainActivity() {
        onView(withId(R.id.rv_recipe_list)).perform(RecyclerViewActions.scrollToPosition(3));
        onView(withText("Cheesecake")).check(matches(isDisplayed()));
    }

    @Test
    public void checkRecyclerViewText_DetailActivity() {
        onView(withId(R.id.rv_recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.rv_steps_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
        onView(withText("Prep the cookie crust.")).check(matches(isDisplayed()));
        //onView(withId(R.id.sepv_step_detail_fragment_player)).check(matches(isCompletelyDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            //Espresso.unregisterIdlingResources(mIdlingResource);
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }


}
