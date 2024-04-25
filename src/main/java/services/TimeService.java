package services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeService {
    public static String parse(long time) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(time), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTime.format(formatter);
    }
}
