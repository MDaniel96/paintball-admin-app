package demo.app.paintball.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00b4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\nB\u0005\u00a2\u0006\u0002\u0010\u000bJ\b\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020.H\u0002J\b\u00100\u001a\u00020.H\u0016J\b\u00101\u001a\u00020.H\u0016J\b\u00102\u001a\u00020.H\u0016J\u0010\u00103\u001a\u00020.2\u0006\u00104\u001a\u000205H\u0016J\u0016\u00106\u001a\u00020.2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u001508H\u0016J\b\u00109\u001a\u00020.H\u0002J\b\u0010:\u001a\u00020.H\u0016J\b\u0010;\u001a\u00020.H\u0016J\u0010\u0010<\u001a\u00020.2\u0006\u0010=\u001a\u00020\rH\u0016J\u0010\u0010>\u001a\u00020.2\u0006\u0010=\u001a\u00020\rH\u0016J\u0018\u0010?\u001a\u00020.2\u0006\u0010=\u001a\u00020\r2\u0006\u0010@\u001a\u00020AH\u0016J\u0012\u0010B\u001a\u00020.2\b\u0010C\u001a\u0004\u0018\u00010DH\u0014J\b\u0010E\u001a\u00020.H\u0014J\u0010\u0010F\u001a\u00020.2\u0006\u0010G\u001a\u00020HH\u0016J\b\u0010I\u001a\u00020.H\u0014J\u0018\u0010J\u001a\u00020.2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020LH\u0016J\b\u0010N\u001a\u00020.H\u0014J\u0010\u0010O\u001a\u00020.2\u0006\u0010P\u001a\u00020HH\u0016J\b\u0010Q\u001a\u00020.H\u0016J\b\u0010R\u001a\u00020.H\u0016J\b\u0010S\u001a\u00020.H\u0016J\b\u0010T\u001a\u00020.H\u0016J\b\u0010U\u001a\u00020.H\u0016J\u0010\u0010V\u001a\u00020.2\u0006\u00104\u001a\u00020WH\u0016J\b\u0010X\u001a\u00020.H\u0002R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u00020\u001e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u00020&8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006Y"}, d2 = {"Ldemo/app/paintball/activities/MapActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;", "Ldemo/app/paintball/map/sensors/Gyroscope$GyroscopeListener;", "Ldemo/app/paintball/data/rest/RestService$SuccessListener;", "Ldemo/app/paintball/fragments/dialogs/ConnectTagFragment$ConnectTagListener;", "Ldemo/app/paintball/data/mqtt/MqttService$PositionListener;", "Ldemo/app/paintball/map/rendering/MapViewImpl$MapViewCreatedListener;", "Ldemo/app/paintball/data/ble/BleServiceImpl$BleServiceListener;", "Ldemo/app/paintball/util/positioning/PositionCalculator$PositionCalculatorListener;", "Ldemo/app/paintball/data/mqtt/MqttService$GameListener;", "()V", "bleService", "Ldemo/app/paintball/data/ble/BleService;", "getBleService", "()Ldemo/app/paintball/data/ble/BleService;", "setBleService", "(Ldemo/app/paintball/data/ble/BleService;)V", "chatButtons", "Ldemo/app/paintball/fragments/buttons/MapButtonsFragment;", "game", "Ldemo/app/paintball/data/rest/models/Game;", "gyroscope", "Ldemo/app/paintball/map/sensors/Gyroscope;", "isMapButtonsOpen", "", "mainButtons", "map", "Ldemo/app/paintball/map/MapView;", "mqttService", "Ldemo/app/paintball/data/mqtt/MqttService;", "getMqttService", "()Ldemo/app/paintball/data/mqtt/MqttService;", "setMqttService", "(Ldemo/app/paintball/data/mqtt/MqttService;)V", "positionCalculator", "Ldemo/app/paintball/util/positioning/PositionCalculator;", "restService", "Ldemo/app/paintball/data/rest/RestService;", "getRestService", "()Ldemo/app/paintball/data/rest/RestService;", "setRestService", "(Ldemo/app/paintball/data/rest/RestService;)V", "statsPanel", "Ldemo/app/paintball/fragments/panels/MapStatsPanelFragment;", "addBluePlayerSuccess", "", "addPlayersToMap", "addRedPlayerSuccess", "connectComplete", "createGameSuccess", "gameMessageArrived", "message", "Ldemo/app/paintball/data/mqtt/messages/GameMessage;", "getGameSuccess", "response", "Lretrofit2/Response;", "hideButtons", "mapViewCreated", "onBackPressed", "onBleConnected", "connection", "onBleDisconnected", "onBlePositionDataReceived", "data", "Ldemo/app/paintball/data/ble/data/BlePositionData;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOrientationChanged", "radian", "", "onPause", "onPositionCalculated", "posX", "", "posY", "onResume", "onScaleChanged", "scaleFactor", "onScrollDown", "onScrollUp", "onTagConnected", "onZoomIn", "onZoomOut", "positionMessageArrived", "Ldemo/app/paintball/data/mqtt/messages/PositionMessage;", "showButtons", "app_debug"})
public final class MapActivity extends androidx.appcompat.app.AppCompatActivity implements demo.app.paintball.map.sensors.GestureSensor.GestureListener, demo.app.paintball.map.sensors.Gyroscope.GyroscopeListener, demo.app.paintball.data.rest.RestService.SuccessListener, demo.app.paintball.fragments.dialogs.ConnectTagFragment.ConnectTagListener, demo.app.paintball.data.mqtt.MqttService.PositionListener, demo.app.paintball.map.rendering.MapViewImpl.MapViewCreatedListener, demo.app.paintball.data.ble.BleServiceImpl.BleServiceListener, demo.app.paintball.util.positioning.PositionCalculator.PositionCalculatorListener, demo.app.paintball.data.mqtt.MqttService.GameListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.data.rest.RestService restService;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.data.mqtt.MqttService mqttService;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public demo.app.paintball.data.ble.BleService bleService;
    private demo.app.paintball.data.rest.models.Game game;
    private boolean isMapButtonsOpen = false;
    private demo.app.paintball.map.MapView map;
    private demo.app.paintball.fragments.buttons.MapButtonsFragment mainButtons;
    private demo.app.paintball.fragments.buttons.MapButtonsFragment chatButtons;
    private demo.app.paintball.fragments.panels.MapStatsPanelFragment statsPanel;
    private demo.app.paintball.map.sensors.Gyroscope gyroscope;
    private demo.app.paintball.util.positioning.PositionCalculator positionCalculator;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.rest.RestService getRestService() {
        return null;
    }
    
    public final void setRestService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.rest.RestService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.mqtt.MqttService getMqttService() {
        return null;
    }
    
    public final void setMqttService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.MqttService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.ble.BleService getBleService() {
        return null;
    }
    
    public final void setBleService(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleService p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    public void mapViewCreated() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    public void onScaleChanged(float scaleFactor) {
    }
    
    @java.lang.Override()
    public void onZoomIn() {
    }
    
    @java.lang.Override()
    public void onZoomOut() {
    }
    
    @java.lang.Override()
    public void onScrollUp() {
    }
    
    @java.lang.Override()
    public void onScrollDown() {
    }
    
    @java.lang.Override()
    public void onOrientationChanged(float radian) {
    }
    
    @java.lang.Override()
    public void getGameSuccess(@org.jetbrains.annotations.NotNull()
    retrofit2.Response<demo.app.paintball.data.rest.models.Game> response) {
    }
    
    private final void addPlayersToMap() {
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
    
    @java.lang.Override()
    public void positionMessageArrived(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.messages.PositionMessage message) {
    }
    
    @java.lang.Override()
    public void connectComplete() {
    }
    
    @java.lang.Override()
    public void gameMessageArrived(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.messages.GameMessage message) {
    }
    
    @java.lang.Override()
    public void onBleConnected(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleService connection) {
    }
    
    @java.lang.Override()
    public void onBlePositionDataReceived(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleService connection, @org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.data.BlePositionData data) {
    }
    
    @java.lang.Override()
    public void onBleDisconnected(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.ble.BleService connection) {
    }
    
    @java.lang.Override()
    public void onPositionCalculated(int posX, int posY) {
    }
    
    private final void showButtons() {
    }
    
    private final void hideButtons() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public MapActivity() {
        super();
    }
}