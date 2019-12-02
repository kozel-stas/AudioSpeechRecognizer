package com.speech.rest;

import com.speech.rest.model.Language;
import com.speech.rest.model.SpeechRecognizeResult;
import com.speech.service.SpeechRecognizer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/speech")
public class SpeechController {

    private final SpeechRecognizer speechRecognizer;

    public SpeechController(SpeechRecognizer speechRecognizer) {
        this.speechRecognizer = speechRecognizer;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/audio",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public SpeechRecognizeResult audioRecognize(@RequestParam("lang") Language language, @RequestBody MultipartFile audioFile) throws Exception {
        return new SpeechRecognizeResult(speechRecognizer.recognize(new ByteArrayInputStream(audioFile.getBytes()), language), language);
    }

}
