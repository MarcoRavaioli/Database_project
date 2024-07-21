package unibo.sportcentermanager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import unibo.sportcentermanager.view.authentication.RoleSelectionFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import javax.swing.*;

@SpringBootApplication
public class SportCenterApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    public static void main(final String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(SportCenterApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final RoleSelectionFrame roleSelectionFrame = context.getBean(RoleSelectionFrame.class);
                roleSelectionFrame.setVisible(true);
            }
        });
    }
}
