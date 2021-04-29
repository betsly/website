/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zmugg
 */

@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    private String email;
    private String username;
    private String pw;
    private List<Group> joinedGroups;

    public User(String email, String username, String password) {
        this.email = email;
        this.pw = password;
        this.username = username;
    }

}
