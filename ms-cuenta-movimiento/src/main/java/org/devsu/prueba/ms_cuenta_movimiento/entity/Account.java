package org.devsu.prueba.ms_cuenta_movimiento.entity;

import java.math.BigDecimal;
import java.util.Set;

import org.devsu.prueba.ms_cuenta_movimiento.model.EnumAccountType;
import org.devsu.prueba.ms_cuenta_movimiento.model.EnumStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuentas")
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
	
    @Column(unique = true)
    private String accountNumber;
    
    @Enumerated(EnumType.STRING)
    private EnumAccountType accountType;
    
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private EnumStatus status;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Transaction> transactions;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

}
