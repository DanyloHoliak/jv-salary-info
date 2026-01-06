package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int day = 0;
    private static final int name = 1;
    private static final int hours = 2;
    private static final int amount = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dtFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dtTo = LocalDate.parse(dateTo, formatter);
        LocalDate actualDate;
        int payment;
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String person : data) {
            for (String actualName : names) {
                String[] devidedData = person.split(" ");
                actualDate = LocalDate.parse(devidedData[day], formatter);

                if (actualDate.isBefore(dtTo) || actualDate.isEqual(dtTo)
                        && actualDate.isAfter(dtFrom) || actualDate.isEqual(dtFrom)) {
                    if (actualName.equals(devidedData[name])) {
                        result.append(actualName).append(" - ");
                        payment = Integer.parseInt(devidedData[hours])
                                * Integer.parseInt(devidedData[amount]);
                        result.append(payment);
                    }
                }
            }
        }
        return result.toString();
    }
}
