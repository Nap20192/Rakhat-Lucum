package entities;

public class Student extends User {
    private Student(Builder builder) {
        super(builder);
    }
    public static class Builder extends User.Builder {
        public Builder() {
            role("STUDENT");
            clearance(1);
        }
        @Override
        public Student build() {
            return new Student(this);
        }
    }
}
