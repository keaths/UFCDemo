package UFC.demo.component;

import UFC.demo.entity.Event;
import UFC.demo.entity.Fight;
import UFC.demo.entity.FightWinner;
import UFC.demo.entity.Fighter;
import UFC.demo.repository.EventRepo;
import UFC.demo.service.EventService;
import UFC.demo.service.FighterService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.jsoup.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class ApplicationStartup {

    private EventService eventService;
    private FighterService fighterService;

    public ApplicationStartup(EventService eventService, FighterService fighterService){
        this.eventService = eventService;
        this.fighterService = fighterService;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup(){
        if(eventService.isTableEmpty()){
            getEvents("http://ufcstats.com/statistics/events/completed?page=all");
        } else{
            System.out.println("events already gathered");
        }
        if(fighterService.isTableEmpty()){
            getFighters();
        } else{
            System.out.println("fighters already gathered");
        }
    }
    public void getEvents(String url){
        Document doc;
        try{
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Element product = doc.select("tbody").first();
        assert product != null;
        Elements productElements = product.children();
        productElements.remove(0);


        for(Element el : productElements){
            Event event = new Event();
            Element eventTitle = el.getElementsByClass("b-statistics__table-content").first();
            String eventName = "";
            String eventLink = "";
            if(eventTitle != null){
                eventName = eventTitle.child(0).text();
                eventLink = eventTitle.child(0).attr("href");
            }
            String eventDate = el.getElementsByClass("b-statistics__date").text();
            String eventLocation = el.getElementsByClass("b-statistics__table-col b-statistics__table-col_style_big-top-padding").text();
            event.setEventName(eventName);
            event.setEventDate(eventDate);
            event.setEventLocation(eventLocation);

            eventService.postEvent(event);
        }
        System.out.println("get Events done");
    }

    public void getFighters(){
        Document doc;
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for(char letter : alphabet){
            try{
                doc = Jsoup.connect("http://ufcstats.com/statistics/fighters?char=" + letter + "&page=all").timeout(120 * 1000).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Element product = doc.select("tbody").first();
            assert product != null;
            Elements productElements = product.children();
            productElements.remove(0);


            for(Element el : productElements){
                Fighter fighter = new Fighter();

                Element firstNameTD = el.select("td.b-statistics__table-col").get(0);
                String firstName = "";
                if(firstNameTD != null){
                    firstName = firstNameTD.child(0).text();
                }

                Element lastNameTD = el.select("td.b-statistics__table-col").get(1);
                String lastName = "";
                if(lastNameTD != null){
                    lastName = lastNameTD.child(0).text();
                }

                Element nickNameTD = el.select("td.b-statistics__table-col").get(2);
                String nickName = "";
                if(nickNameTD != null){
                    nickName = nickNameTD.child(0).text();
                }

                Element heightTD = el.select("td.b-statistics__table-col").get(3);
                String height = "";
                if(!heightTD.text().equals("--")){
                    height = heightTD.text();
                }

                Element weightTD = el.select("td.b-statistics__table-col").get(4);
                int weight = 0;
                if(!weightTD.text().equals("--")){
                    weight = Integer.parseInt(weightTD.text().split("\\s+")[0]);
                }

                Element reachTD = el.select("td.b-statistics__table-col").get(5);
                String reach = "";
                if(!reachTD.text().equals("--")){
                    reach = reachTD.text();
                }

                Element stanceTD = el.select("td.b-statistics__table-col").get(6);
                String stance = "";
                if(!stanceTD.text().isEmpty()){
                    stance = stanceTD.text();
                }

                Element winTD = el.select("td.b-statistics__table-col.b-statistics__table-col_type_small").first();
                int win = 0;
                if(winTD != null){
                    win = Integer.parseInt(winTD.text());
                }

                Element lossTD = el.select("td.b-statistics__table-col.b-statistics__table-col_type_small").get(1);
                int loss = 0;
                if(lossTD != null){
                    loss = Integer.parseInt(lossTD.text());
                }

                Element drawTD = el.select("td.b-statistics__table-col.b-statistics__table-col_type_small").get(2);
                int draw = 0;
                if(drawTD != null){
                    draw = Integer.parseInt(drawTD.text());
                }

                Element beltTD = el.select("td.b-statistics__table-col").get(8);
                boolean belt = false;
                if(!beltTD.children().isEmpty()){
                    belt = true;
                }
                fighter.setFirstName(firstName);
                fighter.setLastName(lastName);
                fighter.setNickName(nickName);
                fighter.setHeight(height);
                fighter.setWeight(weight);
                fighter.setReach(reach);
                fighter.setStance(stance);
                fighter.setWins(win);
                fighter.setLosses(loss);
                fighter.setDraws(draw);
                fighter.setBelt(belt);
                fighterService.postFighter(fighter);
                System.out.println(fighter.getFirstName() + " " + fighter.getLastName() + " added");
            }
        }
        System.out.println("get fighters done");
    }

//    public static void getFights() throws IOException {
//        for(int i = 1; i < events.size(); i++){
//            Document fights = Jsoup.connect(events.get(i).getEventUrls()).timeout(600 * 1000).get();
//            ArrayList<String> eventFights = new ArrayList<>();
//            Elements els = fights.select("a.b-flag.b-flag_style_green");
//            for(Element el : els){
//                eventFights.add(el.attr("href"));
//            }
//            for(String link : eventFights){
//                Document fightPage = Jsoup.connect(link).timeout(600 * 1000).get();
//                System.out.println(link);
//                Fight fight = new Fight();
//
//                fight.setMethod(fightPage.select("i.b-fight-details__text-item_first").get(0).child(1).text());
//                if(fight.getMethod().contains("Decision")){
//                    fight.setDetails(fight.getMethod().split("\\s+")[2]);
//                    fight.setMethod(fight.getMethod().split("\\s+")[0]);
//                } else{
//                    Element detailEl = fightPage.select("p.b-fight-details__text").get(1);
//                    if(detailEl.text().split(": ").length == 2){
//                        fight.setDetails(detailEl.text().split(": ")[1]);
//                    }
//                }
//                System.out.println(fight.getMethod());
//                System.out.println(fight.getDetails());
//
//                fight.setRound((long) Integer.parseInt(fightPage.select("i.b-fight-details__text-item").get(0).text().split("\\s+")[1]));
//                fight.setTime(fightPage.select("i.b-fight-details__text-item").get(1).text().split("\\s+")[1]);
//                fight.setRef(fightPage.select("i.b-fight-details__text-item").get(3).child(1).text());
//
//                String[] fightWinnerName = fightPage.select("a.b-link.b-fight-details__person-link").get(0).text().split("\\s+");
//                String[] fightLoserName = fightPage.select("a.b-link.b-fight-details__person-link").get(1).text().split("\\s+");
//
//                FightWinner fightWinner = new FightWinner();
////                if(fightWinnerName.length < 2){
////                    fightWinner.setFighter(findFighterByFirstNameOnly(fightWinnerName[0]));
////                } else{
////                    fightWinner.setFighter(findFighter(fightWinnerName[0], fightWinnerName[1]));
////                }
//
//
//                fightWinner.setKnockDowns(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(1).child(0).text()));
//                fightWinner.setSigStrikesLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(2).child(0).text().split("\\s+")[0]));
//                fightWinner.setSigStrikesAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(2).child(0).text().split("\\s+")[2]));
//                fightWinner.setTotalStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(4).child(0).text().split("\\s+")[0]));
//                fightWinner.setTotalStikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(4).child(0).text().split("\\s+")[2]));
//                fightWinner.setTakeDownLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(5).child(0).text().split("\\s+")[0]));
//                fightWinner.setTakeDownAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(5).child(0).text().split("\\s+")[2]));
//                fightWinner.setSubAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(8).child(0).text()));
//
//                fightWinner.setControlTime(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(9).child(0).text());
//
//                fightWinner.setHeadStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(3).child(0).text().split("\\s+")[0]));
//                fightWinner.setHeadStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(3).child(0).text().split("\\s+")[2]));
//                fightWinner.setBodyStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(4).child(0).text().split("\\s+")[0]));
//                fightWinner.setBodyStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(4).child(0).text().split("\\s+")[2]));
//                fightWinner.setLegStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(5).child(0).text().split("\\s+")[0]));
//                fightWinner.setLegStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(5).child(0).text().split("\\s+")[2]));
//                fightWinner.setDistanceStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(6).child(0).text().split("\\s+")[0]));
//                fightWinner.setDistanceStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(6).child(0).text().split("\\s+")[2]));
//                fightWinner.setClinchStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(7).child(0).text().split("\\s+")[0]));
//                fightWinner.setClinchStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(7).child(0).text().split("\\s+")[2]));
//
//                fight.setFightWinner(fightWinner);
//
//                FightLoser fightLoser = new FightLoser();
////                if(fightLoserName.length < 2){
////                    fightLoser.setFighter(findFighterByFirstNameOnly(fightLoserName[0]));
////                } else{
////                    fightLoser.setFighter(findFighter(fightLoserName[0], fightLoserName[1]));
////                }
//
//
//                fightLoser.setKnockDowns(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(1).child(1).text()));
//                fightLoser.setSigStrikesLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(2).child(1).text().split("\\s+")[0]));
//                fightLoser.setSigStrikesAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(2).child(1).text().split("\\s+")[2]));
//                fightLoser.setTotalStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(4).child(1).text().split("\\s+")[0]));
//                fightLoser.setTotalStikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(4).child(1).text().split("\\s+")[2]));
//                fightLoser.setTakeDownLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(5).child(1).text().split("\\s+")[0]));
//                fightLoser.setTakeDownAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(5).child(1).text().split("\\s+")[2]));
//                fightLoser.setSubAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(8).child(1).text()));
//
//                fightLoser.setControlTime(fightPage.select("tbody.b-fight-details__table-body").get(0).child(0).child(9).child(0).text());
//
//                fightLoser.setHeadStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(3).child(1).text().split("\\s+")[0]));
//                fightLoser.setHeadStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(3).child(1).text().split("\\s+")[2]));
//                fightLoser.setBodyStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(4).child(1).text().split("\\s+")[0]));
//                fightLoser.setBodyStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(4).child(1).text().split("\\s+")[2]));
//                fightLoser.setLegStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(5).child(1).text().split("\\s+")[0]));
//                fightLoser.setLegStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(5).child(1).text().split("\\s+")[2]));
//                fightLoser.setDistanceStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(6).child(1).text().split("\\s+")[0]));
//                fightLoser.setDistanceStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(6).child(1).text().split("\\s+")[2]));
//                fightLoser.setClinchStrikeLand(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(7).child(1).text().split("\\s+")[0]));
//                fightLoser.setClinchStrikeAtt(Integer.parseInt(fightPage.select("tbody.b-fight-details__table-body").get(2).child(0).child(7).child(1).text().split("\\s+")[2]));
//
//                fight.setFightLoser(fightLoser);
//                events.get(i).getFights().add(fight);
//            }
//        }
//        System.out.println("get fights done");
//    }

}
