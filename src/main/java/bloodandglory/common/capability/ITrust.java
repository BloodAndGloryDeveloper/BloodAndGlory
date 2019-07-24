package bloodandglory.common.capability;

public interface ITrust {
    int trust = 0;
    int range[1] {0,1}

    /*以下两个方法是决定你在游戏中是否被攻击的因素，默认*/
    boolean isNeutral(int trust);
    boolean isHostile(int trust);//判断是否为敌对
    boolean isFriendly(int trust);//判断是否为友好
}
