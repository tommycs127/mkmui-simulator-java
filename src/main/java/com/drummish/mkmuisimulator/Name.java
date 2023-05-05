package com.drummish.mkmuisimulator;

import java.util.Random;

public class Name {
    String content, classifier_single, classifier_multiple;
    Random randomGenerator;

    public Name(String content) {
        this.content = content;
        this.randomGenerator = new Random();
    }

    public Name(String content, String classifier_single, String classifier_multiple) {
        this.content = content;
        this.classifier_single = classifier_single;
        this.classifier_multiple = classifier_multiple;
        this.randomGenerator = new Random();
    }

    private Boolean flip_coin() {
        return this.randomGenerator.nextInt(2) == 1;
    }

    public String get(Boolean no_measure) {
        if (!no_measure)
            if (this.classifier_multiple != null && flip_coin())
                if (flip_coin())
                    return "你哋呢" + this.classifier_multiple + this.content;
                else
                    return this.classifier_multiple + this.content;
            else if (this.classifier_single != null && flip_coin())
                return "你呢" + this.classifier_single + this.content;
        return this.content;
    }
}
