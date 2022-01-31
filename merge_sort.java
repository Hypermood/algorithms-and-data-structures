package weblab;

class Solution {

    public static LibraryQueue<Lawyer> sortingSomeLawyer(LibraryQueue<Lawyer> queue) {

        LibraryQueue<Lawyer> workQ = new LibraryQueue();

        for(int i = 0;i<queue.size();i++){
            Lawyer t = queue.dequeue();
            workQ.enqueue(t);
            queue.enqueue(t);
        }

        if(workQ.size() == 0){
            return workQ;
        }

        if(workQ.size() == 1){
            return workQ;
        }

        if(workQ.size() == 2){

            Lawyer l1 = workQ.dequeue();
            Lawyer l2 = workQ.dequeue();
            LibraryQueue<Lawyer> retQ = new LibraryQueue();

            if(l1.getHourlyWage()>l2.getHourlyWage()){
                retQ.enqueue(l1);
                retQ.enqueue(l2);
            }
            else{
                retQ.enqueue(l2);
                retQ.enqueue(l1);
            }

            return retQ;
        }


        LibraryQueue<Lawyer> firstHalf = new LibraryQueue();
        LibraryQueue<Lawyer> secondHalf = new LibraryQueue();
        LibraryQueue<Lawyer> finale = new LibraryQueue();


        for(int i=0;i<workQ.size()/2;i++){
            firstHalf.enqueue(workQ.dequeue());
        }

        while(!workQ.isEmpty()){
            secondHalf.enqueue(workQ.dequeue());
        }

        firstHalf = sortingSomeLawyer(firstHalf);
        secondHalf = sortingSomeLawyer(secondHalf);



        while(!firstHalf.isEmpty() && !secondHalf.isEmpty()){

            if(firstHalf.front().getHourlyWage() > secondHalf.front().getHourlyWage()){
                finale.enqueue(firstHalf.dequeue());
            }
            else{
                finale.enqueue(secondHalf.dequeue());
            }

        }

        while(!firstHalf.isEmpty()){
            finale.enqueue(firstHalf.dequeue());
        }

        while(!secondHalf.isEmpty()){
            finale.enqueue(secondHalf.dequeue());
        }
        return finale;






    }
}

class Lawyer {

    private int badgeNumber;

    private int numberOfTrials;

    private double hourlyWage;

    private double totalIncome;

    private int numberOfObjections;

    public Lawyer(
            int badgeNumber,
            int numberOfTrials,
            double hourlyWage,
            double totalIncome,
            int numberOfObjections) {
        this.badgeNumber = badgeNumber;
        this.numberOfTrials = numberOfTrials;
        this.hourlyWage = hourlyWage;
        this.totalIncome = totalIncome;
        this.numberOfObjections = numberOfObjections;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public int getNumberOfTrials() {
        return numberOfTrials;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public int getNumberOfObjections() {
        return numberOfObjections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lawyer)) return false;
        Lawyer className = (Lawyer) o;
        return badgeNumber == className.badgeNumber
                && numberOfTrials == className.numberOfTrials
                && Double.compare(className.hourlyWage, hourlyWage) == 0
                && Double.compare(className.totalIncome, totalIncome) == 0
                && numberOfObjections == className.numberOfObjections;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                badgeNumber, numberOfTrials, hourlyWage, totalIncome, numberOfObjections);
    }

    @Override
    public String toString() {
        return "Lawyer{"
                + "badgeNumber="
                + badgeNumber
                + ", numberOfTrials="
                + numberOfTrials
                + ", hourlyWage="
                + hourlyWage
                + ", totalIncome="
                + totalIncome
                + ", numberOfObjections="
                + numberOfObjections
                + '}';
    }
}

/**
 * Implements the Queue ADT from the book.
 */
class LibraryQueue<T> {

    private LinkedList<T> q;

    public LibraryQueue() {
        this.q = new LinkedList<>();
    }

    public void enqueue(T e) {
        q.add(e);
    }

    public T dequeue() {
        return q.poll();
    }

    public int size() {
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public T front() {
        return q.peek();
    }
}
