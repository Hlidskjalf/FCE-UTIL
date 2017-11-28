import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The primary class for building the GUI and setting the functionality of the
 * buttons. All buttons use actionPerformed functions that call to custom
 */

public class App extends JPanel implements ActionListener {

    // Elements declared here
    public String storeNumber = "****";

    public JPanel mainPanel;
    public JLabel Title;
    public JButton changeStoreBtn;
    private JButton termMWS;
    private JButton ping;
    private JButton reboot;
    private JButton termCust;
    private JButton cleanUp;
    private JButton vnc;
    private JButton Exit;
    private JButton blank;
    private JButton price_update;


    public App() {

        /**
         * Set up the panel, and add GridBagConstraints. Using the GridBagConstraints we
         * are able to force each button to occupy a single line and behave dynamically
         * when the window is resized.
         */


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        /**
         * GridBagConstraints parameters. Sets the weight and Insets for each button.
         * This overrides the rigid area set to each button below, and forces the buttons to
         * behave more dynamically.
         */

        c.weightx = 5;
        c.weighty = .5;
        c.insets = new Insets(5, 100, 10, 100);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        /**
         * Set the button width to force each button to be on a single line. This only sets the
         * preferred size, which is overriden by the GridBagConstraints. Without the constraints set
         * this will set the buttons to max width (up to 500px) and achieve the same basic result,
         * though will look a lot worse.
         */

        changeStoreBtn.add(Box.createRigidArea(new Dimension(500, 25)));
        termMWS.add(Box.createRigidArea(new Dimension(500, 25)));
        ping.add(Box.createRigidArea(new Dimension(500, 25)));
        reboot.add(Box.createRigidArea(new Dimension(500, 25)));
        termCust.add(Box.createRigidArea(new Dimension(500, 25)));
        cleanUp.add(Box.createRigidArea(new Dimension(500, 25)));
        vnc.add(Box.createRigidArea(new Dimension(500, 25)));
        price_update.add(Box.createRigidArea(new Dimension(500, 25)));
        Exit.add(Box.createRigidArea(new Dimension(500, 25)));


        /**
         * Add the buttons and the constraint parameters to the panel. Each button is added to the panel
         * with the button object itself, and the constraints (c), to force the appropriate layout.
         */

        panel.add(Title);
        panel.add(blank, c);
        panel.add(changeStoreBtn, c);
        panel.add(termMWS, c);
        panel.add(ping, c);
        panel.add(reboot, c);
        panel.add(termCust, c);
        panel.add(cleanUp, c);
        panel.add(vnc, c);
        panel.add(price_update, c);
        panel.add(Exit, c);


        /**
         * This section is for the ActionListeners for each button. The functions for the buttons is defined
         * in ButtonFunctions.java.
         */
        // TODO: Test individual actionListeners versus a single actionListener with multiple selector

        changeStoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeNumber = ButtonFunctions.setStoreNumber(storeNumber);
                Title.setText("");
                Title.setText("FCE-UTIL                 Store Number: " + storeNumber);
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

        reboot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonFunctions.rebootStore(storeNumber);
            }
        });
        vnc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonFunctions.launchVNC(storeNumber);
            }
        });
        cleanUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonFunctions.runCleanUpScript(storeNumber);
            }
        });
        termCust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonFunctions.terminateCustomProcess(storeNumber);
            }
        });
        price_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ITTBuilder.prepFile(storeNumber);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

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

        panel.setOpaque(true);
        panel.setBackground(Color.decode("0x3B3F42"));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setContentPane(panel);

        frame.pack();
        frame.setSize(600, 470);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
