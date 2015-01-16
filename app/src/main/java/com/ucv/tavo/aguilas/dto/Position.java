package com.ucv.tavo.aguilas.dto;

/**
 * Created by gustavo on 27/12/14.
 */
public class Position {
    String team;
    String games_played;
    String games_won;
    String games_lost;
    String avg;

    public String getDif() {
        return dif;
    }

    public void setDif(String dif) {
        this.dif = dif;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getGames_played() {
        return games_played;
    }

    public void setGames_played(String games_played) {
        this.games_played = games_played;
    }

    public String getGames_won() {
        return games_won;
    }

    public void setGames_won(String games_won) {
        this.games_won = games_won;
    }

    public String getGames_lost() {
        return games_lost;
    }

    public void setGames_lost(String games_lost) {
        this.games_lost = games_lost;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    String dif;
}
