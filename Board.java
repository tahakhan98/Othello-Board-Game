import java.util.ArrayList;
import java.util.Random;

public class Board {

    //char board[][];
    char[][] board ;
    int size;
    int fill;
//Board tempboard;

    public Board() {
        board=new char[8][8];
        size=8;
    }

    public Board(int _size)
    {
        fill=4;

        size=_size;
        board = new char[size][size];
        for (int i = 0; i < size; i++)
        {

            for (int j = 0; j < size; j++)
            {
                board[i][j] = '*';

            }
            if(size==8){
                board[3][3] = 'X';
                board[3][4] = 'O';
                board[4][3] = 'O';
                board[4][4] = 'X';
            }
            if(size==10){
                board[4][4] = 'X';
                board[4][5] = 'O';
                board[5][4] = 'O';
                board[5][5] = 'X';
            }
        }

    }

    void displayBoard()

    {


        for (int y = 7; y >= 0; y--)
        {
            System.out.print(  y + " ");
            for (int x = 0; x < 8; x++)
                System.out.print(  " " + board[x][y]);
            System.out.println("");
        }
        System.out.println("   0 1 2 3 4 5 6 7");

    }


    void flipPieces(int x, int y, int deltaX, int deltaY,char myPiece, char opponentPiece)
    {
        while (board[x][y] == opponentPiece)
        {
            board[x][y] = myPiece;
            x += deltaX;
            y += deltaY;
        }
    }


    // Checks a direction from x,y to see if we can make a move
    boolean checkFlip(int x, int y, int deltaX, int deltaY,
                      char myPiece, char opponentPiece)
    {
        if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8))
        {
            if (board[x][y] == opponentPiece)
            {
                while ((x >= 0) && (x < 8) && (y >= 0) && (y < 8))
                {
                    x += deltaX;
                    y += deltaY;
                    if (board[x][y] == '*') // not consecutive
                        return false;
                    if (board[x][y] == myPiece)
                        return true; // At least one piece we can flip
                    else
                    {
                        //just keep scanning in our direction
                    }

                }
            }
        }
        return false; // Either no consecutive opponent pieces or hit the edge
    }


    void makeMove(int x, int y, char piece)
    {
// Put the piece at x,y
        board[x][y] = piece;
//fill++;
// Figure out the character of the opponent's piece
        char opponent = 'O';
        if (piece == 'O')
            opponent = 'X';
// Check to the left
        if (checkFlip( x - 1, y, -1, 0, piece, opponent))
            flipPieces( x - 1, y, -1, 0, piece, opponent);
// Check to the right
        if (checkFlip( x + 1, y, 1, 0, piece, opponent))
            flipPieces( x + 1, y, 1, 0, piece, opponent);
// Check down
        if (checkFlip( x, y-1, 0, -1, piece, opponent))
            flipPieces( x, y-1, 0, -1, piece, opponent);
// Check up
        if (checkFlip( x, y + 1, 0, 1, piece, opponent))
            flipPieces( x, y + 1, 0, 1, piece, opponent);
// Check down-left
        if (checkFlip( x-1, y - 1, -1, -1, piece, opponent))
            flipPieces( x-1, y - 1, -1, -1, piece, opponent);
// Check down-right
        if (checkFlip( x + 1, y - 1, 1, -1, piece, opponent))
            flipPieces( x + 1, y - 1, 1, -1, piece, opponent);
// Check up-left
        if (checkFlip( x - 1, y + 1, -1, 1, piece, opponent))
            flipPieces( x - 1, y + 1, -1, 1, piece, opponent);
// Check up-right
        if (checkFlip( x + 1, y + 1, 1, 1, piece, opponent))
            flipPieces( x + 1, y + 1, 1, 1, piece, opponent);
    }


    boolean validMove(int x, int y, char piece)
    {
// Check that the coordinates are empty
        if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8))
        {
            if (board[x][y] != '*')
                return false;
// Figure out the character of the opponent's piece
            char opponent = 'O';
            if (piece == 'O')
                opponent = 'X';
// If we can flip in any direction, it is valid
// Check to the left
            if (checkFlip( x - 1, y, -1, 0, piece, opponent))
                return true;
// Check to the right
            if (checkFlip( x + 1, y, 1, 0, piece, opponent))
                return true;
// Check down
            if (checkFlip( x, y - 1, 0, -1, piece, opponent))
                return true;
// Check up
            if (checkFlip( x, y + 1, 0, 1, piece, opponent))
                return true;
// Check down-left
            if (checkFlip( x - 1, y - 1, -1, -1, piece, opponent))
                return true;
// Check down-right
            if (checkFlip( x + 1, y - 1, 1, -1, piece, opponent))
                return true;
// Check up-left
            if (checkFlip( x - 1, y + 1, -1, 1, piece, opponent))
                return true;
// Check up-right
            if (checkFlip( x + 1, y + 1, 1, 1, piece, opponent))
                return true;
        }
        return false; // If we get here, we didn't find a valid flip direction
    }

    void calculteValid(int moveX[], int moveY[], char piece)
    {
        int numMoves=0;
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
            {
                if (validMove( x, y, piece)) // remember coordinates
                {
                    moveX[numMoves] = x;
                    moveY[numMoves] = y;
                    numMoves++; // Increment number of moves found

                }
            }

    }

    int[] checkMinMax()
    {
        Board tempboard=new Board();
        //tempboard.board=board;
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
            {
                tempboard.board[x][y]=board[x][y];
            }
        //copyBoard(board,tempboard.board);
        //tempboard.displayBoard();
        int[] moveX=new int[60];
        int[] moveY=new int[60];
        tempboard.calculteValid(moveX,moveY,'X');

        for(int i=0;i<moveX.length;i++)
        {
            //  System.out.println(moveX[i]+" "+moveY[i]);
        }

//tempboard.displayBoard();
        int temp=0;
        int min=1000;
        int max=-1111;
        int[] help = new int[100];
        int i=0;
        int index=0;

        for(;moveX[i]!=0&&moveY[i]!=0;i++)
        {
            tempboard.makeMove(moveX[i], moveY[i], 'X');
            Board tempboard1=new Board();
            //tempboard1.board=tempboard.board;
            for (int x = 0; x < 8; x++)
                for (int y = 0; y < 8; y++)
                {
                    tempboard1.board[x][y]=tempboard.board[x][y];
                }
            int[] moveX1=new int[60];
            int[] moveY2=new int[60];
            tempboard1.calculteValid(moveX1,moveY2,'O');

            for(int j=0;moveX1[j]!=0&&moveY2[j]!=0;j++)
            {
                tempboard1.makeMove(moveX1[i], moveY2[i], 'O');
                temp=tempboard1.heuristic('x');
                if(temp<min){
                    min=temp;
                    //index=j;
                }
                //tempboard1.board=tempboard.board;
                for (int x = 0; x < 8; x++)
                    for (int y = 0; y < 8; y++)
                    {
                        tempboard1.board[x][y]=tempboard.board[x][y];
                    }

            }

            if(min>max)
            {
                index=i;
                max=min;
            }
            //tempboard.board=board;
            for (int x = 0; x < 8; x++)
                for (int y = 0; y < 8; y++)
                {
                    tempboard.board[x][y]=board[x][y];
                }
        }
        int[] tempq=new int[2];

        tempq[0]=moveX[index];
        tempq[1]=moveY[index];
        System.out.println("X  "+tempq[0]);
        System.out.println("Y  "+tempq[1]);




//makeMove(tempq[0], tempq[1], 'X');

//displayBoard();
        return tempq;
//return help;
    }






    int getMoveList(int moveX[], int moveY[], int numMoves, char piece)
    {
        numMoves = 0; // Initially no moves found
// Check each square of the board and if we can move there, remember the coords
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
            {
                if (validMove( x, y, piece)) // remember coordinates
                {
                    moveX[numMoves] = x;
                    moveY[numMoves] = y;
                    numMoves++; // Increment number of moves found
                }
            }
        return numMoves;
    }



    boolean gameOver()
    {
        int[] XMoveX = new int[60];
        int[] XMoveY=new int[60];
        int[] OMoveX=new int[60];
        int[] OMoveY=new int[60];
        int numXMoves = 0, numOMoves = 0;
        int x=getMoveList( XMoveX, XMoveY, numXMoves, 'X');
        int y=getMoveList( OMoveX, OMoveY, numOMoves, 'O');
        if ((x == 0) && (y == 0))
            return true;
        return false;
    }


    int score( char piece)
    {
        int total = 0;
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
            {
                if (board[x][y] == piece)
                    total++;
            }
        return total;
    }


    void getRandomMove( int x, int y, char piece)
    {
        int[] moveX=new int[60];
        int[] moveY=new int[60];
        int numMoves = 0;

        int t=getMoveList( moveX, moveY, numMoves, piece);
        if (t == 0)
        {
            x = -1;
            y = -1;
        }
        else
        {
            Random rand = new Random();
            int i = rand.nextInt(t);

            x = moveX[i];
            y = moveY[i];
        }
    }



    int[] checkMinMaxdifi()
    {
        Board tempboard=new Board();
        //tempboard.board=board;
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
            {
                tempboard.board[x][y]=board[x][y];
            }
        //copyBoard(board,tempboard.board);
        //tempboard.displayBoard();
        int[] moveX=new int[60];
        int[] moveY=new int[60];
        tempboard.calculteValid(moveX,moveY,'X');

        for(int i=0;i<moveX.length;i++)
        {
            //  System.out.println(moveX[i]+" "+moveY[i]);
        }

//tempboard.displayBoard();
        int temp=0;
        int min=1000;
        int max=-1111;
        int[] help = new int[100];
        int i=0;
        int index=0;







        for(;moveX[i]!=0&&moveY[i]!=0;i++)
        {
            tempboard.makeMove(moveX[i], moveY[i], 'X');
            //Board tempboard1=new Board();
            temp=tempboard.heuristic('x');
            if(temp>max){
                // min=temp;
                //index=j;
                index=i;
                max=temp;
            }
            //tempboard1.board=tempboard.board;
            for (int x = 0; x < 8; x++)
                for (int y = 0; y < 8; y++)
                {
                    tempboard.board[x][y]=board[x][y];
                }

        }
        int[] tempq=new int[2];

        tempq[0]=moveX[index];
        tempq[1]=moveY[index];
        System.out.println("X "+tempq[0]);
        System.out.println("Y "+tempq[1]);




//makeMove(tempq[0], tempq[1], 'X');

//displayBoard();
        return tempq;
//return help;
    }




    int heuristic(char whoseTurn)
    {
        char opponent = 'O';
        if (whoseTurn == 'O')
            opponent = 'X';
        int ourScore = score(whoseTurn);
        int opponentScore = score(opponent);
        return (ourScore - opponentScore);
    }

    void copyBoard(char[][] src, char[][] dest)
    {

        src=dest;
    }
 /*



*/



}
