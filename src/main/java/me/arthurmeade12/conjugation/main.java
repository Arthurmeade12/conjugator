package me.arthurmeade12.conjugator;
public class main {
  public static void main(String[] args) {
    if (args.length >= 3) {
      config.evalprops();
      String[] verb = new String[4];
      if (args.length == 3) {
        for (byte a = 0; a < 3; a++) {
          verb[a] = args[a];
        }
        verb[3] = "-";
      } else {
        verb = args;
      }
      msg.debug("Verb[] : " + verb[0] + " " + verb[1] + " " + verb[2] + " " + verb[3]);
      latinutils.checks(verb);
      msg.debug("Conjugation: " + latinutils.get_conjugation(verb[1]));
    } else {
      msg.warn("Only accepting command-line arguments at the moment.");
    }
  }
}
