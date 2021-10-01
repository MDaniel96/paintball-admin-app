package demo.app.paintball.data.mqtt.messages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\b\u00a8\u0006\u0012"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/ChatMessage;", "Ldemo/app/paintball/data/mqtt/MqttMessage;", "message", "", "length", "", "(Ljava/lang/String;J)V", "raw", "(Ljava/lang/String;)V", "getLength", "()J", "setLength", "(J)V", "getMessage", "()Ljava/lang/String;", "setMessage", "getTopic", "Ldemo/app/paintball/data/mqtt/MqttTopic;", "app_debug"})
public final class ChatMessage extends demo.app.paintball.data.mqtt.MqttMessage {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String message;
    private long length;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getLength() {
        return 0L;
    }
    
    public final void setLength(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttTopic getTopic() {
        return null;
    }
    
    public ChatMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String raw) {
        super(null);
    }
    
    public ChatMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message, long length) {
        super(null);
    }
}