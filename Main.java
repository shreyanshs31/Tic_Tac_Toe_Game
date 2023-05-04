/* Created By: Shreyansh Shukla
 * Date: 13-04-2023
 * Project Name: Tic Tak Toe Game
 */

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //game initial greetings
        System.out.println("Let's play tic tak toe"); 

        Scanner in = new Scanner(System.in);

        //Storing players names
        System.out.print("Player 1: Enter your name: ");
        String player1 = in.nextLine();
        System.out.print("Player 2: Enter your name: ");
        String player2 = in.nextLine();

        //making a 3x3 board
        char[][] board = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }

        /*making a boolean player1 if it is true then its 
        player1 turn if its false its player2 turn*/
        boolean p1 = true;

        boolean game_ends = false;
        while(!game_ends) {

            //display board
            display_board(board);
            
            //checks whether it is player1 turn or player2 turn and prints geetings for them in screen
            if(p1) {
                System.out.println(player1 + "'s turn (x):");
            } else {
                System.out.println(player2 + "'s turn (o):");
            }


            //creating a variable char for storing what operator is with whome player
            char c = '-';
            if(p1) {
                c = 'x';
            }else {
                c = 'o';
            }
            

            //************ask for the user for row and col and check whether if it is valid*******************


            Scanner position = new Scanner(System.in);
            int row = 0;
            int col = 0;
            
            //keeps asking the player to enter a valid row and col
            while(true) {

                //store the entered row and col by the player
                System.out.println("Enter the row: ");
                row = position.nextInt();
                System.out.println("Enter the col: ");
                col = position.nextInt();

                //check the whether the entered row and col are in the board
                if(row < 3 && row > -1 && col < 3 && col > -1) {
                    //checks whether the entered position is already used or not
                    if(board[row][col] == '-') {
                        break;
                    }
                    //if the postion is already used ask player to enter different position
                    else {
                        System.out.println("This position is already used before enter other position");
                    }
                }
                //asks player to enter a valid row and col
                else {
                    System.out.println("Enter a valid row and col");
                }
            }
            
            //enter the symbol in the valid postion
            board[row][col] = c;

            //*********************check if the game has ended**************************************
            char win_or_not = win(board);
            if(win_or_not == 'x') {
                System.out.println(player1 + " has won the game");
                game_ends = true;
                //after the game ends display the board
                display_board(board);
                break;
            }
            if(win_or_not == 'o') {
                System.out.println(player2 + " has won the game");
                game_ends = true;
                //after the game ends display the board
                display_board(board);
                break;
            }
            
            //********************checks if it is a draw or not**************************************
            boolean draw_or_not = board_full_or_not(board);
            
            /*if draw_or_not is true then board is full and its a draw, 
            therefore game has ended in a draw*/
            if(draw_or_not == true) {
                game_ends = true;
                //after the game ends display the board
                System.out.println("Its a draw");
                display_board(board);
                break;
            }

            //**************************alternate the chance**********************************************
            
            //if its neither a win or a draw then we have to change the chance to player2
            p1 = !p1;
        }
        in.close();
        position.close();
    }


    //*****************This function is used for displaying the board after every turn*************************
    static void display_board(char[][] board) {

        System.out.println("Board:-");

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    //*******************This function checks whether either player have won or not**************************
    static char win(char[][] board) {

        //checking for rows and columns
        for(int i = 0; i < 3; i++) {
            if(board[i][0] != '-' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return board[i][0];
            }
            if(board[0][i] != '-' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return board[0][i];
            }
        }

        // checking for diagonals
        if(board[0][0] != '-' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0];
        }
        if(board[0][2] != '-' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return board[0][2];
        }

        //if every condition is false then return a space
        return ' ';
    }

    //******************This function checks whether board is full or not***************************
    static boolean board_full_or_not(char[][] board) {
        //check if the board is full or not by iterating through each element
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == '-') {
                    //if it is not full then return false
                    return false;
                }
            }
        }
        //if it is full then return true
        return true;
    }
}