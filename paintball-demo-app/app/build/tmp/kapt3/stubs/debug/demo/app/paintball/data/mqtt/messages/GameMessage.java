package demo.app.paintball.data.mqtt.messages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0004\u00a8\u0006\u000e"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/GameMessage;", "Ldemo/app/paintball/data/mqtt/MqttMessage;", "type", "Ldemo/app/paintball/data/mqtt/messages/GameMessage$Type;", "(Ldemo/app/paintball/data/mqtt/messages/GameMessage$Type;)V", "raw", "", "(Ljava/lang/String;)V", "getType", "()Ldemo/app/paintball/data/mqtt/messages/GameMessage$Type;", "setType", "getTopic", "Ldemo/app/paintball/data/mqtt/MqttTopic;", "Type", "app_debug"})
public final class GameMessage extends demo.app.paintball.data.mqtt.MqttMessage {
    @org.jetbrains.annotations.NotNull()
    private demo.app.paintball.data.mqtt.messages.GameMessage.Type type;
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.mqtt.messages.GameMessage.Type getType() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.messages.GameMessage.Type p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttTopic getTopic() {
        return null;
    }
    
    public GameMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String raw) {
        super(null);
    }
    
    public GameMessage(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.data.mqtt.messages.GameMessage.Type type) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\n"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/GameMessage$Type;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "START", "LEAVE", "Companion", "app_debug"})
    public static enum Type {
        /*public static final*/ START /* = new START(null) */,
        /*public static final*/ LEAVE /* = new LEAVE(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        public static final demo.app.paintball.data.mqtt.messages.GameMessage.Type.Companion Companion = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        Type(java.lang.String value) {
        }
        
        @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/GameMessage$Type$Companion;", "", "()V", "find", "Ldemo/app/paintball/data/mqtt/messages/GameMessage$Type;", "value", "", "app_debug"})
        public static final class Companion {
            
            @org.jetbrains.annotations.NotNull()
            public final demo.app.paintball.data.mqtt.messages.GameMessage.Type find(@org.jetbrains.annotations.NotNull()
            java.lang.String value) {
                return null;
            }
            
            private Companion() {
                super();
            }
        }
    }
}