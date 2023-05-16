public class JulianDate extends Date {
    JulianDate() {
        this.day = 1;
        this.month = 1;
        this.year = 1;

        this.addDays(719164);
        // Calculating days since 1/1/1970
        long millis;
        millis = System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset();
        int days = (int)(millis / (1000*60*60*24));
        this.addDays(days);
    }

    JulianDate(int newYear, int newMonth, int newDay) {
        year = newYear;
        month = newMonth;
        day = newDay;
    }

    @Override
    public boolean isLeapYear() {
        return (this.year % 4) == 0;
    }

    @Override
    public boolean isLeapYear(int year) {
        return (year % 4) == 0;
    }
}
