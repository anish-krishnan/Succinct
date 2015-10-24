import java.util.List;
import java.util.ArrayList;


/**
 * Write a description of class KeyWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KeyWord
{
    String text;
    int numWords;
    List frequency;
    List words;
    int numWordCount;
    
    /**
     * Constructor for objects of class KeyWord
     */
    public KeyWord(String page)
    {
        text = page;
        numWords = 0;
        frequency = new ArrayList();
        words = new ArrayList();
        numWordCount = wordCount();
        words();
        phrases();
        screen();
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
    
    
    
    private String findPreviousWord(int i) {
        for(int h = i - 2; h > 0; h--) {
            if(h == 0 || text.charAt(h-1) == ' ') {
                return createWord(text.substring(h, i-1));
            }
        }
        return "";
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
    
    private boolean hasPreviousWord(int i) {
        if(i <= 1 || !Character.isLetter(text.charAt(i-2))) {
            return false;
        }
        return true;
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
    
    private void printTerms() {
        for(int i = 0; i < words.size(); i++) {
            Integer num = (Integer)frequency.get(i);
            System.out.println(words.get(i) + " " + num.intValue());
        }
    }
}
