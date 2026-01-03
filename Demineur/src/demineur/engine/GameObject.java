package demineur.engine;

public abstract class GameObject {
    
    protected Context context;
    protected boolean update_flag;
    protected boolean alive_flag;
    
    public GameObject(Context context_) {
        context = context_;     
        update_flag = true; 
        alive_flag = true;
    };
    
    public void kill() {
        alive_flag = false; 
    };
    
    public abstract void update(float dt); 
};
