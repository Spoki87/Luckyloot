package com.luckyloot.user.wallet.model;

import com.luckyloot.exception.domain.InsufficientFundsException;
import com.luckyloot.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", unique = true)
  private User user;

  private BigDecimal balance = BigDecimal.ZERO;

  public Wallet(User user) {
    this.user = user;
  }

  public void add(BigDecimal amount) {
    this.balance = this.balance.add(amount);
  }

  public void subtract(BigDecimal amount) {
    if (balance.compareTo(amount) < 0) {
      throw new InsufficientFundsException();
    }
    this.balance = this.balance.subtract(amount);
  }
}