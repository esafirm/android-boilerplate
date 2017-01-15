package com.klikacc.android.common;

import android.os.Bundle;

import com.bluelinelabs.conductor.Router;

public interface Setup {
    void onViewCreated(Bundle savedInstanceState);
    Router getMainRouter();
    Router getOverlayRouter();
}
