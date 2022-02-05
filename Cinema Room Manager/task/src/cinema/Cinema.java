package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();
        char[][] seatsArray = new char[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                seatsArray[i][j]='S';
            }
        }

        boolean exit = false;
        while(!exit) {
            System.out.println("\n1. Show the seats" +
                    "\n2. Buy a ticket" +
                    "\n0. Exit");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    showCinemaRoomWithRowsCols(seatsArray);
                    break;
                case 2:
                    System.out.println("Ticket price: $"+getTicketPrice(seatsArray,scanner));
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }

//        System.out.println();
////        System.out.println("Total income:");
////        System.out.println("$"+ calculateIncome(rows,cols));
//
//        showCinemaRoomWithRowsCols(rows,cols,chosenRow,chosenCol);
    }
    private static int getTicketPrice(char[][] seatsArray ,Scanner scanner) {
        int chosenTicketPrice = 0;
        System.out.println("Enter a row number:");
        int chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int chosenCol = scanner.nextInt();

        seatsArray[chosenRow-1][chosenCol-1] = 'B';
        int totalSeats = seatsArray.length * seatsArray[0].length;
        final int regularPrice =10;
        final int largeRoomPrice =8;
        if(totalSeats <=60){
            return regularPrice;
        }else {
            if(chosenRow <= seatsArray.length / 2){
                return regularPrice;
            } else {
                return largeRoomPrice;
            }
        }

    }
    private static int  calculateIncome(int rows, int cols) {
         int totalSeats = rows * cols;
        final int regularPrice =10;
        final int largeRoomPrice =8;
        int income = 0 ;
        if(totalSeats <= 60){
            income = totalSeats * regularPrice;
        } else if (totalSeats > 60) {
            int regularPRiceRows = rows / 2;
            int regularPriceSeats = regularPRiceRows * cols;
            int discountSeats = (rows - regularPRiceRows) *cols;
            income = (regularPriceSeats * regularPrice) + (discountSeats * largeRoomPrice);
        }
        return income;
    }
    private static void showCinemaRoomWithRowsCols(char[][] seatsArray) {
        System.out.println("Cinema:");
        for (int i = -1; i < seatsArray.length; i++) {
            for (int j = -1; j <seatsArray[0].length; j++) {
                if (i == -1&& j == -1) {
                    System.out.print("  ");
                } else if (i == -1) {
                    System.out.print(j+1+" ");
                } else if (j == -1) {
                    System.out.println();
                    System.out.print(i+1);
                } else {
                    System.out.print(" "+seatsArray[i][j]);
//                    if(arg.length==2 && i==arg[0] && j==arg[1]){
//                       // System.out.print(" B");
//                        seatsArray[i][j]= 'B';
//                    }else
//                    //System.out.print(" S");
//                    seatsArray[i][j]='S';
                }
            }
        }
    }

}