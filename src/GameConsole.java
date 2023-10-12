import java.util.Scanner;

public class GameConsole<T extends Game<? extends Player>> {
    private final T game;
    private static final Scanner scanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    public int addPlayer(){
        System.out.println("Enter your playing name:");
        String name =  scanner.nextLine();
        System.out.printf("Welcome to %s %s !%n".formatted(game.getGameName(), name));
        return game.addPlayer(name);
    }

    public void Playgame(int playerIndex){
        boolean done =  false;
        while (!done){
            var gameActions =  game.getGameAction(playerIndex);
            System.out.println("Select one of the following game");
            for(Character c: gameActions.keySet()){
                String propmt = gameActions.get(c).prompt();
                System.out.println("\t" + propmt + "(" + c + ")");
            }
            System.out.println("Enter next action?:");
            char nextMove = scanner.nextLine().toUpperCase().charAt(0);
            GameAction gameaction = gameActions.get(nextMove);
            if(gameaction != null){
                System.out.println("--------------------------------------");
                done = game.executeGameAction(playerIndex, gameaction);
                if(!done){
                    System.out.println("--------------------------------------------");
                }
            }
        }
    }
}
