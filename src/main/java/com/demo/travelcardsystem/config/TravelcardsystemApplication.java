package com.demo.travelcardsystem.config;

import com.demo.travelcardsystem.businessrule.RuleCollection;
import com.demo.travelcardsystem.businessrule.TravelStrategy;
import com.demo.travelcardsystem.constant.Zone;
import com.demo.travelcardsystem.entity.Station;
import com.demo.travelcardsystem.entity.TravelCard;
import com.demo.travelcardsystem.entity.TravelCardObserver;
import com.demo.travelcardsystem.repository.InMemoryCardTransactionRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.demo.travelcardsystem"})
public class TravelcardsystemApplication{
    public static Set<Station> stations;

    public static void main(String[] args) {
        SpringApplication.run(TravelcardsystemApplication.class, args);
    }

    @Bean
    public TravelCard getTravelCard(TravelCardObserver travelCardObserver) {
        TravelCard travelCard = new TravelCard();
        travelCard.registerObserver(travelCardObserver);
        return travelCard;
    }

    @Bean
    public RuleCollection loadAllTravelStrategy(TravelStrategy travelStrategy) {
       return travelStrategy.loadAllBusinessRules();
    }

 
    @Bean
    public  Boolean loadAllStation(InMemoryCardTransactionRepository inMemoryCardTransactionRepository) {
        stations = new HashSet<>();

        //ADD Algubaiba
        stations.add(new Station("Algubaiba", new HashSet<>(Arrays.asList(Zone.ONE))));
        //ADD Jumeirah
        stations.add(new Station("Jumeirah", new HashSet<>(Arrays.asList(Zone.ONE, Zone.TWO))));
        //ADD Bur Dubai
        stations.add(new Station("Bur Dubai", new HashSet<>(Arrays.asList(Zone.THREE))));
        //ADD Deirah
        stations.add(new Station("Deirah", new HashSet<>(Arrays.asList(Zone.TWO))));

        return inMemoryCardTransactionRepository.addAllStationsToStationStore(stations);
    }

    @Bean
    public Boolean loadInitialCards(InMemoryCardTransactionRepository inMemoryCardTransactionRepository) {
        TravelCard firstTravelCard = new TravelCard();
        firstTravelCard.setCardNumber("A101");
        firstTravelCard.setBalance(30); 

        TravelCard secondTravelCard = new TravelCard();
        secondTravelCard.setCardNumber("B201");
        secondTravelCard.setBalance(50);

        inMemoryCardTransactionRepository.registerNewCard(firstTravelCard);
        inMemoryCardTransactionRepository.registerNewCard(secondTravelCard);

        return true;
    }

    /*
     public Boolean loadInitialCards(InMemoryCardTransactionRepository inMemoryCardTransactionRepository) {
        TravelCard firstTravelCard = createTravelCard("A101", 30);
        TravelCard secondTravelCard = createTravelCard("B201", 50);

        registerTravelCard(inMemoryCardTransactionRepository, firstTravelCard);
        registerTravelCard(inMemoryCardTransactionRepository, secondTravelCard);

        return true;
    }

    private TravelCard createTravelCard(String cardNumber, int balance) {
        TravelCard travelCard = new TravelCard();
        travelCard.setCardNumber(cardNumber);
        travelCard.setBalance(balance);
        return travelCard;
    }

    private void registerTravelCard(InMemoryCardTransactionRepository inMemoryCardTransactionRepository, TravelCard travelCard) {
        inMemoryCardTransactionRepository.registerNewCard(travelCard);
    }

     */


}
