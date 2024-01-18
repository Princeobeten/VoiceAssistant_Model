import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Configuration config =new Configuration();

        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us"); // voices command
        // the parameter's found in the objects below, are given by converting our commands.txt file to a .dic & .lm
        // on this website = http://www.speech.cs.cmu.edu/tools/
        config.setDictionaryPath("src\\main\\resources\\dictionary.dic"); // commands
        config.setLanguageModelPath("src\\main\\resources\\languageModel.lm"); // language model

        // getting voices commands
        try {
            LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
            speech.startRecognition(true); // starting the recognition

            SpeechResult speechResult = null;

            String voiceCommand;
            while ((speechResult = speech.getResult()) != null) {
                voiceCommand = speechResult.getHypothesis();
                System.out.println("Voice Command is " + voiceCommand);

                if (voiceCommand.equalsIgnoreCase("Open Chrome")) {// checking is command
                    Runtime.getRuntime().exec("cmd.exe /c start chrome www.google.com"); // caring out 1st the command
                } else if (voiceCommand.equalsIgnoreCase("Close Chrome")) {
                    Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe"); // caring out 2nd the command}
                }
            }
        }catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
