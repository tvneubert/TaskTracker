package ch.zhaw.prog2.tasktracker;

/**
 * This class is used to format the time in the format hh:mm:ss:ms
 * where hh is the hours, mm is the minutes, ss is the seconds and ms is the milliseconds.
 * The format is always two digits for each value.
 */
public class TimeFormater {

    /**
     * This method is used to format the time in the format hh:mm:ss:ms
     * where hh is the hours, mm is the minutes, ss is the seconds and ms is the milliseconds.
     * The format is always two digits for each value.
     *
     * @param milliseconds The time in milliseconds.
     * @return The formatted time.
     */
    public static String formatTimerTime(int milliseconds) {
        String result = "";
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        int ms = (int) (milliseconds % 1000);

        result += String.format("%02d:", hours);
        result += String.format("%02d:", minutes);
        result += String.format("%02d.", seconds);
        result += String.format("%03d", ms);

        return result;
    }
}
