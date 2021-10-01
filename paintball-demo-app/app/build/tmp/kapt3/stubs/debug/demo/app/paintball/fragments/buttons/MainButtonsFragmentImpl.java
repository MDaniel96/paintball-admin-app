package demo.app.paintball.fragments.buttons;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 .2\u00020\u0001:\u0001.B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010#\u001a\u00020$H\u0002J&\u0010%\u001a\u0004\u0018\u00010\u00042\u0006\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u001a\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\u00042\b\u0010*\u001a\u0004\u0018\u00010+H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001e\u0010\u0018\u001a\u00020\u00198\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0004X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Ldemo/app/paintball/fragments/buttons/MainButtonsFragmentImpl;", "Ldemo/app/paintball/fragments/buttons/MapButtonsFragment;", "()V", "btnBottom", "Landroid/view/View;", "getBtnBottom", "()Landroid/view/View;", "setBtnBottom", "(Landroid/view/View;)V", "btnBottomLayout", "getBtnBottomLayout", "setBtnBottomLayout", "btnBottomTextView", "getBtnBottomTextView", "setBtnBottomTextView", "btnMiddle", "getBtnMiddle", "setBtnMiddle", "btnMiddleLayout", "getBtnMiddleLayout", "setBtnMiddleLayout", "btnMiddleTextView", "getBtnMiddleTextView", "setBtnMiddleTextView", "mqttService", "Ldemo/app/paintball/data/mqtt/MqttService;", "getMqttService", "()Ldemo/app/paintball/data/mqtt/MqttService;", "setMqttService", "(Ldemo/app/paintball/data/mqtt/MqttService;)V", "rootLayout", "getRootLayout", "setRootLayout", "timer", "Ljava/util/Timer;", "initFabSpyingButton", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Companion", "app_debug"})
public final class MainButtonsFragmentImpl extends demo.app.paintball.fragments.buttons.MapButtonsFragment {
    @org.jetbrains.annotations.NotNull()
    public android.view.View rootLayout;
    @org.jetbrains.annotations.NotNull()
    public android.view.View btnBottom;
    @org.jetbrains.annotations.NotNull()
    public android.view.View btnBottomLayout;
    @org.jetbrains.annotations.NotNull()
    public android.view.View btnBottomTextView;
    @org.jetbrains.annotations.NotNull()
    public android.view.View btnMiddle;
    @org.jetbrains.annotations.NotNull()
    public android.view.View btnMiddleLayout;
    @org.jetbrains.annotations.NotNull()
    public android.view.View btnMiddleTextView;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.data.mqtt.MqttService mqttService;
    private final java.util.Timer timer = null;
    public static final long SPYING_TIME = 7000L;
    public static final long SPYING_RECHARGE_TIME = 12000L;
    public static final demo.app.paintball.fragments.buttons.MainButtonsFragmentImpl.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getRootLayout() {
        return null;
    }
    
    @java.lang.Override()
    public void setRootLayout(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getBtnBottom() {
        return null;
    }
    
    @java.lang.Override()
    public void setBtnBottom(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getBtnBottomLayout() {
        return null;
    }
    
    @java.lang.Override()
    public void setBtnBottomLayout(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getBtnBottomTextView() {
        return null;
    }
    
    @java.lang.Override()
    public void setBtnBottomTextView(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getBtnMiddle() {
        return null;
    }
    
    @java.lang.Override()
    public void setBtnMiddle(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getBtnMiddleLayout() {
        return null;
    }
    
    @java.lang.Override()
    public void setBtnMiddleLayout(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getBtnMiddleTextView() {
        return null;
    }
    
    @java.lang.Override()
    public void setBtnMiddleTextView(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.mqtt.MqttService getMqttService() {
        return null;
    }
    
    public final void setMqttService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.MqttService p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initFabSpyingButton() {
    }
    
    public MainButtonsFragmentImpl() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/fragments/buttons/MainButtonsFragmentImpl$Companion;", "", "()V", "SPYING_RECHARGE_TIME", "", "SPYING_TIME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}