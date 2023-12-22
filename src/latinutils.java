package me.arthurmeade12.conjugator;
import java.util.Scanner;
public class latinutils {
  public static boolean checks(String[] verb) {
    String bad_fourth = "Perfect passive participles must be first-second (ending in -us)";
    if (latinutils.is_deponent(verb[1])) {
      if (!(verb[0].endsWith("or"))) {
        throw new BadLatinException("First principle part of deponent must end in \"or\".");
      }
      if (!(verb[1].endsWith("i"))) {
        throw new BadLatinException("Deponent infinitives must end in \"i\".");
      }
      if (!(verb[2].equals("-"))) {
        throw new BadLatinException("Deponents do not have perfect active stems.");
      }
      if (!(verb[3].endsWith("us"))) {
        throw new BadLatinException(bad_fourth);
      }
      return true;
    } else {
      // non-deponent
      if (!(verb[0].endsWith("o"))) {
        throw new BadLatinException("Active first principle part must end in \"o\".");
      }
      if (!(verb[1].endsWith("re"))) {
        throw new BadLatinException("Active infinitives of all conjugations must end in \"re\".");
      }
      if (!(verb[2].endsWith("i"))) {
        throw new BadLatinException("The perfect active must end in \"i\".");
      }
      if (!(verb[3].endsWith("us") || verb[3] == "-")) {
        throw new BadLatinException(bad_fourth);
      }
      return true;
    }
  }
  public static byte get_conjugation(String infinitive){
    switch (infinitive.substring(infinitive.length() - 3)) {
    case "are":
    case "ari":
      return 1;
    case "eri": // passive infinitives of thirds drop the whole ere, we know this is second
    case "ēri":
    case "ēre":
      return 2;
    case "ire":
    case "iri":
      return 4;
    case "ere":
      if (!(config.values.secondlongmark)) {
        msg.warn("Ambiguous infinitive. To which conjugation does the infinitive belong ? ");
        msg.out("Answer 2 for second conjugation, or 3 for third conjugation.");
        msg.out_nonewline("\033[5m: ");
        Scanner inf = new Scanner(System.in);
        String ans;
        for (byte i = 0; i < 3; i++) {
          ans = inf.next(); // String instead of byte to prevent user from crashing the app.
          switch (ans) {
          case "2":
          case "3":
            return Byte.parseByte(ans);
          default:
            msg.warn("Answer was not 2 or 3.");
          }
          throw new IllegalArgumentException("Three incorrect attempts, giving up");
        }
      } else {
        return 3;
      }
    default:
      if (is_deponent(infinitive)) { // 3rd deponents, e.g. sequor sequi
        return 3;
      } else {
        throw new BadLatinException("Infinitive " + infinitive + " did not fall into any conjugation.");
      }
    }
  }
  public static boolean is_transitive(String perfect_passive_participle){
    boolean result;
    if (perfect_passive_participle.endsWith("urus")) {
      result = false;
    } else {
      result = true;
    }
    msg.debug("Is transitive ? " + result);
    return result;
  }
  public static boolean is_deponent(String inf) { // TODO: improve deponent checks
    boolean result;
    if (inf.endsWith("i")) {
      result = true;
    } else {
      result = false;
    }
    msg.debug("Is deponent ? " + result);
    return result;
  }
  public static boolean is_third_io(String first_part) {
    boolean result;
    if (first_part.endsWith("io")) {
      result = true;
    } else {
      result = false;
    }
    msg.debug("Is a third-io ? " + result);
    return result;
  }
}
