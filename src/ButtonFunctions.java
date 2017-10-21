public class ButtonFunctions {

    // Functions for the buttons to perform on click

    public void setStoreNumber() {
        /**
         * Prompt the user for input via JOptionPane input dialog and return that
         * value to the Store Number global variable. This string will be used in every
         * function, so checking it is vital.
         */

    }

    public void constantPing(String storeNumber) {
        /**
         * @param storeNumber
         * Open a command prompt and run a constant ping on the remote site designated
         * by the Store Number string.
         */

    }

    public void rebootStore(String storeNumber) {
        /**
         * @param storeNumber
         * Reboot the site using psshutdown using the Store Number
         */

    }

    public void terminateMWS(String storeNumber) {
        /**
         * @param storeNumber
         * Remotely terminate MWSStore.exe one the site designated by the Store Number
         */

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
