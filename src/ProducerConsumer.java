class SharedResource {
   private int item;
   private boolean available = false;

    // TODO: synchronize void put(int item)
    // while(available) -> wait()
    // set this.item = item, available = true
    // print "Produced: " + item
    // notify()

    // TODO: synchronize void get()
    // while(!available) -> wait()
    // print "Consumed: " + item
    // available = false
    // notify()

	public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            wait();
        }
        item = value;
        System.out.println("Produced: " + item);
        available = true;
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait();
        }
        System.out.println("Consumed: " + item);
        available = false;
        notify();
    }
}

class Producer extends Thread {
    SharedResource resource;
    // TODO: Constructor to init resource
    
    // TODO: run()
    // Loop 1 to 5
    // call resource.put(i)

	Producer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                resource.produce(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    private SharedResource resource;
    // TODO: Constructor to init resource
    
    // TODO: run()
    // Loop 1 to 5
    // call resource.get()

	Consumer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                resource.consume();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        // TODO: Create SharedResource object
        // TODO: Create Producer and Consumer threads
        // TODO: Start both threads

	 SharedResource resource = new SharedResource();

        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        producer.start();
        consumer.start();
    
    }
}
