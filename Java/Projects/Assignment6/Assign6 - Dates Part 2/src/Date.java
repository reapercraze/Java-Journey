public class Date {
    int year;
    int month;
    int day;

    Date() {
        this.day = 1;
        this.month = 1;
        this.year = 1970;

        // Calculating days since 1/1/1970
        long millis;
        millis = System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset();
        int days = (int)(millis / (1000*60*60*24));
        this.addDays(days);
    }

    Date(int newYear, int newMonth, int newDay) {
        year = newYear;
        month = newMonth;
        day = newDay;
    }

    public void printLongDate() {
        System.out.printf("%s %s, %s", this.getMonthName(), this.day, this.year);
    }

    public void printShortDate() {
        System.out.printf("%s/%s/%s", this.month, this.day, this.year);
    }

    public boolean isLeapYear() {
        return false;
    }

    public boolean isLeapYear(int year) {
        return false;
    }

    public void addDays(int days) {
        // Check if days to add is positive
        if (days <= 0) {
            return;
        }

        for (int i = 0; i < days; i++) {
            this.day += 1;

            if (this.day > this.getNumberOfDaysInMonth(this.year, this.month)) {
                this.day = 1;
                this.month += 1;

                if (this.month > 12) {
                    this.month = 1;
                    this.year += 1;
                }
            }
        }
    }

    public void subtractDays(int days) {
        // Check if days to subtract is positive
        if (days <= 0) {
            return;
        }

        for (int i = 0; i < days; i++) {
            this.day -= 1;

            if (this.day == 0) {
                this.month -= 1;

                if (this.month == 0) {
                    this.month = 12;
                    this.year -= 1;
                }

                this.day = this.getNumberOfDaysInMonth(this.year, this.month);
            }
        }
    }

    public String getMonthName() {
        String month;
        switch (this.month) {
            case 1: month = "January";
                break;
            case 2: month = "February";
                break;
            case 3: month = "March";
                break;
            case 4: month = "April";
                break;
            case 5: month = "May";
                break;
            case 6: month = "June";
                break;
            case 7: month = "July";
                break;
            case 8: month = "August";
                break;
            case 9: month = "September";
                break;
            case 10: month = "October";
                break;
            case 11: month = "November";
                break;
            case 12: month = "December";
                break;
            default: month = "Invalid month";
                break;
        }
        return month;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public int getDayOfMonth() {
        return this.day;
    }

    private int getNumberOfDaysInMonth(int year, int month) {
        int days;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 2:
                if (isLeapYear(year)) {
                    days = 29;
                } else {
                    days = 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            default: days = 0;
                break;
        }
        return days;
    }
}
