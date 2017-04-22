import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Robby on 4/22/17.
 */
public class CoinSimulation {

    public static void main(String args[]) {
        // Initialize central authority with public keys
        State state = new State(
                new BigInteger("951834317867"),
                new BigInteger("475917158933"),
                new BigInteger("287056724788"),
                new BigInteger("612509926254"),
                new BigInteger("621775844656"));

        // Initialize bank with public keys
        Bank bank = new Bank(
                new BigInteger("306022109190"),
                new BigInteger("353444264256"),
                new BigInteger("389437573656"),
                new BigInteger("246828991479"));

        // Initialize personal information for Bob
        Person bob = new Person(new BigInteger("957672238971"));
        bob.initializeI(state);
        bob.zPInitialize(bank,state);

        // Create some coins for bob
        createCoinsForPerson(100,state,bank,bob);
    }

    public static void createCoinsForPerson(int amount, State state, Bank bank, Person person) {
        for (int i = 0; i < amount; i++) {
            // Temporary initiation from bank
            bank.initiateCoinCreation(state, person);

            // Person creates random 5-tuple, disposes of information when done
            Random5Tuple tup = person.createTuple();

            // Person computes partial coin information with gw and beta supplied from bank
            Coin coin = person.createPartialCoin(state, tup, bank.gw, bank.beta);

            // Person computes c and sends to bank
            BigInteger c = person.cCalculate(tup,state,coin);

            // Bank computes c1 with c provided from person
            BigInteger c1 = bank.c1Compute(c,state);

            // Person computes r and establish coins r value therein making the coin valid for transactions
            // adds new coin to wallet
            person.completeCoinCreation(state,tup,c1,coin);
        }
    }

}
