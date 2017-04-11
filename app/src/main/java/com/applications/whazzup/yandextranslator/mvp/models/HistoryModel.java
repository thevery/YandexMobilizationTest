package com.applications.whazzup.yandextranslator.mvp.models;

import com.applications.whazzup.yandextranslator.data.storage.realm.TranslateRealm;

import rx.Observable;


public class HistoryModel extends AbstractModel {

    public Observable<TranslateRealm> getTranslateHistory() {
        return mDataManager.getRealmManager().getTranslateHistory();
    }

    public void deleteAllHistory() {
        mDataManager.getRealmManager().deleteAllHistory();
    }

    public void saveTranslateToFavorite(TranslateRealm translateRealm) {
        mDataManager.getRealmManager().saveTranslateToFavorite(translateRealm);
    }

    public void deletetTranslateFromFavorite(TranslateRealm translateRealm) {
        mDataManager.getRealmManager().deleteTranslateFromFavorite(translateRealm);
    }
}
