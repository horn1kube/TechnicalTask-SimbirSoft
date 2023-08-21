package org.simbirsoft.bankingproject.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Transaction {
    private LocalDateTime dateTime;
    private int amount;
    private TransactionType type;
}
