package com.teamtreehouse.model;

import java.util.*;


/**
 * Created by yeshua on 9/16/2017.
 */
public class Team implements Comparable<Team> {

    private String teamName;
    private String coachName;
    private List<Player> mTeamPlayers;


    public Team(){

    }

    public Team(String teamName, String coachName) {
        this.teamName = teamName;
        this.coachName = coachName;
        mTeamPlayers = new LinkedList<>();
    }

    public Map<String, List<Player>> byHeight(){
        Map<String, List<Player>> byHeight = new TreeMap<>();
        String one = "35-40";
        String two = "41-46";
        String three = "47-50";
        List<Player> playersByHeight1 = null;
        List<Player> playersByHeight2 =null;
        List<Player> playersByHeight3= null;
        for (Player player: getPlayers()) {
            if (player.getHeightInInches() >= 35 && player.getHeightInInches() < 41) {
                if (playersByHeight1 == null) {
                    playersByHeight1 = new ArrayList<>();
                    byHeight.put(one, playersByHeight1);
                }
                playersByHeight1.add(player);
                byHeight.put(one, playersByHeight1);
            }
            if (player.getHeightInInches() >= 41 && player.getHeightInInches() < 47){
                if (playersByHeight2 == null) {
                    playersByHeight2 = new ArrayList<>();
                    byHeight.put(two, playersByHeight2);
                }
                playersByHeight2.add(player);
                byHeight.put(two,playersByHeight2);
            }
            if (player.getHeightInInches() >= 47 && player.getHeightInInches() < 50) {
                if (playersByHeight3 == null) {
                    playersByHeight3 = new ArrayList<>();
                    byHeight.put(three, playersByHeight3);
                }
                playersByHeight3.add(player);
                byHeight.put(three, playersByHeight3);
            }
        }
        return byHeight;
    }

    public Map<String, List<Player>> byExperience() {
        Map<String, List<Player>> byExperience = new TreeMap<>();
        String experienced = "experienced";
        String inexperienced = "inexperienced";
        List<Player> experiencedPlayers = null;
        List<Player> inexperiencedPlayers = null;
        for(Player player: getPlayers()){
            if(player.isPreviousExperience()){
                if(experiencedPlayers == null){
                    experiencedPlayers = new ArrayList<>();
                    byExperience.put(experienced, experiencedPlayers);
                }
                experiencedPlayers.add(player);
                byExperience.put(experienced, experiencedPlayers);
            }
            if(!player.isPreviousExperience()){
                if(inexperiencedPlayers== null){
                    inexperiencedPlayers = new ArrayList<>();
                    byExperience.put(inexperienced, inexperiencedPlayers);
                }
                inexperiencedPlayers.add(player);
                byExperience.put(inexperienced, inexperiencedPlayers);
            }

        }
        return byExperience;
    }

    public List<Player> getPlayersBasedOnExperience(String experience){
        List<Player> players = byExperience().get(experience);
        return players;
    }

    public Set <String> getHeightRanges(){
        return byHeight().keySet();
    }

    public List<Player> getPlayersBasedOnHeight(String ranges){
        List<Player> players = byHeight().get(ranges);
        return players;
    }

    public void addPlayer(Player player){
        mTeamPlayers.add(player);
    }
    public void removePlayer(Player player) {
        mTeamPlayers.remove(player);
    }

    public List<Player> getPlayers(){
        return mTeamPlayers;
    }


    public String getTeamName() {
        return teamName;
    }



    public String getCoachName() {
        return coachName;
    }


    @Override
    public int compareTo(Team other){
        int compare= this.getTeamName().compareTo(other.getTeamName());
        return compare;
    }
}
