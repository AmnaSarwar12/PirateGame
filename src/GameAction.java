import java.util.function.Predicate;

public record GameAction(char Key, String prompt, Predicate<Integer> action) {


}
