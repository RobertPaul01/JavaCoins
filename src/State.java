import java.math.BigInteger;

/**
 * Created by Robby on 4/20/17.
 */
public class State {

    // Central authority public keys
    public BigInteger p, q, g, g1, g2;

    public State(BigInteger p, BigInteger q, BigInteger g, BigInteger g1, BigInteger g2) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.g1 = g1;
        this.g2 = g2;
    }

    // H(A,B,z,a,b)=((A+B)^(z+a+b))%q
    public BigInteger H(Coin coin) {
        return coin.A.add(coin.B).modPow((coin.z.add(coin.a).add(coin.b)), q);
    }

    // H0(A,B,M,t)=((A+t)^(B+M))%q
    public BigInteger H0(Transaction trans) {
        return trans.A.add(trans.t).modPow((trans.B.add(trans.M)), q);
    }

    // g^r = a*H^(h(A,B,z,a,b)
    public boolean gCheckValid(Coin coin, Bank bank) {
        return (g.modPow(coin.r,p)).equals(coin.a.multiply((bank.h.modPow(H(coin),p))).mod(p));
    }

    // A^r = b*z^H(A,B,z,a,b)
    public boolean aCheckValid(Coin coin) {
        return (coin.A.modPow(coin.r,p)).equals(coin.b.multiply((coin.z.modPow(H(coin),p))).mod(p));
    }

}