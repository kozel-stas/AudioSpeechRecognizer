package com.speech.rest.model;

public class SpeechRecognizeResult {

    private final String text;
    private final Language language;

    public SpeechRecognizeResult(String text, Language language) {
        this.text = text;
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public String getText() {
        return text;
    }

}
