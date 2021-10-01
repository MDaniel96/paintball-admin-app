package demo.app.paintball.config.topics;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006\u00a8\u0006\r"}, d2 = {"Ldemo/app/paintball/config/topics/RedTeamTopics;", "Ldemo/app/paintball/config/topics/TopicsConfig;", "()V", "enemyPositions", "Ldemo/app/paintball/data/mqtt/MqttTopic;", "getEnemyPositions", "()Ldemo/app/paintball/data/mqtt/MqttTopic;", "enemyTeamChat", "getEnemyTeamChat", "positions", "getPositions", "teamChat", "getTeamChat", "app_debug"})
public final class RedTeamTopics extends demo.app.paintball.config.topics.TopicsConfig {
    @org.jetbrains.annotations.NotNull()
    private static final demo.app.paintball.data.mqtt.MqttTopic positions = demo.app.paintball.data.mqtt.MqttTopic.POSITIONS_RED_TEAM;
    @org.jetbrains.annotations.NotNull()
    private static final demo.app.paintball.data.mqtt.MqttTopic enemyPositions = demo.app.paintball.data.mqtt.MqttTopic.POSITIONS_BLUE_TEAM;
    @org.jetbrains.annotations.NotNull()
    private static final demo.app.paintball.data.mqtt.MqttTopic teamChat = demo.app.paintball.data.mqtt.MqttTopic.CHAT_RED_TEAM;
    @org.jetbrains.annotations.NotNull()
    private static final demo.app.paintball.data.mqtt.MqttTopic enemyTeamChat = demo.app.paintball.data.mqtt.MqttTopic.CHAT_BLUE_TEAM;
    public static final demo.app.paintball.config.topics.RedTeamTopics INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttTopic getPositions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttTopic getEnemyPositions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttTopic getTeamChat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public demo.app.paintball.data.mqtt.MqttTopic getEnemyTeamChat() {
        return null;
    }
    
    private RedTeamTopics() {
        super();
    }
}