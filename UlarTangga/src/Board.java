public class Board extends Dice {
    public int jumlahUlar=3;
    public int jumlahPlayer=2;
    public int jumlahTangga=3;
    public int jumlahLantai=25;
    public static void main(String[] args) {
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
