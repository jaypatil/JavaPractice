public class FreezableFrozen implements Freezable {

    private AccountUnfrozen onUnfrozen;

    public FreezableFrozen(AccountUnfrozen onUnfrozen) {
        this.onUnfrozen = onUnfrozen;
    }

    @Override
    public Freezable deposit() {
        this.onUnfrozen.handle();
        return new FreezableActive(this.onUnfrozen);
    }

    @Override
    public Freezable withdraw() {
        return this.unfreeze();
    }

    private Freezable unfreeze() {
        this.onUnfrozen.handle();
        return new FreezableActive(this.onUnfrozen);
    }

    @Override
    public Freezable freezeAccount() {
        return this;
    }
}
