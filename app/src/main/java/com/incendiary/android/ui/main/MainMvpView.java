package com.incendiary.android.ui.main;

import java.util.List;

import com.incendiary.android.data.model.Ribot;
import com.incendiary.android.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
