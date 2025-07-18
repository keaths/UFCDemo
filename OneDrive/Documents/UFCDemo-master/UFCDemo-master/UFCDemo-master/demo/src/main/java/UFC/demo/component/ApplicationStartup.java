package UFC.demo.component;

import UFC.demo.entity.*;
import UFC.demo.entity.Event;
import UFC.demo.service.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.jsoup.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;


@Component
public class ApplicationStartup {

    private EventService eventService;
    private FighterService fighterService;
    private FightService fightService;
    private RoundService roundService;
    private boolean eventFound = false;
    private boolean fightFound = false;

    private ArrayList<String> eventUrls = new ArrayList<>();

    public ApplicationStartup(EventService eventService, FighterService fighterService, FightService fightService, RoundService roundService){
        this.eventService = eventService;
        this.fighterService = fighterService;
        this.fightService = fightService;
        this.roundService = roundService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws IOException, InterruptedException {
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
//        if(fightService.isTableEmpty()){
            getFights();
//        } else{
//            System.out.println("fights already gathered");
//        }
//        getImages();

    }

//    public void getImages() throws IOException, InterruptedException {
//        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
//        System.setProperty("chrome.driver", "./drivers/chrome.exe");
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
//        WebDriver driver = new ChromeDriver(chromeOptions);
//

//
//
//        int size = Integer.parseInt(driver.findElement(By.cssSelector("div.althelete-total")).getText().split("\\s+")[0]);
//        Scanner scanner = new Scanner(System.in);
//        int curFighter = scanner.nextInt();
//
//        for(int i = curFighter; i < size; i++){
//            int curSize = driver.findElements(By.xpath("(//div[@class='c-listing-athlete-flipcard__front'])")).size();
//            for(int x = i + 1; x < curSize + 1; x++){
//
//                boolean isNull = false;
//
//                WebElement fighterElement = driver.findElement(By.xpath("(//div[@class='c-listing-athlete-flipcard__front'])[" + (i + 1) + "]"));
//                WebElement image = fighterElement;
//                try{
//                    image = fighterElement.findElement(By.xpath("(//div[@class='c-listing-athlete-flipcard__front'])[" + (i + 1) + "]//img"));
//                } catch (RuntimeException e){
//                    isNull = true;
//                }
//
//                if(isNull){
//                    i++;
//                    continue;
//                }
//
//
//                String[] name = driver.findElement(By.xpath("(//div[@class='c-listing-athlete__text']//span[@class='c-listing-athlete__name'])[" + (i + 1) + "]")).getText().split("\\s+");
//                String nickname = driver.findElement(By.xpath("(//div[@class='c-listing-athlete-flipcard__front'])[" + (i + 1) + "]//span[@class='c-listing-athlete__nickname']")).getText().replaceAll("\"","");
//
//                System.out.println("Fighter: " + x + ", " + Arrays.toString(name));
//
//
//                String[] finalFighterName = new String[2];
//                boolean fighterCheck = false;
//
//                Fighter fighter = new Fighter();
//
//                if(name.length > 2){
//                    fighterCheck = true;
//                    StringBuilder lastName = new StringBuilder();
//                    for(int y = 1; y < name.length; y++){
//                        if(y > name.length - 1){
//                            lastName.append(name[y]).append(" ");
//                        } else{
//                            lastName.append(name[y]);
//                        }
//
//                    }
//                    finalFighterName[0] = name[0];
//                    finalFighterName[1] = lastName.toString();
//                }
//
//
//                if (name.length < 2) {
//                    fighter = fighterService.findByFirstName(name[0]);
//                } else if (!fighterCheck && !nickname.isEmpty()){
//                    try{
//                        fighter = fighterService.findByFirstNameAndLastNameAndNickName(name[0], name[1], nickname);
//                    } catch (RuntimeException e){
//                        System.out.println("oops");
//                    }
//                } else if(!fighterCheck){
//                    fighter = fighterService.findByFirstNameAndLastName(name[0], name[1]);
//                }
//                else {
//                    fighter = fighterService.findByFirstNameAndLastName(finalFighterName[0], finalFighterName[1]);
//                }
//
//
//                if(fighter != null) {
//                    String fullName = name[0] + "_" + name[1];
//                    String imageUrl = Objects.requireNonNull(image.getDomProperty("src"));
//                    String destinationFile = "test.jpg";
//
//                    Boolean added = false;
//                    while (!added) {
//
//                        try (InputStream in = new URL(imageUrl).openStream()) {
//                            Files.copy(in, Paths.get(destinationFile));
//
//                            PutObjectResponse putObjectResponse = s3Client.putObject(
//                                    PutObjectRequest.builder()
//                                            .bucket(bucketName)
//                                            .key(fullName)
//                                            .build(),
//                                    RequestBody.fromFile(Paths.get(destinationFile))
//                            );
//
//                            try {
//
//                                String url = "https://ufc-png-bucket.s3.us-east-1.amazonaws.com/" + fullName;
//
//                                fighterService.updatePngUrl(url, fighter.getId());
//                            } catch (Exception e) {
//                                System.out.println("oops");
//                            }
//
//
//                            File file = new File("test.jpg");
//                            file.delete();
//
//                            added = true;
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                i++;
//            }
//            Thread.sleep(1000);
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//            Thread.sleep(1000);
//            boolean clicked = false;
//
//            while(!clicked){
//                try {
//                    driver.findElement(By.xpath("//a[text()='Load More']")).click();
//                    clicked = true;
//                } catch (RuntimeException e){
//                    System.out.println("not clicked");
//                }
//            }
//            Thread.sleep(5000);
//
//            i--;
//        }
//
//        System.out.println("done");
//    }

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
            event.setEventLink(eventLink);

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

    public void getFights() throws IOException, InterruptedException {
        List<Event> events = eventService.getAllEvents();
        Fight lastFight = fightService.getLastFight();
        events.remove(0);
        int size = events.size();

        if(!eventFound && lastFight != null){
            for(int i = 0; i < size; i++){
                System.out.println(events.get(0).getId());
                System.out.println(lastFight.getEvent().getId());
                if(!Objects.equals(events.get(0).getId(), lastFight.getEvent().getId())){
                    events.remove(events.get(0));
                } else{
                    eventFound = true;
                    break;
                }
                if(i == 370){
                    System.out.println("hi");
                }
            }
        }

        WebDriver driver = new ChromeDriver();

        for(int i = 0; i < events.size(); i++) {
            System.out.println(i + " of " + events.size() + " events added");
            driver.get(events.get(i).getEventLink());
            Thread.sleep(1000);
            int totalFights = driver.findElements(By.xpath("//a[@class='b-flag b-flag_style_green']")).size();
            for(int x = 0; i < totalFights; x++){
                List<WebElement> fights = driver.findElements(By.xpath("//a[@class='b-flag b-flag_style_green']"));
                fights.get(x).click();
                Thread.sleep(1000);
                Fight fight = new Fight();
                fight.setEvent(events.get(i));
                fight.setRef(driver.findElement(By.xpath("(//i[@class='b-fight-details__text-item'])[4]//span")).getText());
                fight.setTime(driver.findElement(By.xpath("(//i[@class='b-fight-details__text-item'])[2]")).getText().split("\\s+")[1]);
                fight.setRound(Integer.parseInt(driver.findElement(By.xpath("(//i[@class='b-fight-details__text-item'])[1]")).getText().split("\\s+")[1]));
                fight.setMethod(driver.findElement(By.xpath("//i[@class='b-fight-details__text-item_first']//i[2]")).getText().trim());
                fight.setDetails(driver.findElement(By.xpath("//p[@class='b-fight-details__text'][2]")).getText().split(": ")[1].replace("\"", "").trim());

                String[] winner = driver.findElement(By.cssSelector("i.b-fight-details__person-status_style_green + div a")).getText().split("\\s+");
                String fightWinnerNickname = driver.findElement(By.cssSelector("i.b-fight-details__person-status_style_green + div p")).getText().trim().replace("\"","");
                String[] finalFightWinnerName = new String[2];
                boolean fightWinnerCheck = false;

                if(winner.length > 2){
                    fightWinnerCheck = true;
                    StringBuilder lastName = new StringBuilder();
                    for(int y = 1; y < winner.length; y++){
                        if(y > winner.length - 1){
                            lastName.append(winner[y]).append(" ");
                        } else{
                            lastName.append(winner[y]);
                        }

                    }
                    finalFightWinnerName[0] = winner[0];
                    finalFightWinnerName[1] = lastName.toString();
                }

                Fighter fightWinner = new Fighter();

                if (winner.length < 2) {
                    fightWinner = fighterService.findByFirstName(winner[0]);
                } else if (!fightWinnerCheck){
                    try{
                        fightWinner = fighterService.findByFirstNameAndLastNameAndNickName(winner[0], winner[1], fightWinnerNickname);
                    } catch (RuntimeException e){
                        System.out.println("oops");
                    }
                } else {
                    fightWinner = fighterService.findByFirstNameAndLastNameAndNickName(finalFightWinnerName[0], finalFightWinnerName[1], fightWinnerNickname);
                }
                fight.setFightWinner(fightWinner);



                String[] loser = driver.findElement(By.cssSelector("i.b-fight-details__person-status_style_gray + div a")).getText().split("\\s+");
                String fightLoserNickname = driver.findElement(By.cssSelector("i.b-fight-details__person-status_style_gray + div p")).getText().trim().replace("\"","");
                String[] finalFightLoserName = new String[2];
                boolean fightLoserCheck = false;

                if(loser.length > 2){
                    fightLoserCheck = true;
                    StringBuilder lastName = new StringBuilder();
                    for(int y = 1; y < loser.length; y++){
                        if(y > loser.length - 1){
                            lastName.append(loser[y]).append(" ");
                        } else{
                            lastName.append(loser[y]);
                        }

                    }
                    finalFightLoserName[0] = loser[0];
                    finalFightLoserName[1] = lastName.toString();
                }

                Fighter fightLoser = new Fighter();

                if (loser.length < 2) {
                    fightLoser = fighterService.findByFirstName(loser[0]);
                } else if (!fightLoserCheck){
                    try{
                        fightLoser = fighterService.findByFirstNameAndLastNameAndNickName(loser[0], loser[1], fightLoserNickname);
                    } catch (RuntimeException e){
                        System.out.println("oops");
                    }
                } else {
                    fightLoser = fighterService.findByFirstNameAndLastNameAndNickName(finalFightLoserName[0], finalFightLoserName[1], fightLoserNickname);
                }
                fight.setFightLoser(fightLoser);

                boolean tableExist = false;

                try{
                    driver.findElement(By.xpath("//i[@class='b-fight-details__collapse-left']")).click();
                    driver.findElement(By.xpath("(//i[@class='b-fight-details__collapse-left'])[2]")).click();
                    tableExist = true;
                } catch (RuntimeException e){
                    System.out.println("oops");
                }

                if(tableExist){
                    WebElement totals = driver.findElement(By.xpath("(//table[@class='b-fight-details__table js-fight-table'])[1]"));
                    WebElement sigStrikes = driver.findElement(By.xpath("(//table[@class='b-fight-details__table js-fight-table'])[2]"));
                    String totalTable = "(//table[@class='b-fight-details__table js-fight-table'])[2]";
                    String statTable = "(//table[@class='b-fight-details__table js-fight-table'])[1]";

                    int rounds = driver.findElements(By.xpath("(//table[@class='b-fight-details__table js-fight-table'])[2]//tbody//tr")).size();

                    ArrayList<Round> allRounds = new ArrayList<>();

                    for(int y = 0; y < rounds; y++){
                        WebElement roundElement = driver.findElement(By.xpath("(//table[@class='b-fight-details__table js-fight-table'])[2]//tbody[" + (y + 2) + "]"));
                        String[] fighter1 = roundElement.findElement(By.xpath("//td[1]//p[1]")).getText().split("\\s+");
                        String[] fighter2 = roundElement.findElement(By.xpath("//td[1]//p[2]")).getText().split("\\s+");
                        int win = 0;
                        int lose = 0;

                        if(fighter1[0].equals(fightWinner.getFirstName())){
                            win = 1;
                            lose = 2;
                        } else{
                            win = 2;
                            lose = 1;
                        }

                        Round roundWin = new Round();
                        Round roundLose = new Round();


                        roundWin.setRoundNo(y + 1);
                        roundWin.setFighter(fightWinner);
                        roundWin.setHeadLanded(Integer.parseInt(driver.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 4 + "]//p[" + win + "]")).getText().split("\\s+")[0]));
                        roundWin.setHeadAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 4 + "]//p[" + win + "]")).getText().split("\\s+")[2]));
                        roundWin.setBodyLanded(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 5 + "]//p[" + win + "]")).getText().split("\\s+")[0]));
                        roundWin.setBodyAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 5 + "]//p[" + win + "]")).getText().split("\\s+")[2]));
                        roundWin.setLegLanded(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 6 + "]//p[" + win + "]")).getText().split("\\s+")[0]));
                        roundWin.setLegAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 6 + "]//p[" + win + "]")).getText().split("\\s+")[2]));
                        roundWin.setDistanceLanded(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 7 + "]//p[" + win + "]")).getText().split("\\s+")[0]));
                        roundWin.setDistanceAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 7 + "]//p[" + win + "]")).getText().split("\\s+")[2]));
                        roundWin.setClinchLand(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 8 + "]//p[" + win + "]")).getText().split("\\s+")[0]));
                        roundWin.setClinchAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 8 + "]//p[" + win + "]")).getText().split("\\s+")[2]));
                        roundWin.setGroundLand(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 9 + "]//p[" + win + "]")).getText().split("\\s+")[0]));
                        roundWin.setGround_attempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 9 + "]//p[" + win + "]")).getText().split("\\s+")[2]));

                        roundWin.setTd_land(Integer.parseInt(totals.findElement(By.xpath(statTable + "//tbody[" + (y + 2) + "]//tr[@class='b-fight-details__table-row']//td[6]//p[" + win + "]")).getText().split("\\s+")[0]));
                        roundWin.setTdAttempt(Integer.parseInt(totals.findElement(By.xpath(statTable + "//tbody[" + (y + 2) + "]//tr[@class='b-fight-details__table-row']//td[6]//p[" + win + "]")).getText().split("\\s+")[2]));
                        roundWin.setSubAttempt(Integer.parseInt(totals.findElement(By.xpath(statTable + "//tbody[" + (y + 2) + "]//tr[@class='b-fight-details__table-row']//td[8]//p[" + win + "]")).getText()));
                        roundWin.setControl_time(totals.findElement(By.xpath(statTable + "//tbody[" + (y + 2) + "]//tr[@class='b-fight-details__table-row']//td[10]//p[" + win + "]")).getText());



                        roundLose.setRoundNo(y + 1);
                        roundLose.setFighter(fightLoser);
                        roundLose.setHeadLanded(Integer.parseInt(driver.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 4 + "]//p[" + lose + "]")).getText().split("\\s+")[0]));
                        roundLose.setHeadAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 4 + "]//p[" + lose + "]")).getText().split("\\s+")[2]));
                        roundLose.setBodyLanded(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 5 + "]//p[" + lose + "]")).getText().split("\\s+")[0]));
                        roundLose.setBodyAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 5 + "]//p[" + lose + "]")).getText().split("\\s+")[2]));
                        roundLose.setLegLanded(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 6 + "]//p[" + lose + "]")).getText().split("\\s+")[0]));
                        roundLose.setLegAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 6 + "]//p[" + lose + "]")).getText().split("\\s+")[2]));
                        roundLose.setDistanceLanded(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 7 + "]//p[" + lose + "]")).getText().split("\\s+")[0]));
                        roundLose.setDistanceAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 7 + "]//p[" + lose + "]")).getText().split("\\s+")[2]));
                        roundLose.setClinchLand(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 8 + "]//p[" + lose + "]")).getText().split("\\s+")[0]));
                        roundLose.setClinchAttempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 8 + "]//p[" + lose + "]")).getText().split("\\s+")[2]));
                        roundLose.setGroundLand(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 9 + "]//p[" + lose + "]")).getText().split("\\s+")[0]));
                        roundLose.setGround_attempt(Integer.parseInt(sigStrikes.findElement(By.xpath(totalTable + "//tbody[" + (y + 2) + "]//td[" + 9 + "]//p[" + lose + "]")).getText().split("\\s+")[2]));

                        roundLose.setTd_land(Integer.parseInt(totals.findElement(By.xpath(statTable + "//tbody[" + (y + 2) + "]//tr[@class='b-fight-details__table-row']//td[6]//p[" + lose + "]")).getText().split("\\s+")[0]));
                        roundLose.setTdAttempt(Integer.parseInt(totals.findElement(By.xpath(statTable + "//tbody[" + (y + 2) + "]//tr[@class='b-fight-details__table-row']//td[6]//p[" + lose + "]")).getText().split("\\s+")[2]));
                        roundLose.setSubAttempt(Integer.parseInt(totals.findElement(By.xpath(statTable + "//tbody[" + (y + 2) + "]//tr[@class='b-fight-details__table-row']//td[8]//p[" + lose + "]")).getText()));
                        roundLose.setControl_time(totals.findElement(By.xpath(statTable + "//tbody[" + (y + 2) + "]//tr[@class='b-fight-details__table-row']//td[10]//p[" + lose + "]")).getText());

                        allRounds.add(roundWin);
                        allRounds.add(roundLose);
                    }

                    fightService.postFight(fight);
                    for(Round round: allRounds){
                        round.setFight(fightService.getLastFight());
                        roundService.postRound(round);
                    }
                    if(i != events.size() - 1){
                        driver.get(events.get(i).getEventLink());
                    }
                }

            }



        }
        System.out.println("get fights done");
    }

}
