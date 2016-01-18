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
package dk.ekot.spellnumber;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;


public class Spell {
    private final ULocale locale;
    private final NumberFormat formatter;

    public Spell(String locale) {
        this.locale = new ULocale(locale);
        this.formatter = new RuleBasedNumberFormat(this.locale, RuleBasedNumberFormat.SPELLOUT);
    }

    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            usage();
            return;
        }
        System.out.println(args[0] + " " + spell(args[0], Double.parseDouble(args[1])));
    }

    public static String spell(String localeStr, double number) {
        return new Spell(localeStr).format(number);
    }

    private static void usage() {
        System.out.println("Usage:   Spell <locale> <number>");
        System.out.println("Example: Spell da 87");
    }

    public String format(double number) {
        return formatter.format(number);
    }

}
