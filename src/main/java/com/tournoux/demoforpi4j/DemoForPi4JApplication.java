package com.tournoux.demoforpi4j;

import com.tournoux.demoforpi4j.pi4j.Pi4jMinimalBT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoForPi4JApplication {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Pi4jMinimalBT gpioService;
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DemoForPi4JApplication.class);
        SpringApplication app = new SpringApplication(DemoForPi4JApplication.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }




    @Bean
    CommandLineRunner init() {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                gpioService.initializeService();
                try{
                    gpioService.TurnLockOn();
                    gpioService.TurnChan1On();
                    gpioService.TurnChan2On();
                    gpioService.TurnChan4On();
                    Thread.sleep(1000L);
                    gpioService.TurnLockOff();
                    gpioService.TurnChan1Off();
                    gpioService.TurnChan2Off();
                    gpioService.TurnChan4Off();
                    Thread.sleep(1000L);
                    gpioService.TurnLockOn();
                    gpioService.TurnChan1On();
                    gpioService.TurnChan2On();
                    gpioService.TurnChan4On();
                    Thread.sleep(1000L);
                }catch(Exception e){

                    logger.error("Error trying to toggle LED.",e);
                }
            }
        };
    }
}
