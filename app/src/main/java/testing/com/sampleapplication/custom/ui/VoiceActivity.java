package testing.com.sampleapplication.custom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import testing.com.sampleapplication.R;

public class VoiceActivity extends AppCompatActivity {

    private static final int SPEECH_REQUEST_CODE = 0;


    private TextToSpeech myTTS;

    private SpeechRecognizer mySpeechREcognize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initialiseTTS();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSpeakNow);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Initializing TTS", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
                startActivityForResult(intent, SPEECH_REQUEST_CODE);

            }
        });

        initializeSpeechRecognizer();
    }

    private void initializeSpeechRecognizer() {
        if(SpeechRecognizer.isRecognitionAvailable(this)){
            mySpeechREcognize = SpeechRecognizer.createSpeechRecognizer(this);
            mySpeechREcognize.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    List<String> results = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    processResults(results.get(0));
                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });
        }
    }

    private void processResults(String speechREcognizedResult) {
        speechREcognizedResult = speechREcognizedResult.toLowerCase();
        //what is ur name?
        //what is time now?
        //open browser

        if(speechREcognizedResult.contains("what")){
            if(speechREcognizedResult.contains("your name")){
                speak("my name is sandy");
            }
            if(speechREcognizedResult.contains("time")){
                Date now = new Date();
                String time = DateUtils.formatDateTime(this, now.getTime(), DateUtils.FORMAT_SHOW_TIME);
                speak("The time now is "+time);
            }
        }
    }

    private void initialiseTTS() {
        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(myTTS.getEngines().size() == 0){
                    finish();
                } else {
                    myTTS.setLanguage(Locale.US);
                    speak("I m ready");
                }
            }
        });
    }

    private void speak(String messageToSpeak) {
        myTTS.speak(messageToSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Do something with spokenText
            Toast.makeText(this, spokenText, Toast.LENGTH_SHORT).show();
            processResults(results.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
