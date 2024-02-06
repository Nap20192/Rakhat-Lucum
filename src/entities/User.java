package entities;

public class User {
    int clearance;
    String name, group, role, id;

    public User(){

    }
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

    public void setId(String id) { this.id = id; }
    public void setName(String name) {
        this.name = name;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setClearance(int clearance){
        this.clearance = clearance;
    }

}