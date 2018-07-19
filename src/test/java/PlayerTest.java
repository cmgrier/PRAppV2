import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class PlayerTest {
    ArrayList<String> characters = new ArrayList<>();
    Player p = new Player("Bro",1,characters);

    @Test
    public void testCharacterString(){
        characters.add("Cloud");
        characters.add("PacMan");
        assertEquals(p.characterString(), "Cloud.PacMan");
    }
}
