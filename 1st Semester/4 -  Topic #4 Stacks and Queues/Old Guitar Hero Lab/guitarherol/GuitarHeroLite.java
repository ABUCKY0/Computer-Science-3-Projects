/*****************************************************************************
 *  Compilation:  javac GuitarHeroLite.java
 *  Execution:    java  GuitarHeroLite
 *  Dependencies: StdAudio.java StdDraw.java GuitarString.java
 *
 *  Plays two guitar strings (concert A and concert C) when the user
 *  types the lowercase letters 'a' and 'c', respectively in the 
 *  standard drawing window.
 *
 ****************************************************************************/

public class GuitarHeroLite {

    public static void main(String[] args) {

        // Create two guitar strings, for concert A and C
        /*double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);*/

        final double TEXT_POS_X = .2;
        final double TEXT_POS_Y = .5;
        
        StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "E = a, B = s, G = d, D = j, A = k, e (6) = l");
        
        String chars = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[chars.length()];
        for (int i = 0; i < chars.length(); i++) {
            
            strings[i] = new GuitarString(440*Math.pow(1.05956, (i - 24)));
        }


        play(strings, chars);
    }
    
    private static void play(GuitarString[] strings, String chars) {        // the main input loop
        while (true) {
            
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if (chars.indexOf(key) != -1) {
                    strings[chars.indexOf(key)].pluck();
                }
            }

            // compute the superposition of the samples
            double sample = 0.0;
            for (int x = 0; x < strings.length; x++) {
                sample+=strings[x].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int x = 0; x < strings.length; x++) {
                strings[x].tic();
            }
        }
        
    }

}
