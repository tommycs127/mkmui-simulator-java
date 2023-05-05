package com.drummish.mkmuisimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeMKMui {
    final ArrayList<Sentence> replies = new ArrayList<>(List.of(
            new Sentence("OK", false, false, "", false, false, true),
            new Sentence("唔想理你", false, false, "", false, false, false),
            new Sentence("dont ff", true, false, "", true, false, false),
            new Sentence("LAAN", true, true, "啦", true, false, true),
            new Sentence("收皮", true, true, "啦", true, false, false),
            new Sentence("食屎", true, true, "啦", true, false, false),
            new Sentence("死開", true, true, "啦", true, false, false),
            new Sentence("躝開", true, true, "啦", true, false, false),
            new Sentence("真係好撚恐怖", true, false, "", false, false, false),
            new Sentence("關我咩事呢", false, true, "", true, true, false),
            new Sentence("關你咩事呢", false, true, "", true, true, false),
            new Sentence("關你哋咩事呢", false, true, "", true, true, false),
            new Sentence("瞓啦", false, true, "", true, false, false)
    ));
    final ArrayList<Sentence> replies_to_empty = new ArrayList<>(List.of(
            new Sentence("唔撚識講嘢？", false, false, "", true, true, false),
            new Sentence("啞撚咗？", false, false, "", true, true, false),
            new Sentence("指令都唔識打就想溝我？", false, false, "", true, true, false)
    ));
    final ArrayList<Sentence> replies_discord = new ArrayList<>(List.of(
            new Sentence(":thumbsup:", false, false, "", false, false, true),
            new Sentence(":thumbsdown:", false, false, "", false, false, true)
    ));
    Random randomGenerator;

    public FakeMKMui() {
        this.randomGenerator = new Random();
    }

    public String reply(Boolean discord) {
        ArrayList<Sentence> all_replies = new ArrayList<>(this.replies);
        if (discord)
            all_replies.addAll(this.replies_discord);
        return all_replies.get(this.randomGenerator.nextInt(all_replies.size())).get();
    }

    public String reply_to_empty() {
        return this.replies_to_empty.get(this.randomGenerator.nextInt(this.replies_to_empty.size())).get();
    }
}
