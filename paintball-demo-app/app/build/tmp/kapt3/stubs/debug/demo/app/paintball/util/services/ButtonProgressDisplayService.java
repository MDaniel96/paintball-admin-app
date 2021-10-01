package demo.app.paintball.util.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Ldemo/app/paintball/util/services/ButtonProgressDisplayService;", "", "button", "Lmbanje/kurt/fabbutton/FabButton;", "activity", "Landroid/app/Activity;", "(Lmbanje/kurt/fabbutton/FabButton;Landroid/app/Activity;)V", "handler", "Landroid/os/Handler;", "progressValue", "", "runnable", "Ljava/lang/Runnable;", "unitOfProgressChange", "show", "", "duration", "", "stop", "Companion", "app_debug"})
public final class ButtonProgressDisplayService {
    private float progressValue = 0.0F;
    private float unitOfProgressChange = 0.0F;
    private final android.os.Handler handler = null;
    private final java.lang.Runnable runnable = null;
    private final mbanje.kurt.fabbutton.FabButton button = null;
    private final android.app.Activity activity = null;
    public static final float MAX_PROGRESS_VALUE = 100.0F;
    public static final long DELAY = 400L;
    public static final demo.app.paintball.util.services.ButtonProgressDisplayService.Companion Companion = null;
    
    public final void show(long duration) {
    }
    
    public final void stop() {
    }
    
    public ButtonProgressDisplayService(@org.jetbrains.annotations.NotNull()
    mbanje.kurt.fabbutton.FabButton button, @org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Ldemo/app/paintball/util/services/ButtonProgressDisplayService$Companion;", "", "()V", "DELAY", "", "MAX_PROGRESS_VALUE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}