import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Passenger {
    int id;
    String name;
    int age;
    int seatNo;

    Passenger(int id, String name, int age, int seatNo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.seatNo = seatNo;
    }
}

public class TicketReservationSystem {

    static LinkedList<Passenger> reservationList = new LinkedList<>();
    static Queue<Passenger> waitingQueue = new LinkedList<>();

    static int maxSeats = 5;
    static int seatCounter = 1;

    static Scanner sc = new Scanner(System.in);

    // Book Ticket
    static void bookTicket() {
        System.out.print("Enter Passenger ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        if (reservationList.size() < maxSeats) {
            Passenger p = new Passenger(id, name, age, seatCounter++);
            reservationList.add(p);
            System.out.println("Ticket Booked! Seat Number: " + p.seatNo);
        } else {
            Passenger p = new Passenger(id, name, age, -1);
            waitingQueue.add(p);
            System.out.println("Seats full! Added to Waiting List.");
        }
    }

    // Cancel Ticket
    static void cancelTicket() {
        System.out.print("Enter Passenger ID to cancel: ");
        int id = sc.nextInt();

        Passenger found = null;

        for (Passenger p : reservationList) {
            if (p.id == id) {
                found = p;
                break;
            }
        }

        if (found != null) {
            reservationList.remove(found);
            System.out.println("Ticket Cancelled.");

            if (!waitingQueue.isEmpty()) {
                Passenger next = waitingQueue.poll();
                next.seatNo = seatCounter++;
                reservationList.add(next);
                System.out.println(next.name + " moved from Waiting List to Seat " + next.seatNo);
            }
        } else {
            System.out.println("Passenger not found.");
        }
    }

    // Display Reservation List
    static void displayPassengers() {
        if (reservationList.isEmpty()) {
            System.out.println("No reservations.");
            return;
        }

        for (Passenger p : reservationList) {
            System.out.println("ID: " + p.id +
                    " Name: " + p.name +
                    " Age: " + p.age +
                    " Seat: " + p.seatNo);
        }
    }

    // Display Waiting List
    static void displayWaitingList() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Waiting list empty.");
            return;
        }

        System.out.println("Waiting List:");
        for (Passenger p : waitingQueue) {
            System.out.println(p.name);
        }
    }

    // Search Passenger (Linear Search)
    static void searchPassenger() {
        System.out.print("Enter Passenger ID to search: ");
        int id = sc.nextInt();

        for (Passenger p : reservationList) {
            if (p.id == id) {
                System.out.println("Passenger Found:");
                System.out.println("Name: " + p.name + " Seat: " + p.seatNo);
                return;
            }
        }

        System.out.println("Passenger not found.");
    }

    // Sort Passengers by Name (Bubble Sort)
    static void sortPassengers() {
        for (int i = 0; i < reservationList.size() - 1; i++) {
            for (int j = 0; j < reservationList.size() - i - 1; j++) {

                Passenger p1 = reservationList.get(j);
                Passenger p2 = reservationList.get(j + 1);

                if (p1.name.compareTo(p2.name) > 0) {
                    reservationList.set(j, p2);
                    reservationList.set(j + 1, p1);
                }
            }
        }

        System.out.println("Passengers Sorted by Name.");
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n--- Online Ticket Reservation System ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Display Passengers");
            System.out.println("4. Display Waiting List");
            System.out.println("5. Search Passenger");
            System.out.println("6. Sort Passengers");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    bookTicket();
                    break;

                case 2:
                    cancelTicket();
                    break;

                case 3:
                    displayPassengers();
                    break;

                case 4:
                    displayWaitingList();
                    break;

                case 5:
                    searchPassenger();
                    break;

                case 6:
                    sortPassengers();
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}