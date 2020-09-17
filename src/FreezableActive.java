public class FreezableActive implements Freezable {

    private AccountUnfrozen onUnfrozen;

    public FreezableActive(AccountUnfrozen onUnfrozen){
        this.onUnfrozen = onUnfrozen;
    }

    @Override
    public Freezable deposit() {
        return this;
    }

    @Override
    public Freezable withdraw() {
        return this;
    }

    @Override
    public Freezable freezeAccount() {
        return new FreezableFrozen(this.onUnfrozen);
    }
}
