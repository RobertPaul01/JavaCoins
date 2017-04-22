import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Robby on 4/22/17.
 */
public class Person {

    // Collection of person's coins
    private List<Coin> wallet;

    // Secret personal information
    private BigInteger u;

    // Personal account number based on central authority
    public BigInteger I;

    // Personal information received from bank when bank account created
    public BigInteger zP;

    public Person(BigInteger u) {
        wallet = new ArrayList<>();
        this.u = u;
    }

    public void initializeI(State state) {
        this.I = state.g1.modPow(u, state.p);
    }

    public void zPInitialize(Bank bank, State state) {
        zP = bank.zPcompute(this, state);
    }

    public Random5Tuple createTuple() {
        Random ran = new Random();
        BigInteger s = BigInteger.valueOf(ran.nextLong());
        BigInteger x1 = BigInteger.valueOf(ran.nextLong());
        BigInteger x2 = BigInteger.valueOf(ran.nextLong());
        BigInteger a1 = BigInteger.valueOf(ran.nextLong());
        BigInteger a2 = BigInteger.valueOf(ran.nextLong());
        return new Random5Tuple(s,x1,x2,a1,a2);
    }

    public Coin createPartialCoin(State state, Random5Tuple tup, BigInteger gw, BigInteger beta) {
        BigInteger A = this.I.multiply(state.g2).modPow(tup.s,state.p);
        BigInteger B = state.g1.modPow(tup.x1,state.p).multiply((state.g2.modPow(tup.x2,state.p))).mod(state.p);
        BigInteger z = this.zP.modPow(tup.s,state.p);
        BigInteger a = gw.modPow(tup.a1, state.p).multiply((state.g.modPow(tup.a2,state.p))).mod(state.p);
        BigInteger b = beta.modPow((tup.s.multiply(tup.a1)),state.p).multiply((A.modPow(tup.a2,state.p))).mod(state.p);
        return new Coin(A,B,z,a,b);
    }

    public BigInteger cCalculate(Random5Tuple tup, State state, Coin coin) {
        return tup.a1.modInverse(state.q).multiply(state.H(coin)).mod(state.q);
    }

    public void completeCoinCreation(State state, Random5Tuple tup, BigInteger c1, Coin coin) {
        BigInteger r = tup.a1.multiply(c1).add(tup.a2).mod(state.q);
        coin.r = r;
        wallet.add(coin);
    }

}