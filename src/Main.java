// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        var console =  new GameConsole<>(new ShooterGame("the shootout game"));
//        int playerIndex = console.addPlayer();
//        console.Playgame(playerIndex);

        Weapon weapon =  Weapon.getWeaponName('P');
        System.out.println("Weapon = "+ weapon + "Minimum level = " + weapon.getMinLevel() + "Hit points = " + weapon.getHitPoints());

        var list = Weapon.getWeaponByLevel(1);
        list.forEach(System.out::println);

        Pirate Amna = new Pirate("Amna");
        System.out.println(Amna);

        PirateGame.getTowns(0).forEach(System.out::println);
        System.out.println("--------------------------------------");
        PirateGame.getTowns(1).forEach(System.out::println);

        var console =  new GameConsole<>(new PirateGame("the pirate game"));
        int playerIndex = console.addPlayer();
        console.Playgame(playerIndex);
    }
}