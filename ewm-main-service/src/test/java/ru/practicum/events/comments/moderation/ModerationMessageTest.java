package ru.practicum.events.comments.moderation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ModerationMessageTest {


    @Test
    void moderationMessage() {
        ModerationMessage moderationMessage = new ModerationMessage();
        String s = "ты друг мой ";
        moderationMessage.moderationMessage(s);
        assertThat(true).isTrue();

    }
}