package eti.mecka.franciszek.project.cmd;

import com.fasterxml.jackson.databind.ObjectWriter;
import eti.mecka.franciszek.project.player.entity.Player;
import eti.mecka.franciszek.project.player.service.api.OrganizationService;
import eti.mecka.franciszek.project.player.service.api.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ApplicationCommand implements CommandLineRunner {


    private final PlayerService playerService;

    public ApplicationCommand(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;

        main_loop: while (true) {
            command = scanner.next();
            switch (command) {
                case "get_players" -> {
                    playerService.findAll().forEach(System.out::println);
                }
                case "get_organization_players" -> {
                    UUID uuid = UUID.fromString(scanner.next());
                    try {
                        playerService.findAllByOrganization(uuid).ifPresent(System.out::println);
                    } catch (NoSuchElementException ex){
                        System.out.println("NOT_FOUND");
                    }
                }
                case "get_player" -> {
                    UUID uuid = UUID.fromString(scanner.next());
                    try {
                        playerService.find(uuid);
                    } catch (NoSuchElementException ex){
                        System.out.println("NOT_FOUND");
                    }
                }
                case "delete_player" -> {
                    try {
                        UUID uuid = UUID.fromString(scanner.next());
                        playerService.delete(uuid);
                    } catch (NoSuchElementException ex){
                        System.out.println("NOT_FOUND");
                    }
                }
                case "put_player" -> {
                    try {
                        Player player = Player.builder()
                                .id(UUID.fromString(scanner.next()))
                                .first_name(scanner.next())
                                .surname(scanner.next())
                                .nationality(scanner.next())
                                .jerseyNumber(scanner.nextInt())
                                .age(scanner.nextInt())
                                .height(scanner.nextInt())
                                .weight(scanner.nextFloat())
                                .build();
                        playerService.create(player);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Bad Request");
                    }
                }
                case "quit" -> {
                    break main_loop;
                }
            }
        }
    }
}
