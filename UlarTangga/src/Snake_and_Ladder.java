import java.io.*; // untuk memeriksa inputan user apakah sudah sesuai dengan yang diinginkan
import java.util.Random;// sebagai
import java.util.Scanner;// untuk menerima inputan  dari user

public class Snake_and_Ladder {
    //method utama (psvm), untuk memasukkan method dari io
    public static void main (String [] args) throws IOException {
        BufferedReader myInput2 = new BufferedReader (new InputStreamReader (System.in));
        //Cetak Welcome screen dan instruksi permainan
        System.out.println ("\n\n\t\t\t\t\t\t\t\tSELAMAT DATANG DI GAME ULAR TANGGA\n\n");
        System.out.println ("\t\t\t\t\t\t\t\t\t\t\tDeskripsi:");
        System.out.println ("\t\t\t Kamu akan bermain melawan komputer. Kalian berdua akan mulai di kotak 1");
        System.out.println ("\t\t\t\t\t Pemain pertama yang menginjak kotak 100 adalah pemenangnya!");
        System.out.println ("\t\t\t\t Namun, di beberapa kotak akan terdapat ular atau tangga (rahasia)");
        System.out.println ("\t\t\t Jika kamu menginjaknya, kamu harus menjawab 1 soal matematika dengan benar!");
        System.out.println ("\t\t\t\t\t\t Selamat bermain and may the best player win!!\n");

        int counter= 100,iteration=-1; //mencetak board game dari angka 1-100 dan agar bentuknya 10x10
        System.out.println ("--------------------------------Game Board--------------------------------");
        while (counter > 0){
            if (counter%10 == 0 && counter != 100){
                if(iteration==-1) {
                    counter-=9;
                    iteration=1;
                }
                else {
                    System.out.print(counter+"\t");
                    counter-=10;
                    iteration=-1;
                }
                if(counter!=0)
                    System.out.print("\n" + counter + "\t\t");
            }
            else
                System.out.print(counter + "\t\t");
            counter+=iteration;
        }
        System.out.println();
        System.out.println ("---------------------------------------------------------------------------");

        String sGame = "y"; //user harus menekan y atau Y untuk bermain,agar menjadi patokan game dimulai
        System.out.print ("\nApakah Anda ingin bermain? Y or N  > ");
        sGame = myInput2.readLine (); //membaca inputan user,apakah sama dengan inputan y
        System.out.print ("\n");
        while (sGame.equals ("y") || sGame.equals ("Y")) //jika user menekan y, maka program ke method startGame
        {
            sGame = startGame(sGame); //permainan dimulai dengan menjalankan method startGame
        }
        System.out.println ("\n\n\t\t\t\tSAMPAI JUMPA LAGI!"); //jika user menekan selain y
    }

    /*startGame method: berfungsi untuk mengatur jalannya permainan (bertanya ke user jika dia ingin memulai/melanjutkan permainan)
    mengatur variable untuk posisi user dan computer pada board (Dice), dan mengatur variable letak ular dan tangga pada board*/

    public static String startGame (String start) throws IOException {
        BufferedReader myInput = new BufferedReader (new InputStreamReader (System.in));
        int userPosition = 1; //posisi default user di 1
        int compPosition = 1; //posisi default computer di 1
        int diceRoll = 0; //dadu pertama
        int diceRoll2 = 0; //dadu kedua
        int userRoll = 1; //menyimpan nilai dadu yang keluar utk user
        int compRoll = 1; //menyimpan nilai dadu yang keluar utk computer
        String playAgain = "y";
        int snakesLaddersArray [] = new int [6]; //membuat array dengan 6 elemen (3 ular 3 tangga)
        //memasang posisi ular dan tangga pada board
        snakesLaddersArray [0] = 54;
        snakesLaddersArray [1] = 90;
        snakesLaddersArray [2] = 99;
        snakesLaddersArray [3] = 9;
        snakesLaddersArray [4] = 40;
        snakesLaddersArray [5] = 67;
        while (playAgain.equals ("y") || playAgain.equals ("Y")) {
            userRoll =  getDice(diceRoll, diceRoll2); //mengambil data (hasil lemparan dadu) di method getDice
            compRoll =  getDice(diceRoll, diceRoll2);
            System.out.println("---------------------------------------------------------------------------");
            System.out.println ("\t\t\t\t\t-----------------------------------------");
            System.out.println ("\t\t\t\t\t|\tHasil lemparan dadu Anda:" + userRoll + "\t\t\t|\t");
            System.out.println ("\t\t\t\t\t|\tHasil lemparan dadu Komputer: " + compRoll + "\t\t|\t");
            System.out.println ("\t\t\t\t\t-----------------------------------------");
            userPosition = userPosition + userRoll; //mengatur posisi,jika posisi user melebihi 100, maka user tdk bergerak
            compPosition = compPosition + compRoll;
            userPosition = getP(userPosition, userRoll, snakesLaddersArray);//mengubah posisi user sesuai lemparan dadu dan
                                                                            // memeriksa jika user menginjak ular/tangga
            compPosition = compgetP(compPosition, compRoll, snakesLaddersArray, userPosition); //tambahan parameter userPosition
                                                                                                // utk memeriksa jika user sudah menang
            System.out.println("\t\t\t*****************************************************");
            System.out.println ("\t\t\t*\t\tAnda sekarang berada di kotak " + userPosition + "\t\t\t*");
            System.out.println ("\t\t\t*\t\tKomputer sekarang berada di kotak " + compPosition + "\t\t*");
            System.out.println("\t\t\t*****************************************************");
            //jika permainan sudah berakhir dan user ingin bermain lagi
            if (userPosition == 100 || compPosition == 100) {
                userPosition = 1;
                compPosition = 1;
                System.out.print ("\nApakah Anda ingin bermain lagi? Y or N  > ");
                playAgain = myInput.readLine ();
                System.out.print ("\n\n\n\n\n\n\n\n\n\n\n\n");
            }
            //melanjutkan permainan jika belum menang
            else {
                System.out.print ("\nApakah Anda ingin melanjutkan permainan? Y or N  > ");
                playAgain = myInput.readLine ();
            }
        }
        return playAgain; //return parameter playAgain seandainya user ingin bermain lagi
    }

    /*getDice method: Method untuk melempar 2 buah dadu dari angka 1-6 dan menjumlahkan hasilnya*/

    public static int getDice (int diceRoll, int diceRoll2)
    {
        diceRoll = (int)(Math.random()*6 )+1 ;
        diceRoll2 = (int)(Math.random()*6 )+1 ;
        int move = diceRoll + diceRoll2;
        return move; // return parameter move akan membawa hasil diceroll ke startGame
    }

    /*getP method: berfungsi untuk memeriksa apakah user menginjak ular/tangga lalu
    mengubah posisi user sesuai dengan titik akhir ular/tangga*/

    public static int getP (int userPosition, int userRoll, int[] snakesLaddersArray) throws IOException {
        if(userPosition == snakesLaddersArray[0]) //posisi ular 1
        {
            System.out.println("\nAnda menginjak Ular!\nJawab pertanyaan matematika berikut dengan benar!");
            try (final Scanner inputScanner = new Scanner(System.in)) {
                final Random random = new Random();
                final Difficulty difficulty = Soal.askDifficulty(inputScanner, random);
                if (difficulty == null) {
                    System.out.println("Error! Please enter valid input E or M or H to select level");
                    return userPosition;
                }
                System.out.println(difficulty.name());
                Soal.askProblem(inputScanner, random, difficulty);
            }
            userPosition = 100; //posisi baru
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~ANDA MENGINJAK ULAR! MOHON TURUN!~~~~~~~~~~~~~");
        }
        else if (userPosition == snakesLaddersArray[1]) //posisi ular 2
        {
            System.out.println("\nAnda menginjak Ular!\nJawab pertanyaan matematika berikut dengan benar!");
            try (final Scanner inputScanner = new Scanner(System.in)) {
                final Random random = new Random();
                final Difficulty difficulty = Soal.askDifficulty(inputScanner, random);
                if (difficulty == null) {
                    System.out.println("Error! Please enter valid input E or M or H to select level");
                    return userPosition;
                }
                System.out.println(difficulty.name());
                Soal.askProblem(inputScanner, random, difficulty);
            }
            userPosition = 100;
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~ANDA MENGINJAK ULAR! MOHON TURUN!~~~~~~~~~~~~~");

        }
        else if (userPosition == snakesLaddersArray[2]) //posisi ular 3
        {
            System.out.println("\nAnda menginjak Ular!\nJawab pertanyaan matematika berikut dengan benar!");
            try (final Scanner inputScanner = new Scanner(System.in)) {
                final Random random = new Random();
                final Difficulty difficulty = Soal.askDifficulty(inputScanner, random);
                if (difficulty == null) {
                    System.out.println("Error! Please enter valid input E or M or H to select level");
                    return userPosition;
                }
                System.out.println(difficulty.name());
                Soal.askProblem(inputScanner, random, difficulty);
            }
            userPosition = 100;
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~ANDA MENGINJAK ULAR! MOHON TURUN!~~~~~~~~~~~~~");
        }
        else if (userPosition == snakesLaddersArray[3]) //posisi tangga 1
        {
            System.out.println("\nAnda menginjak Tangga!\nJawab pertanyaan matematika berikut dengan benar!");
            try (final Scanner inputScanner = new Scanner(System.in)) {
                final Random random = new Random();
                final Difficulty difficulty = Soal.askDifficulty(inputScanner, random);
                if (difficulty == null) {
                    System.out.println("Error! Please enter valid input E or M or H to select level");
                    return userPosition;
                }
                System.out.println(difficulty.name());
                Soal.askProblem(inputScanner, random, difficulty);
            }
            userPosition = 100;
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~ANDA MENGINJAK TANGGA! SILAHKAN NAIK!~~~~~~~~~~~~~");

        }
        else if (userPosition == snakesLaddersArray[4]) //posisi tangga 2
        {
            System.out.println("\nAnda menginjak Tangga!\nJawab pertanyaan matematika berikut dengan benar!");
            try (final Scanner inputScanner = new Scanner(System.in)) {
                final Random random = new Random();
                final Difficulty difficulty = Soal.askDifficulty(inputScanner, random);
                if (difficulty == null) {
                    System.out.println("Error! Please enter valid input E or M or H to select level");
                    return userPosition;
                }
                System.out.println(difficulty.name());
                Soal.askProblem(inputScanner, random, difficulty);
            }
            userPosition = 100;
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~ANDA MENGINJAK TANGGA! SILAHKAN NAIK!~~~~~~~~~~~~~");

        }
        else if (userPosition == snakesLaddersArray[5]) //posisi tangga 3
        {
            System.out.println("\nAnda menginjak Tangga!\nJawab pertanyaan matematika berikut dengan benar!");
            try (final Scanner inputScanner = new Scanner(System.in)) {
                final Random random = new Random();
                final Difficulty difficulty = Soal.askDifficulty(inputScanner, random);
                if (difficulty == null) {
                    System.out.println("Error! Please enter valid input E or M or H to select level");
                    return userPosition;
                }
                System.out.println(difficulty.name());
                Soal.askProblem(inputScanner, random, difficulty);
            }
            userPosition = 100;
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~ANDA MENGINJAK TANGGA! SILAHKAN NAIK!~~~~~~~~~~~~~");
        }
        if (userPosition < 0 || userPosition > 112)
        {
            System.out.println ("TERJADI EROR");
        }
        else if (userPosition > 100) //jika posisi user melebihi 100
        {
            userPosition = userPosition - userRoll; //user tidak bergerak
            System.out.println ("Hasil lemparan mu melebihi 100, kamu tidak bergerak!");
        }
        else if (userPosition == 100)
        {
            System.out.println ("SELAMAT ANDA MEMENANGKAN PERMAINAN!!");
        }
        return userPosition;
    }

    /*compgetP method: sama dengan method getP, perbedaannya komputer tidak perlu menjawab soal matematika*/

    public static int compgetP (int compPosition, int compRoll, int snakesLaddersArray [], int userPosition) throws IOException {
        if (compPosition == snakesLaddersArray[0]) {
            compPosition = 20;
            System.out.println("\t\t\t\t~~~~~~~~~~~~~COMPUTER MENGINJAK ULAR! MOHON TURUN!~~~~~~~~~~~~~");
        } else if (compPosition == snakesLaddersArray[1]) {
            compPosition = 20;
            System.out.println("\t\t\t\t~~~~~~~~~~~~~COMPUTER MENGINJAK ULAR! MOHON TURUN!~~~~~~~~~~~~~");

        } else if (compPosition == snakesLaddersArray[2]) {
            compPosition = 20;
            System.out.println("\t\t\t\t~~~~~~~~~~~~~COMPUTER MENGINJAK ULAR! MOHON TURUN!~~~~~~~~~~~~~");
        } else if (compPosition == snakesLaddersArray[3]) {
            compPosition = 20;
            System.out.println("COMPUTER MENGINJAK TANGGA! SILAHKAN NAIK!");
        } else if (compPosition == snakesLaddersArray[4]) {
            compPosition = 20;
            System.out.println("COMPUTER MENGINJAK TANGGA! SILAHKAN NAIK!");

        } else if (compPosition == snakesLaddersArray[5]) {
            compPosition = 20;
            System.out.println("COMPUTER MENGINJAK TANGGA! SILAHKAN NAIK!");
        }
        if (compPosition < 0 || compPosition > 112) {
            System.out.println("TERJADI EROR");
        } else if (compPosition > 100) {
            compPosition = compPosition - compRoll;
            System.out.println("Lemparan dadu computer melebihi 100, tidak bergerak!");
        } else if (compPosition == 100 && userPosition != 100) {
            System.out.println("COMPUTER MEMENANGKAN PERMAINAN!");
        }
        return compPosition;
    }
}