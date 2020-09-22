package com.codeclan.example.sports_manager_project;
import com.codeclan.example.sports_manager_project.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TournamentTableTest {

    private TournamentTable tournamentTable1;
    private TeamMatch teamMatch1;
    private TeamMatch teamMatch2;
    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;
    private Team team5;
    private Venue venue1;
    private Venue venue2;
    private Venue venue3;
    private Venue venue4;
    private Tournament tournament1;
    private Date date;
    private Person person1;
    private ScoreEvent scoreEvent1;
    private ScoreEvent scoreEvent2;
    private Sport sport1;
    private ArrayList<TeamMatch> matches;

    @Before()
    public void before() {
        date = new GregorianCalendar(1991, Calendar.AUGUST, 8).getTime();
        person1 = new Person("Jude", "Bellingham", date, new Country("England"));
        sport1 = new Sport("football");
        tournament1 = new Tournament("Premier League", sport1);
        scoreEvent1 = new ScoreEvent(person1, 1, 45, teamMatch1);
        scoreEvent2 = new ScoreEvent(person1, 1, 55, teamMatch1);
        venue1 = new Venue("Wembley");
        venue2 = new Venue("Old Trafford");
        venue3 = new Venue("Upton Park");
        venue4 = new Venue("Stadium");
        team1 = new Team("Arsenal London", "ARS", venue1);
        team1.addPlayer(person1);

        team2 = new Team("Manchester United", "ManU", venue2);
        team3 = new Team("West Ham", "WHM", venue3);
        team4 = new Team("Millwall", "MIL", venue4);
        team5 = new Team("Norwich", "NOR", new Venue("Carrow Road"));
        teamMatch1 = new TeamMatch(team1, team2, team1.getVenue());
        teamMatch2 = new TeamMatch(team3, team4, team3.getVenue());
        teamMatch1.addScoreEvent(scoreEvent1);
        teamMatch1.addScoreEvent(scoreEvent2);
        tournament1.addMatch(teamMatch1);
        tournament1.addMatch(teamMatch2);

        matches = new ArrayList<>();
        matches.add(teamMatch1);
        matches.add(teamMatch2);
        tournamentTable1 = new TournamentTable(matches);
    }

    @Test
    public void canGetRecords() {
        assertEquals(4, tournamentTable1.getRecords().size());
    }

    @Test
    public void canGetRecordsKeys() {
        assertTrue( "Arsenal London", tournamentTable1.getRecords().containsKey(team1));
    }

    @Test
    public void canGetScoresFor() {
        assertEquals(2, tournamentTable1.getRecords().get(team1).getScoreFor());
    }

    @Test
    public void canGetWins() {
        assertEquals(1, tournamentTable1.getRecords().get(team1).getWins());
    }

    @Test
    public void canGetDraw() {
        assertEquals(1, tournamentTable1.getRecords().get(team3).getDraws());
    }

    @Test
    public void canGetLoss() {
        assertEquals(1, tournamentTable1.getRecords().get(team2).getLosses());
    }

    @Test
    public void canGetScoresAgainst(){
        assertEquals(2, tournamentTable1.getRecords().get(team2).getScoreAgainst());
    }

}
