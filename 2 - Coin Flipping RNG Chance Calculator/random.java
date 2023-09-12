import java.lang.Math;

public class MyProgram
{
    public static void main(String[] args)
    {
        PRNG prng = new PRNG();
        prng.flip(9999999);
        
    }
}

class PRNG 
{
    
    
    public PRNG() {
        
    }
    public void flip(int flips) {
        
        int heads = 0;
        int tails = 0;
        for (int x = 0; x < flips; x++) {
            int randNum = (int) Math.round(Math.random());
            if (randNum == 0) {
                heads++;
            }
            else if (randNum == 1) {
                tails++;
            }
            else {
                System.out.println("Error: Invalid Number Recieved");
                break;
                
            }
        }
        System.out.println("Heads/flips: " + ((double)heads/(double)flips)*100 + "%");
        System.out.println("Tails/flips: " + ((double)tails/(double)flips)*100 + "%");
        System.out.println("Heads Total: " + heads);
        System.out.println("Tails Total: " + tails);
        System.out.println("Number of Flips: " + flips);
        
    }
}
