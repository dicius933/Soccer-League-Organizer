package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * Created by yeshua on 9/16/2017.
 */
public class Menu {
    // controller
    private Team team;
    private Team newTeam;
    private BufferedReader reader;
    private Map<String,String> menu;
    private List<Team> teams ;
    private List<Player> list = Arrays.asList(Players.load());
    private List<Player> list1 = new LinkedList<>(list);




    public Menu(Team team){
        this.team = team;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.menu = new HashMap<>();
        this.menu.put("Create", "Create a new team");
        this.menu.put("Add", "Add a player to a team");
        this.menu.put("Remove", "Remove a player from a team");
        this.menu.put("Report", "View a report of a team by height");
        this.menu.put("Balance", "View the League Balance Report");
        this.menu.put("Roster", "View roster");
        this.menu.put("Quit", "Exits the program");
    }

    private String promptAction() throws IOException{
        System.out.printf("\nMenu%n");
        for (Map.Entry<String,String> option :menu.entrySet()){
            System.out.printf("%s - %s %n", option.getKey(), option.getValue());
        }
        System.out.println("Select an option:");
        String choice = reader.readLine();
        return choice.trim().toLowerCase();
    }

    public void run() {
        String choice = "";
        teams = new ArrayList<>();
        int option;
        Collections.sort(list);
        Collections.sort(list1);
        do{
            try {
                choice = promptAction();
                switch(choice){
                    case "create":
                        newTeam = promptNewTeam();
                        teams.add(newTeam);
                        organizeTeam();
                        break;
                    case "add":
                        option = promptForIndex(promptTeamList());
                        team = teams.get(option);
                        option = promptForIndex(promptPlayerList());
                        team.addPlayer(list1.remove(option));
                        System.out.printf("%s %s added!%n", list.get(option).getFirstName(),list.get(option).getLastName());
                        break;
                    case "remove":
                        //option = promptForIndex(promptPlayerList());
                        option =promptForIndex(promptTeamList());
                        team = teams.get(option);
                        option = promptForIndex(promptPlayersFromTeam());
                        team.removePlayer(list.get(option));
                        list1.add(list.get(option));
                        Collections.sort(list1);
                        System.out.printf("%s %s removed!%n", list.get(option).getFirstName(),list.get(option).getLastName());
                        break;
                    case "report":
                        option = promptForIndex(promptTeamList());
                        team = teams.get(option);
                        List<Player> l1;
                        List<Player> l2;
                        List<Player> l3;
                        l1 = team.getPlayersBasedOnHeight("35-40");
                        l2 = team.getPlayersBasedOnHeight("41-46");
                        l3 = team.getPlayersBasedOnHeight("47-50");

                            try {
                                System.out.printf("35-40\n");
                                for (Player player : l1) {
                                    System.out.printf("%s %s %s, ", player.getFirstName(), player.getLastName(), player.getHeightInInches());
                                }
                            }catch(NullPointerException e){

                            }try {
                                System.out.printf("\n41-46\n");
                                for (Player player : l2) {
                                    System.out.printf("%s %s %s, ", player.getFirstName(), player.getLastName(), player.getHeightInInches());
                                }
                            }catch(NullPointerException e){

                            }try {
                                System.out.printf("\n47-50\n");
                                for (Player player : l3) {
                                    System.out.printf("%s %s %s, ", player.getFirstName(), player.getLastName(), player.getHeightInInches());
                                }
                            }catch(NullPointerException e){
                            }
                        break;
                    case "balance":
                        String exp = "experienced";
                        String inexp = "inexperienced";

                        for(Team team : teams){
                            List<Player> one =  team.getPlayersBasedOnExperience(inexp);
                            List<Player> two = team.getPlayersBasedOnExperience(exp);

                            System.out.printf("\t\t%s\n",team.getTeamName());
                            System.out.printf("Inexperienced\t Experienced\n");

                            try {
                                for (int i = 0 ; (i < one.size() || i < two.size() ); i++ ){
                                    try {
                                        System.out.printf("%s %s %s\t\t", one.get(i).getFirstName(), one.get(i).getLastName(), one.get(i).isPreviousExperience());

                                    } catch(IndexOutOfBoundsException e){
                                        System.out.printf(null+"\t\t");
                                    } try{
                                        System.out.printf("%s %s %s\n",two.get(i).getFirstName(), two.get(i).getLastName(), two.get(i).isPreviousExperience());
                                    }catch(IndexOutOfBoundsException e){
                                        System.out.printf(null+"\n");
                                    }
                                }
                            System.out.printf("\n # of inexperienced players %d\t# of experienced players %d\n", one.size(), two.size());
                            } catch(NullPointerException e){
                                System.out.printf("Players incomplete\n");
                            }
                        }
                        break;
                    case "roster":
                        for(Team team: teams){
                            System.out.printf("Team %s coached by %s - ",team.getTeamName(), team.getCoachName());

                            for(Player player: team.getPlayers()){
                                System.out.printf("\n   [%s %s %s %s]\n",player.getFirstName(),player.getLastName(),player.getHeightInInches(), Boolean.toString(player.isPreviousExperience() ));
                            }
                        }

                        break;
                    case "quit":

                        break;
                    default:
                        System.out.printf("Unknown choice :'%s'. Try again %n%n%n", choice);

                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }

        }while(!choice.equals("quit"));
    }


        private Team promptNewTeam()throws IOException{
            System.out.printf("Enter team name:");
            String teamName = reader.readLine();
            System.out.printf("Enter couch name:");
            String couchName = reader.readLine();
            return new Team(teamName, couchName);
        }

        private List promptPlayerList(){
            List newList = new ArrayList<>();

            for (Player player : list1){
                newList.add( String.format("%s %s %s %s", player.getFirstName(),player.getLastName(), player.getHeightInInches(),Boolean.toString( player.isPreviousExperience() )));
            }
            return newList;
        }

        private List<Team> organizeTeam(){
            Collections.sort(teams);
            return teams;
        }

        private List promptTeamList(){
            List newList = new ArrayList();
            for(Team team : organizeTeam()) {
                newList.add(String.format("Team %s coached by %s", team.getTeamName(), team.getCoachName()));
            }
            return newList;
        }

        private List promptPlayersFromTeam(){
            List newList= new LinkedList<>();
            for (Player player : team.getPlayers()){
                newList.add(String.format("%s %s %s %s", player.getFirstName(), player.getLastName(),player.getHeightInInches(), Boolean.toString( player.isPreviousExperience() )));
            }
            return  newList;
        }

        private int promptForIndex(List<String> options) throws IOException {
            int counter = 1;
            for(String option : options) {
                System.out.printf("%d.) %s %n", counter, option);
                counter++;
            }
            System.out.print("Your choice:  ");
            String optionAsString = reader.readLine();
            //validation by taking out the whitespace.
            int choice = Integer.parseInt(optionAsString.trim() );
            return choice - 1;
        }


}


