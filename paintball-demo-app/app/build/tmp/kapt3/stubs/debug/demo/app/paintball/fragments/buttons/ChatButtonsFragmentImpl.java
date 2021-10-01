package demo.app.paintball.fragments.buttons;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 62\u00020\u00012\u00020\u0002:\u00016B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020(H\u0003J\b\u0010,\u001a\u00020(H\u0002J&\u0010-\u001a\u0004\u0018\u00010\u00052\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u0001012\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u001a\u00104\u001a\u00020(2\u0006\u00105\u001a\u00020\u00052\b\u00102\u001a\u0004\u0018\u000103H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0005X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u001a\u0010\u0016\u001a\u00020\u0005X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020\u0005X\u0096.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0007\"\u0004\b&\u0010\t\u00a8\u00067"}, d2 = {"Ldemo/app/paintball/fragments/buttons/ChatButtonsFragmentImpl;", "Ldemo/app/paintball/fragments/buttons/MapButtonsFragment;", "Ldemo/app/paintball/data/mqtt/MqttService$ChatListener;", "()V", "btnBottom", "Landroid/view/View;", "getBtnBottom", "()Landroid/view/View;", "setBtnBottom", "(Landroid/view/View;)V", "btnBottomLayout", "getBtnBottomLayout", "setBtnBottomLayout", "btnBottomTextView", "getBtnBottomTextView", "setBtnBottomTextView", "btnMiddle", "getBtnMiddle", "setBtnMiddle", "btnMiddleLayout", "getBtnMiddleLayout", "setBtnMiddleLayout", "btnMiddleTextView", "getBtnMiddleTextView", "setBtnMiddleTextView", "chatActivated", "", "mqttService", "Ldemo/app/paintball/data/mqtt/MqttService;", "getMqttService", "()Ldemo/app/paintball/data/mqtt/MqttService;", "setMqttService", "(Ldemo/app/paintball/data/mqtt/MqttService;)V", "recordService", "Ldemo/app/paintball/util/services/RecordService;", "recording", "rootLayout", "getRootLayout", "setRootLayout", "chatMessageArrived", "", "message", "Ldemo/app/paintball/data/mqtt/messages/ChatMessage;", "initFabActivateTeamChat", "initFabTeamChat", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Companion", "app_debug"})
public final class ChatButtonsFragmentImpl extends demo.app.paintball.fragments.buttons.MapButtonsFragment implements demo.app.paintball.data.mqtt.MqttService.ChatListener {
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
    private demo.app.paintball.util.services.RecordService recordService;
    private boolean recording = false;
    private boolean chatActivated = true;
    public static final long RECORDING_TIME = 4000L;
    public static final demo.app.paintball.fragments.buttons.ChatButtonsFragmentImpl.Companion Companion = null;
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
    
    private final void initFabTeamChat() {
    }
    
    @android.annotation.SuppressLint(value = {"NewApi"})
    private final void initFabActivateTeamChat() {
    }
    
    @java.lang.Override()
    public void chatMessageArrived(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.messages.ChatMessage message) {
    }
    
    public ChatButtonsFragmentImpl() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ldemo/app/paintball/fragments/buttons/ChatButtonsFragmentImpl$Companion;", "", "()V", "RECORDING_TIME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}