package cinema;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static int crrentIncome =0;
    static int numOfPurchasedTickets=0;
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
                    "\n3. Statistics" +
                    "\n0. Exit");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    showCinemaRoomWithRowsCols(seatsArray);
                    break;
                case 2:
                    int ticket = getTicketPrice(seatsArray,scanner);
                    System.out.println(ticket>0?"Ticket price: $"+ticket: "That ticket has already been purchased!");
                    break;
                case 3:
                    getStatistics(rows, cols);
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

    private static void getStatistics(int rows, int cols) {
        System.out.println("Number of purchased tickets: "+ numOfPurchasedTickets);
//                    System.out.println("Percentage: "+ new DecimalFormat("0.00").
//                            format((  numOfPurchasedTickets)/ (double rows*cols) *100);
        System.out.println("Percentage: "+
                new DecimalFormat("0.00").
                 format(((double) numOfPurchasedTickets /(double) (rows * cols) )* 100)+"%");
        System.out.println("Current income: $"+ crrentIncome);
        System.out.println("Total income: $"+calculateIncome(rows, cols));
    }

    private static int getTicketPrice(char[][] seatsArray ,Scanner scanner) {
        int chosenTicketPrice = 0;
        boolean correctSeatNumberEntered = false;
        int chosenRow=0;
        int chosenCol=0;
        while(!correctSeatNumberEntered) {
            System.out.println("Enter a row number:");
             chosenRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            chosenCol = scanner.nextInt();
            if(chosenRow >seatsArray.length || chosenCol >seatsArray[0].length){
                System.out.println("Wrong input!");
            }else
            if (seatsArray[chosenRow - 1][chosenCol - 1] == 'S') {
                seatsArray[chosenRow - 1][chosenCol - 1] = 'B';
                correctSeatNumberEntered =true;
            } else {
                System.out.println("That ticket has already been purchased!");

            }
        }
        numOfPurchasedTickets += 1;

        int totalSeats = seatsArray.length * seatsArray[0].length;
        final int regularPrice =10;
        final int largeRoomPrice =8;
        if(totalSeats <=60){
            chosenTicketPrice = regularPrice;
        }else {
            if(chosenRow <= seatsArray.length / 2){
                chosenTicketPrice=regularPrice;
            } else {
                chosenTicketPrice=largeRoomPrice;
            }
        }
        crrentIncome += chosenTicketPrice;
        return chosenTicketPrice;

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