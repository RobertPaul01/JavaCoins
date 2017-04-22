import java.math.BigInteger;

/**
 * Created by Robby on 4/22/17.
 */
public class Coin {

    public BigInteger A, B, z, a, b, r;

    public Coin(BigInteger a, BigInteger b, BigInteger z, BigInteger a1, BigInteger b1) {
        A = a;
        B = b;
        this.z = z;
        this.a = a1;
        this.b = b1;
    }

    @Override
    public String toString() {
        return "(" + A + "," + B + "," + z + "," + a + "," + b + "," + r + ")";
    }

}