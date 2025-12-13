package net.javaguides.tendermanagementms.model;

import jakarta.persistence.*;


@Entity
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String rolename;

    public RoleModel(){

    }

    public RoleModel(int id){
        this.id = id;
    }
    public RoleModel(String rolename) {
        this.rolename = rolename;
    }

    public int getId() {
        return id;
    }

    public RoleModel(int id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
