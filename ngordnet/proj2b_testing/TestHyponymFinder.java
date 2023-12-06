package ngordnet.proj2b_testing;

import ngordnet.main.WordNet;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class TestHyponymFinder {
    @Test
    public void testHyponymsSimple(){
        WordNet wn=new WordNet("./data/wordnet/synsets11.txt","./data/wordnet/hyponyms11.txt");
        assertThat(wn.getHyponym("antihistamine")).isEqualTo(Set.of("antihistamine","actifed"));
    }

    @Test
    public void testHyponymsLessSimple(){
        WordNet wn=new WordNet("./data/wordnet/synsets16.txt","./data/wordnet/hyponyms16.txt");
        assertThat(wn.getHyponym("change")).isEqualTo(Set.of("alteration", "change", "demotion", "increase", "jump", "leap", "modification", "saltation", "transition", "variation"));
    }

    @Test
    public void testHyponymsList() {
        WordNet wn = new WordNet("./data/wordnet/synsets16.txt", "./data/wordnet/hyponyms16.txt");
        assertThat(wn.getHyponym(List.of("change", "occurrence"))).isEqualTo(Set.of("alteration", "change", "increase", "jump", "leap", "modification", "saltation", "transition"));
    }

    @Test
    public void testHyponymsList2() {
        WordNet wn = new WordNet("./data/wordnet/synsets16.txt", "./data/wordnet/hyponyms16.txt");
        assertThat(wn.getHyponym(List.of("change", "occurrence", "transition"))).isEqualTo(Set.of("jump", "leap", "saltation", "transition"));
    }

    @Test
    public void testHyponymsList3() {
        WordNet wn = new WordNet("./data/wordnet/synsets16.txt", "./data/wordnet/hyponyms16.txt");
        assertThat(wn.getHyponym(List.of("change", "occurrence", "transition", "demotion"))).isEmpty();
    }


}
