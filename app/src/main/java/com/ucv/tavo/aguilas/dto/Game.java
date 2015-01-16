package com.ucv.tavo.aguilas.dto;

/**
 * Created by gustavo on 12/12/14.
 */
public class Game {
    String teams;
    String stadium;
    String time;
    String stat_juego;
    String dsInning;
    String visitor_team;
    String visitor_score;
    String home_team;
    String home_score;

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStat_juego() {
        return stat_juego;
    }

    public void setStat_juego(String stat_juego) {
        this.stat_juego = stat_juego;
    }

    public String getDsInning() {
        return dsInning;
    }

    public void setDsInning(String dsInning) {
        this.dsInning = dsInning;
    }

    public String getVisitor_team() {
        if (visitor_team == null || visitor_team == "") {
            return getTeams().split("vs")[0];
        } else{
            return visitor_team;
        }
    }

    public void setVisitor_team(String visitor_team) {
        this.visitor_team = visitor_team;
    }

    public String getVisitor_score() {
        return visitor_score;
    }

    public void setVisitor_score(String visitor_score) {
        this.visitor_score = visitor_score;
    }

    public String getHome_team() {
        if (home_team == null || home_team == "") {
            return getTeams().split("vs")[1];
        } else{
            return home_team;
        }
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getHome_score() {
        return home_score;
    }

    public void setHome_score(String home_score) {
        this.home_score = home_score;
    }
}
