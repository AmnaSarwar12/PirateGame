import java.security.PublicKey;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Weapon {
        KNIFE(0,12),
        PISTOL(1,13),
        MACHINE_GUN(2,14),
        GERANADE(0,2);

    private int minLevel;
    private int hitPoints = 0;


    Weapon(int minlevel, int hitpoint) {
        this.minLevel = minlevel;
        this.hitPoints =  hitpoint;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public static Weapon getWeaponName(char firstInitail){
        for(Weapon w: values()){
            if(w.name().charAt(0) == firstInitail){
                return w;
            }
        }
        return values()[0];
    }

    public static List<Weapon> getWeaponByLevel(int levelOfPlay){
        List<Weapon> weapons =  new ArrayList<>(EnumSet.allOf(Weapon.class));
        weapons.removeIf(w -> (w.minLevel > levelOfPlay));
        return weapons;
    }
}
