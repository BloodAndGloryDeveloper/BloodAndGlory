package bloodandglory.common.registies;

public class Registry {
    public static final Registry instance = new Registry();

    public void preInit(){
        ItemRegistry.preInit();
    }
}
