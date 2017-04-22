import java.math.BigInteger;

/**
 * Created by Robby on 4/22/17.
 */
public class Transaction {

    public BigInteger A, B, M, t;

    public Transaction(BigInteger a, BigInteger b, BigInteger m, BigInteger t) {
        A = a;
        B = b;
        M = m;
        this.t = t;
    }

}
