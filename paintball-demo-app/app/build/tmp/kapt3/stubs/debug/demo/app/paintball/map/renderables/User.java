package demo.app.paintball.map.renderables;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\fJ\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0094\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Ldemo/app/paintball/map/renderables/User;", "Ldemo/app/paintball/map/renderables/Renderable;", "()V", "image", "Landroid/graphics/Bitmap;", "getImage", "()Landroid/graphics/Bitmap;", "imageMatrix", "Landroid/graphics/Matrix;", "loopCounter", "", "screenCenterX", "", "screenCenterY", "slowCounter", "sprite", "spriteState", "drawSprite", "", "canvas", "Landroid/graphics/Canvas;", "render", "setOrientation", "degree", "setSize", "x", "y", "Companion", "app_debug"})
public final class User extends demo.app.paintball.map.renderables.Renderable {
    private float screenCenterX = 0.0F;
    private float screenCenterY = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Bitmap image = null;
    private final android.graphics.Bitmap sprite = null;
    private int loopCounter = 0;
    private int slowCounter = 0;
    private int spriteState = 0;
    private final android.graphics.Matrix imageMatrix = null;
    public static final int SIZE = 3;
    public static final double PULSING_SIZE = 1.34;
    public static final int PULSING_SPEED = 2;
    public static final int SPRITE_VERTICAL = 8;
    public static final int SPRITE_HORIZONTAL = 4;
    public static final int SPRITE_UNUSED = 7;
    public static final float PHONE_ORIENTATION = 90.0F;
    private static int posX;
    private static int posY;
    public static final demo.app.paintball.map.renderables.User.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected android.graphics.Bitmap getImage() {
        return null;
    }
    
    @java.lang.Override()
    public void setSize(int x, int y) {
    }
    
    @java.lang.Override()
    public void render(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    private final void drawSprite(android.graphics.Canvas canvas) {
    }
    
    public final void setOrientation(float degree) {
    }
    
    public User() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011\u00a8\u0006\u0015"}, d2 = {"Ldemo/app/paintball/map/renderables/User$Companion;", "", "()V", "PHONE_ORIENTATION", "", "PULSING_SIZE", "", "PULSING_SPEED", "", "SIZE", "SPRITE_HORIZONTAL", "SPRITE_UNUSED", "SPRITE_VERTICAL", "posX", "getPosX", "()I", "setPosX", "(I)V", "posY", "getPosY", "setPosY", "app_debug"})
    public static final class Companion {
        
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
        
        private Companion() {
            super();
        }
    }
}