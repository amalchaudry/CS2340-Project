package com.example.odam;

import android.app.Application;

public class GameApplication extends Application {
    private Difficulty diff;

    public Difficulty getDiff() {
        return diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }

    /**when you want to access this class
     * // set
     * ((MyApplication) this.getApplication()).setSomeVariable("foo");
     *
     * // get
     * String s = ((MyApplication) this.getApplication()).getSomeVariable();
     */
}
