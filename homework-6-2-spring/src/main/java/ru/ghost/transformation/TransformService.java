package ru.ghost.transformation;

import org.springframework.stereotype.Service;
import ru.ghost.domain.Butterfly;
import ru.ghost.domain.Caterpillar;

@Service
public class TransformService {
    public Butterfly hutch(Caterpillar caterpillar) throws Exception {
        System.out.println("Caterpillar " + caterpillar.getNumber());
        Thread.sleep(30);
        System.out.println("The transformation of a butterfly from a caterpillar " + caterpillar.getNumber() + " done");
        return new Butterfly("New Butterfly " + caterpillar.getNumber());
    }
}
