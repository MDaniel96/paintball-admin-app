package demo.app.paintball.data.mqtt;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH&J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0014"}, d2 = {"Ldemo/app/paintball/data/mqtt/MqttMessage;", "", "raw", "", "(Ljava/lang/String;)V", "playerName", "getPlayerName", "()Ljava/lang/String;", "setPlayerName", "rawFields", "", "getRawFields", "()Ljava/util/List;", "getTopic", "Ldemo/app/paintball/data/mqtt/MqttTopic;", "publish", "", "mqttService", "Ldemo/app/paintball/data/mqtt/MqttService;", "Companion", "app_debug"})
public abstract class MqttMessage {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> rawFields = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String playerName;
    private final java.lang.String raw = null;
    public static final char SEPARATOR = '|';
    public static final demo.app.paintball.data.mqtt.MqttMessage.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getRawFields() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlayerName() {
        return null;
    }
    
    public final void setPlayerName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final void publish(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.MqttService mqttService) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.mqtt.MqttTopic getTopic();
    
    public MqttMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String raw) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ldemo/app/paintball/data/mqtt/MqttMessage$Companion;", "", "()V", "SEPARATOR", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}