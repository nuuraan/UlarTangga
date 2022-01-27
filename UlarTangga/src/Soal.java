import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Soal extends Snake_and_Ladder{
    public static Difficulty askDifficulty(final Scanner inputScanner, final Random random) {
        System.out.println("\nSilahkan memasukkan huruf: E untuk easy; M untuk medium; H untuk hard");
        switch (inputScanner.next().toLowerCase().charAt(0)){
            case 'e':
                return new Difficulty(
                        "Easy", random, 10,
                        new String[] {"Jawaban Anda: BENAR"},
                        new String[] {"Jawaban Anda: SALAH"});
            case 'm':
                return new Difficulty(
                        "Medium", random, 100,
                        new String[] {"Jawaban Anda: BENAR"},
                        new String[] {"Jawaban Anda: SALAH"});
            case 'h':
                return new Difficulty(
                        "Hard", random, 10000,
                        new String[] {"Jawaban Anda: BENAR"},
                        new String[] {"Jawaban Anda: SALAH"});
            default:
                return null;
        }
    }

    public static void askProblem(final Scanner scanner, final Random random, final Difficulty difficulty) {

        final int firstNumber = difficulty.number();
        final int secondNumber = difficulty.number();

        final String operations = "+-*";
        final char operation = operations.charAt(random.nextInt(operations.length()));

        final int actualAnswer;
        switch (operation) {
            case '+':
                actualAnswer = firstNumber + secondNumber;
                break;
            case '-':
                actualAnswer = firstNumber - secondNumber;
                break;
            case '*':
                actualAnswer = firstNumber * secondNumber;
                break;
            default:
                throw new IllegalStateException();
        }

        System.out.println(String.format("Berapa hasil dari %d %c %d?", firstNumber, operation, secondNumber));

        final int userAnswer = scanner.nextInt();
        if (userAnswer == actualAnswer){
            System.out.println(difficulty.winMessage());
        } else {
            System.out.println(difficulty.loseMessage());
        }
    }

}
class Difficulty {

    private final String name;
    private final Random random;
    private final int bound;

    private final String[] winMessages;
    private final String[] loseMessages;

    public Difficulty(
            final String name,
            final Random random,
            final int maxNumber,
            final String[] winMessages,
            final String[] loseMessages) {
        super();

        this.name = name;
        this.random = random;
        this.bound = maxNumber + 1;
        this.winMessages = Arrays.copyOf(winMessages, winMessages.length);
        this.loseMessages = Arrays.copyOf(loseMessages, loseMessages.length);
    }

    public String name() {
        return this.name;
    }

    public int number() {
        return this.random.nextInt(this.bound);
    }

    public String winMessage() {
        return this.winMessages[this.random.nextInt(this.winMessages.length)];
    }

    public String loseMessage() {
        return this.loseMessages[this.random.nextInt(this.winMessages.length)];
    }
}