import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Robby on 4/22/17.
 */
public class Bank {

    // Bank public keys
    public BigInteger h, h1, h2;

    // Bank private keys
    private BigInteger x;

    public Bank(BigInteger h, BigInteger h1, BigInteger h2) {
        this.h = h;
        this.h1 = h1;
        this.h2 = h2;
    }

    public Bank(BigInteger h, BigInteger h1, BigInteger h2, BigInteger x) {
        this(h,h1,h2);
        this.x = x;
    }

    public BigInteger createAccountNumber(BigInteger I, State state) {
        return I.multiply(state.g2).modPow(x, state.p);
    }

    // Coin creation public keys for Person
    public BigInteger gw, beta;

    // Coin creation private keys
    public BigInteger w;

    public void initiateCoinCreation(State state, Person person) {
        Random ran = new Random();
        w = BigInteger.valueOf(ran.nextLong());
        gw = state.g.modPow(w, state.p);
        beta = person.I.multiply(state.g2).modPow(w,state.p);
    }

    public BigInteger c1Compute(BigInteger c, State state) {
        return c.multiply(x).add(w).mod(state.q);
    }

}