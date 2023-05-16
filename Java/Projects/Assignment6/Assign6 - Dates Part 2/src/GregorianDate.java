public class GregorianDate extends Date {
    GregorianDate() {
    }

    GregorianDate(int newYear, int newMonth, int newDay) {
        year = newYear;
        month = newMonth;
        day = newDay;
    }

    @Override
    public boolean isLeapYear() {
        boolean leap = false;
        // If the year is divisible by 400 then automatically it is a leap year
        if ((this.year % 400) == 0) {
            leap = true;
            // Else if it is divisible by 100 then it is not a leap year
        } else if ((this.year % 100) == 0) {
            leap = false;
            // Finally if it is divisible by 4 then it is a leap year
        } else if ((this.year % 4) == 0) {
            leap = true;
        }

        return leap;
    }

    @Override
    public boolean isLeapYear(int year) {
        boolean leap = false;
        if ((year % 400) == 0) {
            leap = true;

        } else if ((year % 100) == 0) {
            leap = false;

        } else if ((year % 4) == 0) {
            leap = true;
        }

        return leap;
    }
}
