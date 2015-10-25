import java.util.List;
import java.util.ArrayList;

public class Summarization
{
    String text;
    int numWords;
    List frequency;
    List words;
    int numWordCount;
    int numSentences;
    String[] sentences;
    int[] sentenceRankings;
    String[] notEnd;
    
    public static void main() {
        Summarization s = new Summarization("Biology is a natural science concerned with the study of life and living organisms, including their structure, function, growth, evolution, distribution, and taxonomy.[1] Modern biology is a vast and eclectic field, composed of many branches and subdisciplines. However, despite the broad scope of biology, there are certain general and unifying concepts within it that govern all study and research, consolidating it into single, coherent fields. In general, biology recognizes the cell as the basic unit of life, genes as the basic unit of heredity, and evolution as the engine that propels the synthesis and creation of new species. It is also understood today that all organisms survive by consuming and transforming energy and by regulating their internal environment to maintain a stable and vital condition. Subdisciplines of biology are defined by the scale at which organisms are studied, the kinds of organisms studied, and the methods used to study them: biochemistry examines the rudimentary chemistry of life; molecular biology studies the complex interactions among biological molecules; botany studies the biology of plants; cellular biology examines the basic building-block of all life, the cell; physiology examines the physical and chemical functions of tissues, organs, and organ systems of an organism; evolutionary biology examines the processes that produced the diversity of life; and ecology examines how organisms interact in their environment.");
    }
    
    /**
     * Constructor for objects of class Summarization
     */
    public Summarization(String str)
    {
        text = str;
        numWords = 0;
        frequency = new ArrayList();
        words = new ArrayList();
        notEnd = new String[]{"Dr", "Mr", "Ms", "Mrs"};
        wordCount();
        words();
        phrases();
        screen();
        countSentences();
        setSentences();
        rankSentences();
        print(shorten(getTopSentences()));
    }

    private int wordCount() {
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == ' ') {
                numWords++;
            }
        }
        if(text.charAt(text.length()-1) != ' ') {
            numWords++;
        }
        return numWords;
    }
    
    private void words() {
        int wordStart = 0;
        int wordEnd = 0;
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == ' ') {
                wordEnd = i;
                String word = createWord(text.substring(wordStart, wordEnd).toLowerCase());
                if(isNotCommonWord(word) && words.contains(word)) {
                    int index = words.indexOf(word);
                    Integer num = (Integer)frequency.get(index);
                    int freq = num.intValue();
                    frequency.remove(index);
                    if(index <= frequency.size()) {
                        frequency.add(index, new Integer(freq + 1));
                    } else {
                        frequency.add(new Integer(freq + 1));
                    }
                } else if(isNotCommonWord(word)) {
                    words.add(word);
                    frequency.add(new Integer(1));
                }
                wordStart = wordEnd + 1;
            }
        }
    }
    
    private void phrases() {
        int wordStart = 0;
        int wordEnd;
        int phraseStart = 0;
        int phraseEnd;
        String phrase;
        boolean phraseInit = false;
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == ' ' || i == text.length()-1) {
                if(i == text.length()-1)
                    wordEnd = i + 1;
                else 
                    wordEnd = i;
                String raw = text.substring(wordStart, wordEnd).toLowerCase();
                String word = createWord(raw);
                if(isNotCommonWord(word) && !phraseInit) {
                    phraseStart = i - raw.length();
                    phraseInit = true;
                } else if(!isNotCommonWord(word) && phraseInit) {
                    phraseEnd = i -1 - raw.length();
                    phrase = text.substring(phraseStart, phraseEnd).toLowerCase();
                    if(words.contains(phrase) && isPhrase(phrase)) {
                        int index = words.indexOf(phrase);
                        Integer num = (Integer)frequency.get(index);
                        int freq = num.intValue();
                        frequency.remove(index);
                        if(index <= frequency.size()) {
                            frequency.add(index, new Integer(freq + 1));
                        } else {
                            frequency.add(new Integer(freq + 1));
                        }
                    } else if(isPhrase(phrase)) {
                        words.add(phrase);
                        frequency.add(new Integer(1));
                    }
                    phraseInit = false;
                }
                wordStart = wordEnd + 1;
            }
        }
    }
    
    private void screen() {
        for(int i = frequency.size()-1; i >= 0; i--) {
            Integer num = (Integer)frequency.get(i);
            if(num.intValue() <= 2 ) {
                frequency.remove(i);
                words.remove(i);
            }
        }
    }
    
    private String createWord(String s) {
        String word = "";
        for(int i = 0; i < s.length(); i++) {
            if(isValid(s.charAt(i))) {
                word += s.charAt(i);
            }
        }
        return word;
    }
    
    private boolean isValid(char c) {
        return Character.isLetter(c);
    }
    
    private boolean isNotCommonWord(String s) {
        return s.length() > 4;
    }
    
    public String getText() {
        return text;
    }
    
    public String[] getKeyTerms() {
        String[] terms = (String[])words.toArray();
        return terms;
    }
    
    private boolean isPhrase(String s) {
        boolean hasSpace = false;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                hasSpace = true;
            } else if(!Character.isLetter(s.charAt(i))) {
                return false;
            }
        }
        return hasSpace;
    }
    
    private void countSentences() {
        int startIndex = 0;
        int index = 0;
        boolean isEnding = true;
        while(index != -1) {
            index = text.indexOf(".", startIndex);
            if(index > 0 && !Character.isUpperCase(text.charAt(index - 1))) {
                if(index == 1) {
                    if(isEnd(index)) {
                        numSentences++;
                        startIndex = index+1;
                    }
                } else if(index == 2) {
                    for(int i = 0; i < 3; i++) {
                        if(text.substring(index-2, index).equals(notEnd[i]))
                        isEnding =  false;
                    }
                    if(isEnding && isEnd(index)) {
                        numSentences++;
                        startIndex = index+1;
                    }
                } else if(index  >= 3) {
                    for(int i = 0; i < 4; i++) {
                        if(text.substring(index-notEnd[i].length(), index).equals(notEnd[i]))
                        isEnding =  false;
                    }
                    if(isEnding && isEnd(index)) {
                        numSentences++;
                        startIndex = index+1;
                    }
                }
            }
            startIndex = index+1;
        }
    }
    
    private void setSentences() {
        sentences = new String[numSentences];
        sentenceRankings = new int[numSentences];
        int startIndex = 0;
        int index = -1;
        boolean isEnding = true;
        for(int h = 0; h < numSentences; h++) {
            index = text.indexOf(".", index+1);
            if(index > 0 && !Character.isUpperCase(text.charAt(index - 1))) {
                if(index == 1) {
                    if(isEnd(index)) {
                        sentences[h] = text.substring(startIndex, index);
                        startIndex = index+1;
                    } else
                    h--;
                } else if(index == 2) {
                    for(int i = 0; i < 3; i++) {
                        if(text.substring(index-2, index).equals(notEnd[i])) {
                            isEnding =  false;
                        }
                    }
                    if(isEnding && isEnd(index)) {
                        sentences[h] = text.substring(startIndex, index);
                        startIndex = index+1;
                    } else
                    h--;
                } else if(index  >= 3) {
                    for(int i = 0; i < 4; i++) {
                        if(text.substring(index-notEnd[i].length(), index).equals(notEnd[i])) {
                            isEnding =  false;
                            h--;
                        }
                    }
                    if(isEnding && isEnd(index)) {
                        sentences[h] = text.substring(startIndex, index);
                        startIndex = index+1;
                    } else
                    h--;
                }
            }
        }
    }
    
    private boolean isEnd(int index) {
        if(index == text.length()-1) {
            return true;
        } else if(index == text.length()-2 && text.charAt(index+1) == ' ') {
            return true;
        } else if(text.charAt(index+1) == ' ' && Character.isUpperCase(text.charAt(index+2))) {
            return true;
        } else {
            return false;
        }
    }
    
    private void rankSentences() {
        for(int i = 0; i < numSentences; i++) {
            sentenceRankings[i] = 0;
            for(int h = 0; h < words.size(); h++) {
                int index = -1;
                index = sentences[i].indexOf((String)(words.get(h)));
                while(index != -1) {
                    sentenceRankings[i]++;
                    index = sentences[i].indexOf((String)(words.get(h)), index+1);
                }
            }
        }
    }
    
    private String[] getTopSentences() {
        String[] topSentences = new String[5];
        if(numSentences > 5) {
            for(int i = 0; i < 5; i++) {
                int greatest = 0;
                int greatestIndex = 0;
                for(int h = 0; h < numSentences; h++) {
                    if(sentenceRankings[h] > greatest) {
                        greatest = sentenceRankings[h];
                        greatestIndex = h;
                    }
                }
                topSentences[i] = sentences[greatestIndex];
                sentenceRankings[greatestIndex] = 0;
            }
        } else {
            return sentences;
        }
        return topSentences;
    }
    
    
    private String[] shorten(String[] sent) {
        String[] ret = new String[sent.length];
        for(int h = 0; h < sent.length; h++) {
            ret[h] = "";
            int wordStart = 0;
            int wordEnd = 0;
            for(int i = 0; i < sent[h].length(); i++) {
                if(sent[h].charAt(i) == ' ' || sent[h].charAt(i) == '.' || sent[h].charAt(i) == ';' || sent[h].charAt(i) == ',') {
                    wordEnd = i;
                    String raw = sent[h].substring(wordStart, wordEnd);
                    String word = createWord(raw.toLowerCase());
                    if(word.equals("a")) {
                        
                    } else if(word.equals("and")) {
                        ret[h] += "&";
                    } else if(word.equals("to")) {
                        ret[h] += "2";
                    } else if(word.equals("for")) {
                        ret[h] += "4";
                    } else if(word.equals("are")) {
                        ret[h] += "r";
                    } else if(word.equals("with")) {
                        ret[h] += "w/";
                    } else {
                        ret[h] += raw;
                    }
                    ret[h] += sent[h].charAt(i);
                    wordStart = wordEnd + 1;
                }
            }
        }
        return ret;
    }
    
    private void print(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
    
}


