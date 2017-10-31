import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new App();
                try {
                    ITTBuilder.createFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
