import java.util.Random;
import java.util.Scanner;

public class Board {
    public int jumlahUlar=3;
    public int jumlahPlayer=2;
    public int jumlahTangga=3;
    public int jumlahLantai=25;
    public static void main(String[] args) {
        System.out.println("\nLet's play UlarTangga!");
        for (int row = 0; row < 5; row++)
        {
            System.out.println("");
            System.out.println("____________________________________");

            for (int column = 0; column < 6; column++)
            {
                System.out.print("| " + "     ");
            }
        }
        System.out.println("");
        System.out.println("____________________________________");
        System.out.println("\nIsi nama Anda untuk bermain!");
        Scanner myObj= new Scanner(System.in);
        String turn = myObj.nextLine();
        Dice player1 = new Dice();
        player1.acakAngka();
        System.out.println("Player melangkah sebanyak " + player1.playerMelangkah+" "+ "lantai");
        System.out.println("\nAnda menginjak lantai spesial!\nJawab pertanyaan matematika berikut dengan benar!");
        try (final Scanner inputScanner = new Scanner(System.in)) {
            final Random random = new Random();

            final Difficulty difficulty = Soal.askDifficulty(inputScanner, random);
            if (difficulty == null) {
                System.out.println("Error! Please enter valid input E or M or H to select level");
                return;
            }
            System.out.println(difficulty.name());
            Soal.askProblem(inputScanner, random, difficulty);
        }
    }
    public void Lantai(){

    }
    public boolean ularTurun(boolean salah) {return true;}
    public boolean tanggaNaik(boolean benar) {return true;}
    public void lantaiPutih(){

    }
    public void garisLantai(){

    }
    public void soalUlar(){

    }
    public void soalTangga(){

    }
    public void tileFinish(){

    }
    public void warnaPlayer (){

    }
    public void playerStep(){

    }
    public void koordPlayer(){

    }
}
