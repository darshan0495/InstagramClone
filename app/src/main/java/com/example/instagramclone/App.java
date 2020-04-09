package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("J1UEJQuVu1G8ygEQynq38HjbILfFKLYGPJLktDjf")
                // if defined
                .clientKey("mS5Lch3tHRu6Jc6UsCDNDVBRYr9AKs7ntndleSzy")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
