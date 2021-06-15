import java.util.Scanner;

public class Othello {
    Player intelligent=new Intelliegent();
    Player human=new Human();
    Board board;

    public Othello() {
    }
    void letTheGameBegins()
    {


        System.out.println("Welcome");
        int c, d;

        for (c = 1; c <= 327656; c++)
            for (d = 1; d <= 385994; d++)
            {
                //for delay only
            }


System.out.println("To");
        int c1, d1;

        for (c1 = 1; c1 <= 327668; c1++)
            for (d1 = 1; d1 <= 398994; d1++)
            {
                //for delay only
            }

        System.out.println("Othello");

        int c2, d2;

        for (c2 = 1; c2 <= 32766; c2++)
            for (d2 = 1; d2 <= 39994; d2++)
            {
                //for delay only
            }


System.out.println("******************PARTICIPANTS************************");
        System.out.println("X==Computer");
        System.out.println("O==Human");
        System.out.println("*******************************************************");






        //System.out.println("Enter Board Size");
        Scanner in = new Scanner(System.in);
        //int s = in. nextInt();
        board=new Board(8);
        System.out.println("Your Board Created");
        board.displayBoard();
        int x, y;
        int check;
        System.out.println("enter 0 for easy and 1 for difficult");
        check = in. nextInt();

        if(check==0) {
    while (board.fill != (8 * 8)) {
        while (true) {
            System.out.println("Enter x axis");
            x = in.nextInt();
            System.out.println("Enter y axis");
            y = in.nextInt();
            if (board.validMove(x, y, human.mypiece)) {
                board.makeMove(x, y, human.mypiece);
                board.fill++;
                break;
            } else {
                System.out.println("human invalid move");
            }
        }
        board.displayBoard();
        //board.displayBoard();
        System.out.println("Computer's Turn");
        int[] temp = new int[2];
        temp = board.checkMinMaxdifi();


        if (board.validMove(temp[0], temp[1], intelligent.mypiece)) {
            board.makeMove(temp[0], temp[1], intelligent.mypiece);
            board.displayBoard();
            board.fill++;

        } else {

        }

    }
}
else
{
    while (board.fill != (8 * 8)) {
        while (true) {
            System.out.println("Enter x axis");
            x = in.nextInt();
            System.out.println("Enter y axis");
            y = in.nextInt();
            if (board.validMove(x, y, human.mypiece)) {
                board.makeMove(x, y, human.mypiece);
                board.fill++;
                break;
            } else {
                System.out.println("human invalid move");
            }
        }
        board.displayBoard();
        //board.displayBoard();
        System.out.println("Computer's Turn");
        int[] temp = new int[2];
        temp = board.checkMinMax();


        if (board.validMove(temp[0], temp[1], intelligent.mypiece)) {
            board.makeMove(temp[0], temp[1], intelligent.mypiece);
            board.displayBoard();
            board.fill++;

        } else {

        }

    }
}


    }
}






