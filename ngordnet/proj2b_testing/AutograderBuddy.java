package ngordnet.proj2b_testing;

import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.browser.NgordnetServer;
import ngordnet.main.HistoryHandler;
import ngordnet.main.HistoryTextHandler;
import ngordnet.main.HyponymsHandler;
import ngordnet.main.WordNet;
import ngordnet.ngrams.NGramMap;


public class AutograderBuddy {
    /**
     * Returns a HyponymHandler
     */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        NGramMap ngm = new NGramMap(wordFile, countFile);
        WordNet wn = new WordNet(synsetFile, hyponymFile);

        //NgordnetServer hns = new NgordnetServer();
        //hns.startUp();
        //hns.register("history", new HistoryHandler(ngm));
        //hns.register("historytext", new HistoryTextHandler(ngm));
        //hns.register("hyponyms", new HyponymsHandler(wn));
        return new HyponymsHandler(wn, ngm);
    }
}
