import java.util.Scanner;

public class TestQueue {
    public static void main(String[] args) {
        boolean isTestProgramRunning = true;
        Queue<Integer> testQueue = new Queue<>(); //For this test program, we are going to use the type integer for testing.

        do{
            printMenu();
            switch (getValidateUserMenuInput()){
                case 1://Enque
                    System.out.println("Testing method enqueue().");
                    Integer data = getUserData();
                    System.out.println("Enqueuing data into the queue.");

                    System.out.println("Queue content before adding "+data+" is: ");
                    testQueue.printList();

                    testQueue.enqueue(data);

                    System.out.println("\nQueue content after adding "+data+" is: ");
                    testQueue.printList();
                    break;
                case 2://Dequeue
                    System.out.println("Testing method dequeue().");
                    System.out.println("Dequeuing the first element in the queue");

                    System.out.println("Queue content before dequeuing is: ");
                    testQueue.printList();

                    testQueue.dequeue();

                    System.out.println("\nQueue content before dequeuing is: ");
                    testQueue.printList();
                    break;
                case 3://front
                    System.out.println("Testing method front()");
                    System.out.println("Front element is: "+testQueue.front());//All access methods throw EmptyStackException if called on an empty stack.
                    break;
                case 4://size
                    System.out.println("Testing method size()");
                    System.out.println("Size is: "+testQueue.size());
                    break;
                case 5://isEmpty
                    System.out.println("Testing method isEmpty()");
                    System.out.println(testQueue.isEmpty());
                    break;
                case 6: //printList
                    System.out.println("Testing method printList()");
                    testQueue.printList();
                    break;
                case 7:
                    System.out.println("Terminating program, thank you");
                    isTestProgramRunning = false;
                    break;

            }
        }while (isTestProgramRunning);
    }

    /**
     * This method prints the menu for the user.
     */
    public static void printMenu(){
        System.out.println();
        System.out.println("---------MAIN MENU--------");
        System.out.println("1 – Enqueue element");
        System.out.println("2 – Dequeue element");
        System.out.println("3 – Front element");
        System.out.println("4 – Size of queue");
        System.out.println("5 – Is Empty queue?");
        System.out.println("6 - Print queue content");
        System.out.println("7 - Exit program\n");
    }

    /**
     * This method gets and validates user input. This method should be called every time the menu is called to get an input
     * from the user regrading their choice on the menu.
     */
    public static int getValidateUserMenuInput(){
        boolean testPassed = false;
        int userInputInteger = 0;
        while(!testPassed){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter option number: ");
            try{
                userInputInteger = Integer.parseInt(sc.nextLine());
                if(userInputInteger>0 && userInputInteger <= 7){ //if the number is actually a valid menu option, return that number.
                    testPassed = true;
                }else{
                    System.out.println("Please enter a number between 1-7.");
                }
            }catch (NumberFormatException e){
                System.out.println("Please enter an Integer.");
            }
        }
        return userInputInteger;
    }

    /**
     * This method prompts the user to enter a value for the Data node which will then be returned to the caller.
     */
    public static int getUserData(){
        boolean testPassed = false;
        int userInputInteger = 0;
        while(!testPassed){
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter a value for the Data node: ");
            try{
                userInputInteger = Integer.parseInt(sc.nextLine()); //Try to parse the user Input into an Integer, and break out of loop if successful.
                testPassed = true;
            }catch (NumberFormatException e){
                System.out.println("Please enter an integer.");
            }
        }
        return userInputInteger;
    }
}
