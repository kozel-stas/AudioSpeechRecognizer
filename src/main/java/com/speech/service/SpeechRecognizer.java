package com.speech.service;

import com.speech.rest.model.Language;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;

public interface SpeechRecognizer {

    String recognize(InputStream inputStream, Language language) throws Exception;

}
