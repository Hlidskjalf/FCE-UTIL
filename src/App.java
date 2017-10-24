import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JPanel implements ActionListener {

    // Elements declared here
    public String storeNumber = "****";

    private JPanel mainPanel;
    public JLabel Title;
    private JButton changeStoreBtn;
    private JButton termMWS;
    private JButton ping;


    public App() {

        // Initialize and setup the elements

        add(Title);
        add(changeStoreBtn);
        add(termMWS);
        add(ping);

        System.out.println(Title);

        // TODO: Test individual actionListeners versus a single actionListener with multiple selector

        changeStoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNumber = ButtonFunctions.setStoreNumber(storeNumber);
                Title.setText("");
                Title.setText("FCE-UTIL              Store Number: " + storeNumber);
            }
        });

        termMWS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonFunctions.terminateMWS(storeNumber);
            }
        });

        ping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonFunctions.constantPing(storeNumber);
            }
        });

    }

    protected static void build() {

        // Build of the JFrame, icons, and the JPane

        JFrame frame = new JFrame("FCE-UTIL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: Make an icon series for the application. Identify preferred sizes and add to res/
        /* Custom Icon code
        try {
            frame.setIconImage(ImageIO.read(new File("res/cpp_logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        } */

        App pane = new App();
        pane.setOpaque(true);
        pane.setBackground(Color.decode("0x3B3F42"));
        pane.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setContentPane(pane);

        frame.pack();
        frame.setSize(550, 400);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
