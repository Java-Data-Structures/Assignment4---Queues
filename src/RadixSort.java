import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RadixSort {

    public static void main(String[] args) {

        boolean isProgramRunning = true;
        do{
            System.out.print("Please enter the number of input values: ");
            int[] inputArr = new int[getUserInt()];
            for(int i=0;i< inputArr.length;i++){
                System.out.print("Enter values for the array index "+i+": ");
                inputArr[i] = getUserInt();
            }

            System.out.println();
            System.out.println("Inputs array before sorting: \t"+printArrayToString(inputArr));
            System.out.println("Inputs array after sorting: \t"+printArrayToString(radixSort(inputArr)));

            System.out.print("Do you want to re-run code with different input string (Y/N)? ");
            Scanner sc = new Scanner(System.in);
            if(!sc.next().equalsIgnoreCase("Y")){
                isProgramRunning = false;
            }
            System.out.println();
        }while(isProgramRunning);


    }

    /**
     * This method prompts the user to enter a value for the Data node which will then be returned to the caller.
     */
    public static int getUserInt(){
        boolean testPassed = false;
        int userInputInteger = 0;
        while(!testPassed){
            Scanner sc = new Scanner(System.in);
            try{
                userInputInteger = Integer.parseInt(sc.nextLine()); //Try to parse the user Input into an Integer, and break out of loop if successful.
                testPassed = true;
            }catch (NumberFormatException e){
                System.out.println("Please enter an integer.");
            }
        }
        return userInputInteger;
    }

    public static String printArrayToString(int[] array){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i< array.length;i++){
            stringBuilder.append(array[i]);
            stringBuilder.append(", ");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length()-2).toString();
    }

    /*
    -----------------------RADIX SORT IMPLEMENTATION BEGINS HERE --------------------------------------------------------
     */

    //Time complexity of O(K*N);
    //k is log10(highest) + 1.
    private static List<Queue<Integer>> queueList = new ArrayList<>(10);

    static{
        queueList.add(0,new Queue<>());
        queueList.add(1,new Queue<>());
        queueList.add(2,new Queue<>());
        queueList.add(3,new Queue<>());
        queueList.add(4,new Queue<>());
        queueList.add(5,new Queue<>());
        queueList.add(6,new Queue<>());
        queueList.add(7,new Queue<>());
        queueList.add(8,new Queue<>());
        queueList.add(9,new Queue<>());
    }

    //preforms a radix sort and returns an int array (since radixSort can only be preformed on ).
    public synchronized static int[] radixSort(int[] inputArr){
        int highestNum = 0;

        //special case the first run to find the highest.
        for(int i=0; i< inputArr.length;i++){
            if(inputArr[i] > highestNum) highestNum = inputArr[i]; //find the highest num (K) so we know when to stop.
            queueList.get(ExtractDigit(1,inputArr[i])).enqueue(inputArr[i]); //get whatever number was in that digit, and put it in a queue of that number.
        }

        int K = (int)Math.log10(highestNum) + 1;
        inputArr = rebuildArray(inputArr.length);//override the input array to save space complexity.

        //after we find the number of times we need to iterate, do that amount.
        // i = 2 since we already did the first iteration.
        //K is the number of digits in the highest number.
        for(int i= 2;i<=K;i++){ //this does not actually increase the Big O of the algorithm, since it is a constant based on the greatest number and not the size of list.
            for(int j=0;j< inputArr.length;j++){
                queueList.get(ExtractDigit(i,inputArr[j])).enqueue(inputArr[j]);
            }
            inputArr = rebuildArray(inputArr.length); //update the array in the radix sort.
        }
        return inputArr;
    }

    private static int[] rebuildArray(int lengthOfArray){
        int[] array = new int[lengthOfArray];
        int currArrPos = 0; //pointer for the current Array position.
        int currQueue = 0; //counter for which queue we're currently in.

        while(currArrPos<lengthOfArray){
            if(queueList.get(currQueue).isEmpty()){
                currQueue++;
            }else{
                array[currArrPos] = queueList.get(currQueue).dequeue();
                currArrPos++;
            }
        }

        return array;
    }

    //Gets the number at any given position in a number. (1 is rightmost digit.)
    private static int ExtractDigit(int pos, int num){
        return (int)((num % Math.pow(10,pos)) / (Math.pow(10,(pos-1))));
    }


}
