package com.applications.whazzup.yandextranslator.mvp.presenters;


import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.applications.whazzup.yandextranslator.App;
import com.applications.whazzup.yandextranslator.mvp.models.RootModel;
import com.applications.whazzup.yandextranslator.mvp.views.IRootView;
import com.applications.whazzup.yandextranslator.ui.activities.RootActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mortar.Presenter;
import mortar.bundler.BundleService;

public class RootPresenter extends Presenter<IRootView> {

    private static RootPresenter ourInstance = null;
    private static int DEFAULT_MODE = 0;
    private static int TAB_MODE = 1;
    private String languageCodeTo = "en";
    private String languageCodeFrom = "ru";

     @Inject
        RootModel mRootModel;

    public static RootPresenter getInstace(){
        if(ourInstance == null){
            ourInstance = new RootPresenter();
        }
        return ourInstance;
    }

    private RootPresenter() {
        App.getRootActivityComponent().inject(this);
        mRootModel.getAllLang();
    }

    @Nullable
    public IRootView getRootView() {
        return getView();
    }

    @Override
    protected BundleService extractBundleService(IRootView view) {
        return BundleService.getBundleService((RootActivity)view);
    }

    public ActionBarBuilder newActionBarBuilder(){
        return this.new ActionBarBuilder();
    }

    public class ActionBarBuilder{

        private boolean isGoBack;
        private ViewPager viewPager;
        private boolean directionVisible;
        private List<MenuItemHolder> item = new ArrayList<>();
        private int toolBArMode = DEFAULT_MODE;
        private boolean visible = true;

       public ActionBarBuilder setBackArrow(boolean enable){
            this.isGoBack = enable;
            return this;
        }

        public ActionBarBuilder setDirectionVisible(boolean isVisible){
            this.directionVisible = isVisible;
            return this;
        }

        public ActionBarBuilder addAction(MenuItemHolder itemMenu){
            this.item.add(itemMenu);
            return this;
        }

        public ActionBarBuilder setActionBarVisible(boolean isVisible){
            this.visible = isVisible;
            return this;
        }

        public ActionBarBuilder setTab(ViewPager pager){
            this.toolBArMode = TAB_MODE;
            this.viewPager = pager;
            return this;
        }

        public void build(){
            if(getView()!=null){
                RootActivity activity = (RootActivity) getView();
                activity.setBackArrow(isGoBack);
                activity.setMenuItem(item);
                if (toolBArMode == TAB_MODE) {
                    activity.setTabLayout(viewPager);
                } else {
                    activity.removeTabLayout();
                }
                activity.directionVisible(directionVisible);
                activity.actionBarVisible(visible);
            }
        }
    }

    public String getLanguageCodeTo() {
        return languageCodeTo;
    }

    public void setLanguageCodeTo(String languageCodeTo) {
        this.languageCodeTo = languageCodeTo;
    }

    public String getLanguageCodeFrom() {
        return languageCodeFrom;
    }

    public void setLanguageCodeFrom(String languageCodeFrom) {
        this.languageCodeFrom = languageCodeFrom;
    }
}
