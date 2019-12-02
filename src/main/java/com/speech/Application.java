package com.speech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        Configuration configuration = new Configuration();
//
//        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
//        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
//        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
//
//        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
//
//        recognizer.startRecognition(true);
//        SpeechResult result;
//        while ((result = recognizer.getResult()) != null) {
//            System.out.format("Hypothesis: %s\n", result.getHypothesis());
//        }
//        recognizer.stopRecognition();
        SpringApplication.run(Application.class);
    }

}
