public class AntThread extends Thread {
    private String name;
    private int x;
    private int y;
    private int velocity;

    public AntThread(String name, int x, int y, int velocity) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " починає свій рух.");
            move(x, y, velocity);
            System.out.println(name + " досягає кінцевої точки та повертається.");
            move(0, 0, velocity);
            System.out.println(name + " повернувся до початкової точки.");
        } catch (InterruptedException e) {
            System.out.println(name + " був перерваний.");
        }
    }

    private void move(int targetX, int targetY, int velocity) throws InterruptedException {
        int distanceX = targetX - x;
        int distanceY = targetY - y;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        int steps = (int) (distance / velocity);
        double stepX = distanceX / (double) steps;
        double stepY = distanceY / (double) steps;

        for (int i = 0; i < steps; i++) {
            Thread.sleep(1000);

            x += stepX;
            y += stepY;

            System.out.println(name + " рухається до (" + x + ", " + y + ")");
        }
    }
    public static void main(String[] args) {
        AntThread ant1 = new AntThread("Мураха 1", 0, 0, 2);
        AntThread ant2 = new AntThread("Мураха 2", 0, 0, 3);

        ant1.start();
        ant2.start();
    }
}

