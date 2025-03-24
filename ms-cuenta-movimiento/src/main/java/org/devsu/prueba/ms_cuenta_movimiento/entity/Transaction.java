package org.devsu.prueba.ms_cuenta_movimiento.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
	
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
    
    private BigDecimal initialBalance;
    private BigDecimal value;
    private BigDecimal balance;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now(ZoneId.systemDefault());
    }

}
