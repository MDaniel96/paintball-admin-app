package demo.app.paintball.fragments.buttons;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,J\b\u0010-\u001a\u00020*H\u0016J\u000e\u0010.\u001a\u00020*2\u0006\u0010+\u001a\u00020,J\u0010\u0010/\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020*H\u0016R\u0018\u0010\u0003\u001a\u00020\u0004X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\u00020\u0004X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u0018\u0010\f\u001a\u00020\u0004X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u0018\u0010\u000f\u001a\u00020\u0004X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u0018\u0010\u0012\u001a\u00020\u0004X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u0018\u0010\u0015\u001a\u00020\u0004X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0019X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u00020\u0004X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\bR\u001a\u0010 \u001a\u00020!X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020!X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010#\"\u0004\b(\u0010%\u00a8\u00063"}, d2 = {"Ldemo/app/paintball/fragments/buttons/MapButtonsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "btnBottom", "Landroid/view/View;", "getBtnBottom", "()Landroid/view/View;", "setBtnBottom", "(Landroid/view/View;)V", "btnBottomLayout", "getBtnBottomLayout", "setBtnBottomLayout", "btnBottomTextView", "getBtnBottomTextView", "setBtnBottomTextView", "btnMiddle", "getBtnMiddle", "setBtnMiddle", "btnMiddleLayout", "getBtnMiddleLayout", "setBtnMiddleLayout", "btnMiddleTextView", "getBtnMiddleTextView", "setBtnMiddleTextView", "isOpen", "", "()Z", "setOpen", "(Z)V", "rootLayout", "getRootLayout", "setRootLayout", "screenHeight", "", "getScreenHeight", "()F", "setScreenHeight", "(F)V", "translateY", "getTranslateY", "setTranslateY", "changeLevel", "", "level", "", "hide", "initLevel", "onAttach", "context", "Landroid/content/Context;", "show", "app_debug"})
public abstract class MapButtonsFragment extends androidx.fragment.app.Fragment {
    private float screenHeight = 0.0F;
    private float translateY = 0.0F;
    private boolean isOpen = false;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.view.View getRootLayout();
    
    public abstract void setRootLayout(@org.jetbrains.annotations.NotNull()
    android.view.View p0);
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.view.View getBtnBottom();
    
    public abstract void setBtnBottom(@org.jetbrains.annotations.NotNull()
    android.view.View p0);
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.view.View getBtnBottomLayout();
    
    public abstract void setBtnBottomLayout(@org.jetbrains.annotations.NotNull()
    android.view.View p0);
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.view.View getBtnBottomTextView();
    
    public abstract void setBtnBottomTextView(@org.jetbrains.annotations.NotNull()
    android.view.View p0);
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.view.View getBtnMiddle();
    
    public abstract void setBtnMiddle(@org.jetbrains.annotations.NotNull()
    android.view.View p0);
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.view.View getBtnMiddleLayout();
    
    public abstract void setBtnMiddleLayout(@org.jetbrains.annotations.NotNull()
    android.view.View p0);
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.view.View getBtnMiddleTextView();
    
    public abstract void setBtnMiddleTextView(@org.jetbrains.annotations.NotNull()
    android.view.View p0);
    
    protected final float getScreenHeight() {
        return 0.0F;
    }
    
    protected final void setScreenHeight(float p0) {
    }
    
    protected final float getTranslateY() {
        return 0.0F;
    }
    
    protected final void setTranslateY(float p0) {
    }
    
    protected final boolean isOpen() {
        return false;
    }
    
    protected final void setOpen(boolean p0) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void initLevel(int level) {
    }
    
    public final void changeLevel(int level) {
    }
    
    public void show() {
    }
    
    public void hide() {
    }
    
    public MapButtonsFragment() {
        super();
    }
}