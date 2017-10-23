import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class App extends JPanel implements ActionListener {

    // Elements declared here
    public String storeNumber = "****";

    private JPanel mainPanel;
    public JLabel Title;
    private JLabel changeStoreBtn;
    private JButton termMWS;


    public App() {

        // Initialize and setup the elements

        add(Title);
        add(changeStoreBtn);
        add(termMWS);

        System.out.println(Title);


        // TODO Change this to a button, and set the icon rather than having an image.
        // This will prevent having to create graphics now, and speed up 2.0 deployment
        changeStoreBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                storeNumber = ButtonFunctions.setStoreNumber(storeNumber);
                Title.setText("");
                Title.setText("FCE-UTIL              Store Number: " + storeNumber);
            }
        });

        // TODO: Test individual actionListeners versus a single actionListener with multiple selector
        termMWS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonFunctions.terminateMWS(storeNumber);
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
