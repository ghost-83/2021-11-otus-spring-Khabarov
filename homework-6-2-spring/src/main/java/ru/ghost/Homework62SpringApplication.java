package ru.ghost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.ghost.domain.Butterfly;
import ru.ghost.domain.Caterpillar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@ComponentScan
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class Homework62SpringApplication {

    @MessagingGateway
    public interface ButterflyCaterpillarGateway {

        @Gateway(requestChannel = "caterpillarChannel", replyChannel = "butterflyChannel")
        Collection<Butterfly> process(Collection<Caterpillar> caterpillars);
    }


    @Bean
    public PublishSubscribeChannel butterflyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(20).get();
    }

    @Bean
    public IntegrationFlow butterflyFlow() {
        return IntegrationFlows.from("caterpillarChannel")
                .split()
                .handle("transformService", "hutch")
                .aggregate()
                .channel("butterflyChannel")
                .get();
    }

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Homework62SpringApplication.class);

        ButterflyCaterpillarGateway butterflyEggGateway = ctx.getBean(ButterflyCaterpillarGateway.class);

        ForkJoinPool pool = ForkJoinPool.commonPool();

        while (true) {
            Thread.sleep(5000);

            pool.execute(() -> {
                Collection<Caterpillar> caterpillars = generateCaterpillars();

                System.out.println("New tracks: " +
                        caterpillars.stream()
                                .map(Caterpillar::getNumber)
                                .map(serial -> Integer.toString(serial))
                                .collect(Collectors.joining(",")) + ", total = " + caterpillars.size());

                Collection<Butterfly> butterflies = butterflyEggGateway.process(caterpillars);

                System.out.println(
                        "Total tracks = " + caterpillars.size() + ", total butterflies = " + butterflies.size()
                                + ". Butterfly: " + butterflies
                                .stream()
                                .map(Butterfly::getName)
                                .collect(Collectors.joining(",")));
            });
        }
    }


    private static Collection<Caterpillar> generateCaterpillars() {
        List<Caterpillar> caterpillars = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            caterpillars.add(new Caterpillar(i));
        }
        return caterpillars;
    }
}
