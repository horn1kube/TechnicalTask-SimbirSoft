package org.simbirsoft.bankingproject.utils;

import com.opencsv.CSVWriter;
import org.apache.commons.lang3.StringUtils;
import org.simbirsoft.bankingproject.config.PatternConfig;
import org.simbirsoft.bankingproject.model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Files {
    public static void writeTransactionsInCSV(List<Transaction> transactions, Path path) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()), ' ',
                CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            writer.writeAll(transactions.stream().map(transaction -> {
                String[] line = new String[3];
                line[0] = transaction.getDateTime().format(DateTimeFormatter.ofPattern(PatternConfig.CSV_DATETIME_PATTERN, Locale.ENGLISH));
                line[1] = String.valueOf(transaction.getAmount());
                line[2] = StringUtils.capitalize(String.valueOf(transaction.getType()));
                return line;
            }).toList());
        }
    }
}
