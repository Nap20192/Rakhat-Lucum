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

    User(Builder builder){
        this.id=builder.id;
        this.name= builder.name;
        this.group= builder.group;
        this.role=builder.role;
        this.clearance=builder.clearance;
    }

    public class UserFactory {
        public static User createUser(String role, String id, String name, String group) {
            switch (role.toUpperCase()) {
                case "STUDENT":
                    return new Student.Builder()
                            .id(id)
                            .name(name)
                            .group(group)
                            .role(role)
                            .build();
                case "STAFF":
                    return new Staff.Builder()
                            .id(id)
                            .name(name)
                            .role(role)
                            .build();
                // Add more cases for other user types if needed
                default:
                    throw new IllegalArgumentException("Invalid role: " + role);
            }
        }
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setClearance(int clearance) {
        this.clearance = clearance;
    }
}

