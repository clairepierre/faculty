package sample;

import javafx.util.Pair;
import java.math.BigInteger;
import java.util.Random;

public class ElGamal {

    private BigInteger p;
    private BigInteger g;
    private BigInteger gA;
    private Random rn = new Random();
    private Integer a;

    public ElGamal(BigInteger p, BigInteger g) {
        this.p = p;
        this.g = g;
        this.a = rn.nextInt(p.intValue() - 2);
//        this.a = 1751;
        this.gA = g.pow(a).mod(p);
    }


    Pair<BigInteger, BigInteger> encrypt(BigInteger message) {
        BigInteger alpha, betha;
        int k;
        k = rn.nextInt(p.intValue() - 2);
        alpha = this.g.pow(k).mod(p);

        betha = this.gA.pow(k).multiply(message).mod(this.p);
        return new Pair<>(alpha, betha);
    }

    BigInteger decrypt(Pair<BigInteger, BigInteger> pair) {
        BigInteger message;

        int exponent = p.intValue() - 1 - this.a;

        message = pair.getValue().multiply(pair.getKey().pow(exponent)).mod(p);

        return message;
    }
}
