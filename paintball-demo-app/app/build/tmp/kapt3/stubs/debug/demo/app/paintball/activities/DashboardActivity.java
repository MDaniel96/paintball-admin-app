package demo.app.paintball.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 !2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0001!B\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\u0016\u0010\u0015\u001a\u00020\u00102\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\u0012\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u0018\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0018H\u0016J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010 \u001a\u00020\u0010H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\""}, d2 = {"Ldemo/app/paintball/activities/DashboardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Ldemo/app/paintball/data/rest/RestService$SuccessListener;", "Ldemo/app/paintball/fragments/dialogs/JoinGameFragment$JoinGameListener;", "Ldemo/app/paintball/fragments/dialogs/CreateGameFragment$CreateGameListener;", "Ldemo/app/paintball/fragments/dialogs/ConnectTagFragment$ConnectTagListener;", "()V", "playerName", "", "restService", "Ldemo/app/paintball/data/rest/RestService;", "getRestService", "()Ldemo/app/paintball/data/rest/RestService;", "setRestService", "(Ldemo/app/paintball/data/rest/RestService;)V", "addBluePlayerSuccess", "", "addRedPlayerSuccess", "checkOnlyMapMode", "checkTagsEnabled", "createGameSuccess", "getGameSuccess", "response", "Lretrofit2/Response;", "Ldemo/app/paintball/data/rest/models/Game;", "initAnimations", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateGame", "game", "onJoinGame", "onTagConnected", "Companion", "app_debug"})
public final class DashboardActivity extends androidx.appcompat.app.AppCompatActivity implements demo.app.paintball.data.rest.RestService.SuccessListener, demo.app.paintball.fragments.dialogs.JoinGameFragment.JoinGameListener, demo.app.paintball.fragments.dialogs.CreateGameFragment.CreateGameListener, demo.app.paintball.fragments.dialogs.ConnectTagFragment.ConnectTagListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.data.rest.RestService restService;
    private java.lang.String playerName;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> permissionsNeeded = null;
    public static final demo.app.paintball.activities.DashboardActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.rest.RestService getRestService() {
        return null;
    }
    
    public final void setRestService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.RestService p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void checkTagsEnabled() {
    }
    
    @java.lang.Override()
    public void onJoinGame(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName) {
    }
    
    @java.lang.Override()
    public void onCreateGame(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName, @org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.models.Game game) {
    }
    
    @java.lang.Override()
    public void getGameSuccess(@org.jetbrains.annotations.NotNull()
    retrofit2.Response<demo.app.paintball.data.rest.models.Game> response) {
    }
    
    @java.lang.Override()
    public void createGameSuccess() {
    }
    
    @java.lang.Override()
    public void addRedPlayerSuccess() {
    }
    
    @java.lang.Override()
    public void addBluePlayerSuccess() {
    }
    
    @java.lang.Override()
    public void onTagConnected() {
    }
    
    private final void checkOnlyMapMode() {
    }
    
    private final void initAnimations() {
    }
    
    public DashboardActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Ldemo/app/paintball/activities/DashboardActivity$Companion;", "", "()V", "permissionsNeeded", "", "", "getPermissionsNeeded", "()Ljava/util/List;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getPermissionsNeeded() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}