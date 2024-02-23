package entities;

public class Staff extends User {
    private Staff(Builder builder) {
        super(builder);
    }
    public static class Builder extends User.Builder {
        public Builder() {
            role("STAFF");
            clearance(1);
        }
        @Override
        public Staff build() {
            return new Staff(this);
        }
    }
}
