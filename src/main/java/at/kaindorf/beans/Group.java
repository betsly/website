/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

/**
 *
 * @author nico
 */
public class Group {
    private int group_id;
    private String group_name;    
    private String description;
    private String password;

    public Group(int group_id, String group_name, String description, String password) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.description = description;
        this.password = password;
    }

    public Group() {
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    @Override
    public String toString() {
        return "Group{" + "group_id=" + group_id + ", group_name=" + group_name + ", description=" + description + '}';
    }
    
    
}
