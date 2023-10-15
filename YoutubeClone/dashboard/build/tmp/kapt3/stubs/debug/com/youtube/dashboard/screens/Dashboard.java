package com.youtube.dashboard.screens;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0010"}, d2 = {"Lcom/youtube/dashboard/screens/Dashboard;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/youtube/dashboard/databinding/ActivityDashboardBinding;", "videoViewModel", "Lcom/youtube/dashboard/viewmodels/VideoViewModel;", "getVideoViewModel", "()Lcom/youtube/dashboard/viewmodels/VideoViewModel;", "videoViewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "dashboard_debug"})
@dagger.hilt.android.AndroidEntryPoint
public final class Dashboard extends androidx.appcompat.app.AppCompatActivity {
    private com.youtube.dashboard.databinding.ActivityDashboardBinding binding;
    private final kotlin.Lazy videoViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.youtube.dashboard.screens.Dashboard.Companion Companion = null;
    
    public Dashboard() {
        super();
    }
    
    private final com.youtube.dashboard.viewmodels.VideoViewModel getVideoViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/youtube/dashboard/screens/Dashboard$Companion;", "", "()V", "launchActivity", "", "activity", "Landroid/app/Activity;", "dashboard_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void launchActivity(@org.jetbrains.annotations.NotNull
        android.app.Activity activity) {
        }
    }
}