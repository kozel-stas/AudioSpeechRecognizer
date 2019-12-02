package com.speech.service;

import com.speech.rest.model.Language;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

@Service
public class AudioSpeechRecognizer implements SpeechRecognizer {

    private final Configuration configuration;
    private final GenericObjectPool<StreamSpeechRecognizer> pool;

    public AudioSpeechRecognizer(Configuration configuration) {
        this.configuration = configuration;
        pool = new GenericObjectPool<>(new PooledObjectFactory<StreamSpeechRecognizer>() {
            @Override
            public PooledObject<StreamSpeechRecognizer> makeObject() throws Exception {
                return new DefaultPooledObject<>(new StreamSpeechRecognizer(configuration));
            }

            @Override
            public void destroyObject(PooledObject<StreamSpeechRecognizer> pooledObject) throws Exception {
            }

            @Override
            public boolean validateObject(PooledObject<StreamSpeechRecognizer> pooledObject) {
                return pooledObject.getObject().getResult() == null;
            }

            @Override
            public void activateObject(PooledObject<StreamSpeechRecognizer> pooledObject) throws Exception {

            }

            @Override
            public void passivateObject(PooledObject<StreamSpeechRecognizer> pooledObject) throws Exception {
            }
        });
    }

    @Override
    public String recognize(InputStream inputStream, Language language) throws Exception {
        if (language != Language.EN) {
            throw new UnsupportedOperationException("EN language supported only.");
        }
        StreamSpeechRecognizer streamSpeechRecognizer = pool.borrowObject();
        try {
            AudioFormat target = new AudioFormat(16000f, 16, 1, true, false);
            AudioInputStream is = AudioSystem.getAudioInputStream(target, AudioSystem.getAudioInputStream(inputStream));
            streamSpeechRecognizer.startRecognition(is);
            String result = streamSpeechRecognizer.getResult().getHypothesis();
            streamSpeechRecognizer.stopRecognition();
            return result;
        } finally {
            pool.returnObject(streamSpeechRecognizer);
        }
    }

}