package demineur.scenes;
import demineur.engine.*;

public class MainScene extends Scene {
    private static final float WAVE_SPEED = 0.7f;
    private static final float WAVE_HEIGHT = 5.0f;
    private static final float TITLE_RISE_SPEED = 10.0f;
    private static final int TITLE_BASE_Y = 0;
    private static final int ENTER_X_OFFSET = -5; 
    private static final float ANIMATION_DURATION = 2.0f;
    
    private float titleTime = 0;
    private float animationTime = ANIMATION_DURATION;
    private boolean isAnimating = true;
    
    public MainScene(Context context) {
        super(context);
    }
    
    @Override
    public void update(float dt) {
        float dtSeconds = dt * 0.001f;
        
        titleTime += dtSeconds;
        
        if (isAnimating && animationTime > 0) {
            animationTime -= dtSeconds;
            if (animationTime < 0) {
                animationTime = 0;
                isAnimating = false;
            }
        }
        
        int titleY = calculateTitleY();
        int enterX = calculateEnterX();
        
        context.drawImage("marwane_std", 0, titleY + (enterX / 2));
        context.drawImage("enter", enterX, 85);
    }
    
    @Override
    public void exit() {
        
    }
    
    @Override 
    public void load() {
        
    }
    
    private int calculateTitleY() {
        float wave = (float)Math.sin(titleTime * WAVE_SPEED * Math.PI * 2) * WAVE_HEIGHT;
       
        float riseOffset = 0;
        if (isAnimating) {
            float progress = 1.0f - (animationTime / ANIMATION_DURATION);
            float eased = 1.0f - (float)Math.pow(1.0f - progress, 3);
            riseOffset = eased * TITLE_RISE_SPEED;
        } else {
            riseOffset = TITLE_RISE_SPEED;
        }
        
        return TITLE_BASE_Y - (int)riseOffset + (int)wave;
    }
    
    private int calculateEnterX() {
        int finalX = ENTER_X_OFFSET;
        
        if (!isAnimating && animationTime == 0) {
            return finalX;
        }
         
        float progress = 1.0f - (animationTime / ANIMATION_DURATION);
        float eased = 1.0f - (float)Math.pow(1.0f - progress, 3);
       
        int totalDistance = context.getDisplayWidth() + ENTER_X_OFFSET;
        
        return -context.getDisplayWidth() + (int)(eased * totalDistance);
    }
}