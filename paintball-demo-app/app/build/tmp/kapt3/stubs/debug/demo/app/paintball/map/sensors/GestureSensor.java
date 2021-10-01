package demo.app.paintball.map.sensors;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0011\u0018\u0000 -2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002-.B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J,\u0010\u0019\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0010H\u0016J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0010H\u0016J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0010H\u0016J(\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010\'\u001a\u00020\u000eH\u0016J\u0012\u0010(\u001a\u00020\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010)\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0018\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0018H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Ldemo/app/paintball/map/sensors/GestureSensor;", "Landroid/view/View$OnTouchListener;", "Landroid/view/ScaleGestureDetector$OnScaleGestureListener;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "gestureListener", "Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;", "scrollPanel", "Landroid/view/View;", "(Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;Landroid/view/View;)V", "gestureDetector", "Landroid/view/GestureDetector;", "getGestureListener", "()Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;", "lastScale", "", "scaleDetector", "Landroid/view/ScaleGestureDetector;", "scaleFactor", "getScrollPanel", "()Landroid/view/View;", "up", "", "onDown", "p0", "Landroid/view/MotionEvent;", "onFling", "p1", "p2", "p3", "onLongPress", "", "onScale", "detector", "onScaleBegin", "onScaleEnd", "onScroll", "e1", "e2", "dX", "dY", "onShowPress", "onSingleTapUp", "onTouch", "view", "event", "Companion", "GestureListener", "app_debug"})
public final class GestureSensor extends android.view.GestureDetector.SimpleOnGestureListener implements android.view.View.OnTouchListener, android.view.ScaleGestureDetector.OnScaleGestureListener {
    private final android.view.ScaleGestureDetector scaleDetector = null;
    private final android.view.GestureDetector gestureDetector = null;
    private float scaleFactor = 1.0F;
    private float lastScale = 1.0F;
    private boolean up = false;
    @org.jetbrains.annotations.NotNull()
    private final demo.app.paintball.map.sensors.GestureSensor.GestureListener gestureListener = null;
    @org.jetbrains.annotations.NotNull()
    private final android.view.View scrollPanel = null;
    public static final float zoomLimit = 0.2F;
    public static final float scrollLimit = 40.0F;
    public static final demo.app.paintball.map.sensors.GestureSensor.Companion Companion = null;
    
    @java.lang.Override()
    public boolean onTouch(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    android.view.MotionEvent event) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onScale(@org.jetbrains.annotations.NotNull()
    android.view.ScaleGestureDetector detector) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onScroll(@org.jetbrains.annotations.NotNull()
    android.view.MotionEvent e1, @org.jetbrains.annotations.NotNull()
    android.view.MotionEvent e2, float dX, float dY) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onScaleBegin(@org.jetbrains.annotations.NotNull()
    android.view.ScaleGestureDetector detector) {
        return false;
    }
    
    @java.lang.Override()
    public void onScaleEnd(@org.jetbrains.annotations.NotNull()
    android.view.ScaleGestureDetector detector) {
    }
    
    @java.lang.Override()
    public boolean onDown(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0) {
        return false;
    }
    
    @java.lang.Override()
    public void onShowPress(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0) {
    }
    
    @java.lang.Override()
    public boolean onSingleTapUp(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0) {
        return false;
    }
    
    @java.lang.Override()
    public void onLongPress(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0) {
    }
    
    @java.lang.Override()
    public boolean onFling(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p0, @org.jetbrains.annotations.Nullable()
    android.view.MotionEvent p1, float p2, float p3) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final demo.app.paintball.map.sensors.GestureSensor.GestureListener getGestureListener() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.View getScrollPanel() {
        return null;
    }
    
    public GestureSensor(@org.jetbrains.annotations.NotNull()
    demo.app.paintball.map.sensors.GestureSensor.GestureListener gestureListener, @org.jetbrains.annotations.NotNull()
    android.view.View scrollPanel) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&\u00a8\u0006\n"}, d2 = {"Ldemo/app/paintball/map/sensors/GestureSensor$GestureListener;", "", "onScaleChanged", "", "scaleFactor", "", "onScrollDown", "onScrollUp", "onZoomIn", "onZoomOut", "app_debug"})
    public static abstract interface GestureListener {
        
        public abstract void onScaleChanged(float scaleFactor);
        
        public abstract void onZoomIn();
        
        public abstract void onZoomOut();
        
        public abstract void onScrollUp();
        
        public abstract void onScrollDown();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Ldemo/app/paintball/map/sensors/GestureSensor$Companion;", "", "()V", "scrollLimit", "", "zoomLimit", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}