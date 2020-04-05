package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("lLjrWfbKEw9BzYmxiapQ2quN4fWHA3NQrl1fr5gA")
                // if defined
                .clientKey("YHvf0wOCCqOKEkDbwHOHJbMDE8Ug7dPmZDvzA9Hn")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
