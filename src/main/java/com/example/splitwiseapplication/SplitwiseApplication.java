package com.example.splitwiseapplication;

import com.example.splitwiseapplication.commands.CommandExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {

    Scanner sc = new Scanner(System.in);
    CommandExecutor commandExecutor = new CommandExecutor();

    public static void main(String[] args) {

        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while(true){
            String input = sc.next();
            commandExecutor.executeCommand(input);
        }


    }
}
