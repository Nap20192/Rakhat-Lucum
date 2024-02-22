package entities;

public class User {
    private int clearance;
    private String name, group, role, id;
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGroup() {
        return group;
    }
    public String getRole() {
        return role;
    }
    public int getClearance(){
        return clearance;
    }
    private User(Builder builder){
        this.id=builder.id;
        this.name= builder.name;
        this.group= builder.group;
        this.role=builder.role;
        this.clearance=builder.clearance;
    }

    public static class Builder {
        private String id;
        private String name;
        private String group;
        private  String role;
        private  int clearance;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder group(String group) {
            this.group = group;
            return this;
        }
        public Builder role(String role) {
            this.role = role;
            return this;
        }
        public Builder clearance(int clearance) {
            this.clearance = clearance;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}