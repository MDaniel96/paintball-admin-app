package demo.app.paintball.data.mqtt.messages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\f\u00a8\u0006\u0011"}, d2 = {"Ldemo/app/paintball/data/mqtt/messages/PositionMessage;", "Ldemo/app/paintball/data/mqtt/MqttMessage;", "posX", "", "posY", "(II)V", "raw", "", "(Ljava/lang/String;)V", "getPosX", "()I", "setPosX", "(I)V", "getPosY", "setPosY", "getTopic", "Ldemo/app/paintball/data/mqtt/MqttTopic;", "app_debug"})
public final class PositionMessage extends demo.app.paintball.data.mqtt.MqttMessage {
    private int posX;
    private int posY;
    
    public final int getPosX() {
        return 0;
    }
    
    public final void setPosX(int p0) {
    }
    
    public final int getPosY() {
        return 0;
    }
    
    public final void setPosY(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttTopic getTopic() {
        return null;
    }
    
    public PositionMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String raw) {
        super(null);
    }
    
    public PositionMessage(int posX, int posY) {
        super(null);
    }
}