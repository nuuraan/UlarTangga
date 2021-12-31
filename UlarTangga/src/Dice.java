public class Dice extends Board{
    private int sum1;
    public int playerMelangkah;
    public boolean acakAngka;
    public String giliran;
    public void acakAngka(){
        while (true)
        {
            int diceX=(int)(Math.random()*6+1);
            int diceY=(int)(Math.random()*6+1);
            int sum1 = diceX + diceY;
            System.out.println("\nRoll total = " + sum1);
            playerMelangkah=sum1;
            break;
        }
    }
}
