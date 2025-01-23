package pt.ipleiria.estg.dei.refeitorio.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DateUtils {
    public static SimpleDateFormat formatYYYYMMdd =  new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat formatddMMYYYY =  new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    /**
     * Generates an array of dates based on the selected date,
     * including 3 days before and 3 days after.
     *
     * @param selectedDate The selected date in "dd/MM/yyyy" format.
     * @return A list of dates as strings in "dd/MM/yyyy" format.
     * @throws Exception if the date format is invalid.
     */
    public static List<Calendar> generateDateRange(String selectedDate) throws Exception {
        // Parse the selected date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(formatYYYYMMdd.parse(selectedDate)));

        // Prepare the result list
        List<Calendar> dateRange = new ArrayList<>();

        // Add dates 3 days before, the selected date, and 3 days after
        for (int i = -3; i <= 3; i++) {
            Calendar tempCalendar = (Calendar) calendar.clone();
            tempCalendar.add(Calendar.DAY_OF_MONTH, i);
            dateRange.add(tempCalendar);
        }

        return dateRange;
    }

    public static String formatToddMMyyyy(String date){
        try {
           Date parsedDate = formatYYYYMMdd.parse(date);
           if(parsedDate != null){
               return formatddMMYYYY.format(parsedDate);
           }
           return date;
        } catch (ParseException e) {
            return date;
        }
    }
}
