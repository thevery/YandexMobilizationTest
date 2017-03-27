package com.applications.whazzup.yandextranslator.di.modules;


import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
