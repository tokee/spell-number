package dk.ekot.spellnumber;

import junit.framework.TestCase;

/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
public class SpellTest extends TestCase {
    public void testBasic() {
        assertEquals("da 87 should work", "syv-og-firs", Spell.spell("da", 87));
    }

    // https://twitter.com/EveryFinnishNo/status/687257054168399872

    public void testFinnish() {
        final double NUM = 602786;
        final String SPELL = "kuusisataakaksituhattaseitsemänsataakahdeksankymmentäkuusi";
        assertEquals("fi " + NUM + " should work", SPELL, Spell.spell("fi", NUM).replace("\u00AD", ""));
    }

    public void testLongestFinnishTweet() {
        final Spell speller = new Spell("fi");
        int last = 0;
        for (long i = 0 ; i < 10000000000L ; i++) {
            String text = i + " " + speller.format(i).replace("\u00AD", "");
            if (text.length() > last) {
                System.out.println(text + " (" + text.length() + ")");
                last = text.length();
            }
        }
    }

    public void testLongestFinnishTweetGuessingABit() {
        final Spell speller = new Spell("fi");
        int last = 0;
        for (long i = 0 ; i < 10000000000L ; i++) {
            // 77777 seitsemänkymmentäseitsemäntuhattaseitsemänsataaseitsemänkymmentäseitsemän (79)
            long num = i*1000000L + 777777L;
            String text = num + " " + speller.format(num).replace("\u00AD", "");
            if (text.length() > last) {
                System.out.println(text + " (" + text.length() + ")");
                last = text.length();
            }
        }
    }
}