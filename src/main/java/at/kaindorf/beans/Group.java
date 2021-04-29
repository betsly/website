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

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {
    private int id;
    private String name;    
    private String description;
    private String password;
    private String host;
    private int points;
}
