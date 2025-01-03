package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";;
    private static double cunstom = 440.0;

    public static void main(String[] args) {

        GuitarString[] frequencys = new GuitarString[keyboard.length()];
        for (int i = 0; i < frequencys.length; i++) {
            frequencys[i] = new GuitarString(cunstom * Math.pow(2, (double) (i - 24) / 12));
        }

        int idx = -1;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                idx = keyboard.indexOf(key);
                if (idx != -1) {
                    frequencys[idx].pluck();
                }
            }
            if (idx != -1) {
                /* compute the superposition of samples */
                double sample = frequencys[idx].sample();

                /* play the sample on standard audio */
                StdAudio.play(sample);

                /* advance the simulation of each guitar string by one step */
                frequencys[idx].tic();
            }
        }
    }
}

