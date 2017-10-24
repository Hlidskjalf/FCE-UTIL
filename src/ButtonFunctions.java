import javax.swing.*;

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

        System.out.println(cmd);
    }

    public void rebootStore(String storeNumber) {
        /**
         * @param storeNumber
         * Reboot the site using psshutdown using the Store Number
         */

    }

    public static void terminateMWS(String storeNumber) {
        /**
         * @param storeNumber
         * Remotely terminate MWSStore.exe one the site designated by the Store Number
         */

        //TODO build PSEXEC string to terminate MWS Store remotely
        String cmd = primer + psexec + head + storeNumber + user + password + "taskkill" + mws;

        System.out.println(cmd);
    }

    public void terminateCustomProcess(String storeNumber) {
        /**
         * @param storeNumber
         * Remotely terminate anything ending in .exe one the site designated by the Store Number
         */

    }

    public void runCleanUpScript(String storeNumber) {
        /**
         * @param storeNumber
         * Execute clean_up.bat from C:\Install on the site designated by the Store Number
         */

    }

    public void launchVNC(String storeNumber) {
        /**
         * Launch a VNC Viewer session with args, provided by the Store Number
         */

    }
}
