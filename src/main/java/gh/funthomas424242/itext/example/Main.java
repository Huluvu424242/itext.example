package gh.funthomas424242.itext.example;

public class Main implements Runnable {

    public static void main(final String args[]) {
        final Main main = new Main();
        main.run();
    }

    @Override
    public void run() {
        new Titelblatt().run();
        new NumeriertesBlatt().run();
    }

}
