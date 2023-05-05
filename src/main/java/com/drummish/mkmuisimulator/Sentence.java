package com.drummish.mkmuisimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Sentence {
    String content, particle;
    Boolean prefix, suffix, no_measure, no_pronoun, no_emoji;
    final ArrayList<Name>
            names = new ArrayList<>(List.of(
                    new Name("毒撚", "個", "啲"),
                    new Name("死毒撚", "個", "啲"),
                    new Name("柒頭", "條", "啲"),
                    new Name("on9", "條", "啲"),
                    new Name("單身狗", "隻", "啲"),
                    new Name("處男", "個", "啲"),
                    new Name("青頭仔", "個", "啲"),
                    new Name("變態佬", "個", "啲"),
                    new Name("死變態佬", "個", "啲")
            )),
            name_pronouns = new ArrayList<>(List.of(
                    new Name("你"),
                    new Name("你哋")
            ));
    final ArrayList<String> emoji = new ArrayList<>(List.of("7.7", "7.777", "：）", "：（", "</3"));
    Random randomGenerator;

    public Sentence(String content, Boolean prefix, Boolean suffix, String particle,
                    Boolean no_measure, Boolean no_pronoun, Boolean no_emoji) {
        this.init(content, prefix, suffix, particle, no_measure, no_pronoun, no_emoji);
    }

    private void init(String content, Boolean prefix, Boolean suffix, String particle,
                 Boolean no_measure, Boolean no_pronoun, Boolean no_emoji) {
        this.content = content;
        this.prefix = prefix;
        this.suffix = suffix;
        this.particle = particle;
        this.no_measure = no_measure;
        this.no_pronoun = no_pronoun;
        this.no_emoji = no_emoji;

        this.randomGenerator = new Random();
    }

    private Boolean flip_coin() {
        return this.randomGenerator.nextInt(2) == 1;
    }

    private Boolean isFullWidth(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
                || block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D
                || block == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || block == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT;
    }

    private Boolean isHalf2Half(String s1, String s2) {
        return !this.isFullWidth(s1.charAt(s1.length() - 1)) && !this.isFullWidth(s2.charAt(0));
    }

    public String get() {
        String final_content = this.content;

        ArrayList<Name> name_list = new ArrayList<>(this.names);
        if (!this.no_pronoun)
            name_list.addAll(this.name_pronouns);

        int index = this.randomGenerator.nextInt(name_list.size());
        String name_ = name_list.get(index).get(this.no_measure);

        if (this.suffix && this.flip_coin()) {
            if (!Objects.equals(this.particle, ""))
                final_content += this.particle;

            if (this.isHalf2Half(final_content, name_))
                final_content += " ";

            final_content += name_;
        }
        else if (this.prefix) {
            final_content = name_;

            if (this.isHalf2Half(name_, final_content))
                final_content += " ";

            final_content += this.content;
        }

        if (!this.no_emoji && this.flip_coin()) {
            index = this.randomGenerator.nextInt(emoji.size());
            String emoji_ = this.emoji.get(index);

            if (this.isHalf2Half(final_content, emoji_))
                final_content += " ";

            final_content += emoji_;
        }

        return final_content;
    }
}
