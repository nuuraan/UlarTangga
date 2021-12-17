import java.util.Scanner;
public class Dice {
    private int sum1;
    public int playerMelangkah;
    public boolean acakAngka;
    public String giliran;
    public static void main(String[] args) {
        System.out.println("Tekan Enter untuk bermain!");
        Scanner myObj= new Scanner(System.in);
        String turn = myObj.nextLine();
        Dice player1 = new Dice();
        player1.acakAngka();
        System.out.println("Player melangkah sebanyak " + player1.playerMelangkah+" "+ "lantai");
    }
    public void acakAngka(){
        while (true)
        {
            int diceX=(int)(Math.random()*6+1);
            int diceY=(int)(Math.random()*6+1);
            int sum1 = diceX + diceY;
            System.out.println("Roll total = " + sum1);
            playerMelangkah=sum1;
            break;
        }
    }
}
