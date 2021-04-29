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
public class BetKoPhase {
    private int betId;
    private int groupId;
    private String name;
    private Country country1;
    private Country country2;
    private boolean closed;

    public BetKoPhase(int betId, int groupId, String name, Country country1, Country country2) {
        this.betId = betId;
        this.groupId = groupId;
        this.name = name;
        this.country1 = country1;
        this.country2 = country2;
    }
}
