import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JPanel implements ActionListener {

    public static String storeNumber = "****";

    private JPanel panel1;
    private JLabel Title;
    private JButton setStore;


    // Elements declared here

    public App() {

        // Initialize and setup the elements

        add(Title);
        add(setStore);

        System.out.println(Title);
        setStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNumber = JOptionPane.showInputDialog("Enter the store number.");
                Title.setText("");
                Title.setText("FCE-UTIL              Store Number: " + storeNumber);
            }
        });
    }

    protected static void build() {

        // Build of the JFrame, icons, and the JPane

        JFrame frame = new JFrame("FCE-UTIL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Custom Icon code
        try {
            frame.setIconImage(ImageIO.read(new File("res/cpp_logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        } */

        App pane = new App();
        pane.setOpaque(true);
        pane.setBackground(Color.decode("0x3B3F42"));
        frame.setContentPane(pane);

        frame.pack();
        frame.setSize(600, 400);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
