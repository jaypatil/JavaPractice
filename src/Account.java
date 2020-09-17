import java.math.BigDecimal;

public class Account {
    private boolean isVerified;
    private boolean isClosed;
    private BigDecimal balance;

    private EnsureUnfrozen ensureUnfrozen;
    private AccountUnfrozen onUnfrozen;

    public Account(AccountUnfrozen onUnfrozen) {
        this.balance = BigDecimal.ZERO;
        this.ensureUnfrozen = this::stayUnfrozen;
        this.onUnfrozen = onUnfrozen;
    }

    public void holderVerified() {
        this.isVerified = true;
    }

    public void closeAccount() {
        this.isClosed = true;
    }

    public void freezeAccount() {
        if (this.isClosed) {
            return;
        }
        if (!this.isVerified) {
            return;
        }
        this.ensureUnfrozen = this::unfreeze;
    }

    public void deposit(BigDecimal amount) {
        if (!this.isClosed) {
            return;
        }
        this.ensureUnfrozen.execute();
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (this.isVerified)
            return;
        if (!this.isClosed) {
            return;
        }
        this.ensureUnfrozen.execute();
        this.balance = this.balance.subtract(amount);
    }

    private void unfreeze() {
        this.onUnfrozen.handle();
        this.ensureUnfrozen = this::stayUnfrozen;
    }

    private void stayUnfrozen() {
    }
}
