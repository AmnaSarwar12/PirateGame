import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PirateGame extends  Game<Pirate>{

    private static final List<List<String>> levelMaps;

    //private final List<Player> players;

    //using static initialize
    //---------------------------------------------------------
    static {
        levelMaps =  new ArrayList<>();
        System.out.println("Loading data-----");
        loadData();
        if(levelMaps.size() == 0){
            throw new RuntimeException("Could not load data, try again later");

        }
        System.out.println("Finished loading data");
    }
    //------------------------------------------------------------

    public PirateGame(String gameName) {
        super(gameName);
    }


    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
    }

    @Override
    public Map<Character, GameAction> getGameAction(int playerIndex) {
        Pirate pirate =  getPlayer(playerIndex);
        System.out.println(pirate);
        List<Weapon> weapons = Weapon.getWeaponByLevel(pirate.value("level"));

        Map<Character, GameAction> map = new LinkedHashMap<>();
        for(Weapon weapon: weapons){
            char init = weapon.name().charAt(0);
            map.put(init, new GameAction(init, "Use " +weapon, this::useWeapon));
            map.putAll(getStandardActions());

        }
        return map;
    }

    private static void loadData(){
        //level town 1
        levelMaps.add(new ArrayList<>(List.of(
                "Karachi, saddar",
                "Islamabad, f11",
                "lahore, zaman park"
        )));

        //level town 2
        levelMaps.add(new ArrayList<>(List.of(
                "Karachi, nazimabad",
                "Islamabad, f9",
                "lahore, badshahi masjid"
        )));
    }

    public static List<String > getTowns(int level){
        if(level <= (levelMaps.size() - 1)){
            return levelMaps.get(level);
        }
        return null;
    }

    private boolean useWeapon(int playerIndex){
        return getPlayer(playerIndex).useWeapon();
    }

    @Override
    public boolean executeGameAction(int player, GameAction action) {
        getPlayer(player).setCurrentWeapon(Weapon.getWeaponName(action.Key()));
        return super.executeGameAction(player, action);
    }
}
