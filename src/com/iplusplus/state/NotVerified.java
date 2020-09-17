package com.iplusplus.state;

import com.iplusplus.AccountState;
import com.iplusplus.AccountUnfrozen;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class NotVerified implements AccountState {
    private AccountUnfrozen onUnfrozen;

    public NotVerified(AccountUnfrozen onUnfrozen){
        this.onUnfrozen = onUnfrozen;
    }
    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
        addToBalance.accept(amount);
        return this;
    }

    @Override
    public AccountState withdraw(BigDecimal balance, BigDecimal amount, Consumer<BigDecimal> subtractFromBalance) {
        return this;
    }

    @Override
    public AccountState freezeAccount() {
        return this;
    }

    @Override
    public AccountState holderVerified() {
        return new Active(this.onUnfrozen);
    }

    @Override
    public AccountState closeAccount() {
        return this;
    }
}
