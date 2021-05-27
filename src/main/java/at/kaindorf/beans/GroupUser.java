/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nico
 */

@AllArgsConstructor
@NoArgsConstructor
@Data

public class GroupUser {
    private int groupID;
    private String username;
    private int points;
}
