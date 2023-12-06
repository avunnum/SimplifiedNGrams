package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {

    private WordNet wn;
    private NGramMap ngm;

    public HyponymsHandler(WordNet wn, NGramMap ngm) {
        this.wn = wn;
        this.ngm = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        int k = q.k();
        int startYear = q.startYear();
        int endYear = q.endYear();
        List<String> words = q.words();
        Set<String> allHyponyms = wn.getHyponym(words);
        if (k == 0) {
            return allHyponyms.toString();
        }
        TreeMap<Double, String> allWords = new TreeMap<>();
        for (String word : allHyponyms) {
            TimeSeries wordCounts = ngm.countHistory(word, startYear, endYear);
            double totalWordCount = wordCounts.data().stream().mapToDouble(Double::doubleValue).sum();
            allWords.put(totalWordCount, word);
        }
        Set<String> kPopularWords = new TreeSet<>();
        for (Map.Entry<Double, String> entry : allWords.descendingMap().entrySet()) {
            if (k == 0 || entry.getKey().equals(0.0)) {
                break;
            }
            kPopularWords.add(entry.getValue());
            k--;
        }
        return kPopularWords.toString();
    }
}
