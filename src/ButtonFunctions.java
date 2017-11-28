import javax.swing.*;
import java.io.IOException;

public class ButtonFunctions {

    // PSEXEC specific variables
    static String primer = "cmd.exe /c start ";
    static String psexec = "psexec ";
    static String head = "\\\\fce-";
    static String user = " -u helpdesk ";
    static String password = "-p LimeCoke ";
    static String mws = " MWSStore.exe";
    static String exe = ".exe";

    // Functions for the buttons to perform on click

    public static String setStoreNumber(String storeNumber) {
        /**
         * Prompt the user for input via JOptionPane input dialog and return that
         * value to the Store Number global variable. This string will be used in every
         * function, so checking it is vital.
         */

        storeNumber = JOptionPane.showInputDialog("Enter the store number.");

        return storeNumber;

    }

    public static void constantPing(String storeNumber) {
        /**
         * @param storeNumber
         * Open a command prompt and run a constant ping on the remote site designated
         * by the Store Number string.
         */

        String cmd = primer + "ping fce-" + storeNumber + " -t";

        try {
            Process rt = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void rebootStore(String storeNumber) {
        /**
         * @param storeNumber
         * Reboot the site using psshutdown using the Store Number.
         */

    }

    public static void terminateMWS(String storeNumber) {
        /**
         * @param storeNumber
         * Remotely terminate MWSStore.exe one the site designated by the Store Number.
         * Easily the most used utility of the current program, so care is being taken to
         * test for any scenario and to make sure this is extensible for the migration to
         * PDI software after Pinnacle is completely phased out.
         */

        String cmd = primer + psexec + head + storeNumber + user + password + "taskkill" + mws;

        try {
            Process rt = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void terminateCustomProcess(String storeNumber) {
        /**
         * @param storeNumber
         * Remotely terminate anything ending in .exe on the site designated by the Store Number.
         */

    }

    public static void runCleanUpScript(String storeNumber) {
        /**
         * @param storeNumber
         * Execute clean_up.bat from C:\Install on the site designated by the Store Number.
         * This utility is less often used, but making use of the script (include in jar?)
         * averages 4-7GB of space cleared out. Current PC in use by FCE as of 10/2017 has
         * a 32GB SSD making space a real concern.
         */

    }

    public static void launchVNC(String storeNumber) {
        /**
         * Launch a VNC Viewer session with args, provided by the Store Number.
         */

    }
}
