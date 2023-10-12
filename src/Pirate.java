import java.util.*;

public class Pirate implements Player{
    private final Map<String, Integer> gameData;
    private final String name;
    private final List<String> townVisited = new LinkedList<>();
    private Weapon currentWeapon;

    public Pirate(String name) {
        this.name = name;
    }

    //---------------------------------------------------------------
    {
        gameData = new HashMap<>(Map.of(
                "Health",100,
                "score", 0,
                "level", 0,
                "townIndex", 0
        ));
        visitTown();
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;

    }




     void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    int value(String name){
        return gameData.get(name);
    }

    private void setValue(String name, int value){
        gameData.put(name, value);
    }

    private void adjustValue(String name, int adj){
        gameData.compute(name, (k,v)-> v += adj);
    }

    private void adjusthealth(int adj){
        int health = value("health");
        health += adj;
        health = (health < 0) ? 0 : ((health > 100) ? 100: health);
        setValue("health", health);
    }

    boolean useWeapon(){
        System.out.println("Used the " +currentWeapon);
        return visitNextTown();
    }

    boolean visitTown(){
        List<String > levelTowns = PirateGame.getTowns(value("level"));
        if(levelTowns == null) return true;
        String  Town = levelTowns.get(value("townIndex"));
        if(Town != null){
            townVisited.add(Town);
            return false;
        }
        return true;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        var current = ((LinkedList<String>) townVisited).getLast();
        String[] simpleNames = new String[townVisited.size()];
        Arrays.setAll(simpleNames, i->townVisited.get(i).split(",")[0]);
        return "-->" + current +
                "\t Pirate " + name + " " + gameData +
                "\n\ttownsvisited = " + Arrays.toString(simpleNames);
    }

    private boolean visitNextTown(){
        int townIndex = value("townindex");
        var towns = PirateGame.getTowns(value("level"));
        if(towns == null)return true;{

            if(townIndex >= (towns.size() - 1)){
                System.out.println("leveling up! Bonus: 500 points");
                adjustValue("score", 500);
                adjustValue("level", 1);
                setValue("townindex", 0);
            }else{
                System.out.println("sailing to next town! Bonus: 50 points");
                adjustValue("townindex", 1);
                adjustValue("score", 50);
            }
        }
        return visitTown();
    }
}
