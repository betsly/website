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
public class BetGroupPhase {
    private int id;
    private int groupId;
    private String name;
    private Country country1;
    private Country country2;
    private Country country3;
    private Country country4;
    private boolean closed;

    public BetGroupPhase(int id, int groupId, String name, Country country1, Country country2, Country country3, Country country4) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.country1 = country1;
        this.country2 = country2;
        this.country3 = country3;
        this.country4 = country4;
    }
    
    
}
