package demo.app.paintball.config.topics;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b&\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0012\u0010\u000b\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0012\u0010\r\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Ldemo/app/paintball/config/topics/TopicsConfig;", "", "()V", "enemyPositions", "Ldemo/app/paintball/data/mqtt/MqttTopic;", "getEnemyPositions", "()Ldemo/app/paintball/data/mqtt/MqttTopic;", "enemyTeamChat", "getEnemyTeamChat", "game", "getGame", "positions", "getPositions", "teamChat", "getTeamChat", "Companion", "app_debug"})
public abstract class TopicsConfig {
    @org.jetbrains.annotations.NotNull()
    private final demo.app.paintball.data.mqtt.MqttTopic game = demo.app.paintball.data.mqtt.MqttTopic.GAME;
    public static final demo.app.paintball.config.topics.TopicsConfig.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.data.mqtt.MqttTopic getGame() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.mqtt.MqttTopic getPositions();
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.mqtt.MqttTopic getEnemyPositions();
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.mqtt.MqttTopic getTeamChat();
    
    @org.jetbrains.annotations.NotNull()
    public abstract demo.app.paintball.data.mqtt.MqttTopic getEnemyTeamChat();
    
    public TopicsConfig() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Ldemo/app/paintball/config/topics/TopicsConfig$Companion;", "", "()V", "playerTopics", "Ldemo/app/paintball/config/topics/TopicsConfig;", "getPlayerTopics", "()Ldemo/app/paintball/config/topics/TopicsConfig;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final demo.app.paintball.config.topics.TopicsConfig getPlayerTopics() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}