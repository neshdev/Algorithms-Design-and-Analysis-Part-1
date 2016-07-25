package neshdev.collections;

public class Main {

    public static void main(String[] args) {

        System.out.println("Exiting...");
    }

    public static void queueTest(){
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        System.out.println("IsEmpty: " + q.isEmpty() );
        System.out.println("length: " + q.size() );
        System.out.println("dequeue 1: " + q.dequeue() );
        System.out.println("length: " + q.size() );

        System.out.println("iterate 1");
        for (Integer i: q) {
            System.out.println(i);
        }

        System.out.println("dequeue 2: " + q.dequeue() );
        System.out.println("dequeue 3: " + q.dequeue() );
        System.out.println("dequeue 4: " + q.dequeue() );
        System.out.println("length: " + q.size() );
        System.out.println("IsEmpty: " + q.isEmpty() );

        System.out.println("iterate 2");
        for (Integer i: q) {
            System.out.println(i);
        }

        System.out.println("Exiting...");
    }

    public  static void stackTest(){
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        System.out.println("IsEmpty: " + s.isEmpty() );
        System.out.println("length: " + s.size() );
        System.out.println("pop 4: " + s.pop() );
        System.out.println("length: " + s.size() );

        System.out.println("iterate 1");
        for (Integer i: s) {
            System.out.println(i);
        }

        System.out.println("pop 3: " + s.pop() );
        System.out.println("pop 2: " + s.pop() );
        System.out.println("pop 1: " + s.pop() );
        System.out.println("length: " + s.size() );
        System.out.println("IsEmpty: " + s.isEmpty() );

        System.out.println("iterate 2");
        for (Integer i: s) {
            System.out.println(i);
        }

        System.out.println("Exiting...");
    }
}
