package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import java.math.BigInteger;
import java.util.*;


public class Controller {
    @FXML
    public TextField plain_txt;
    @FXML
    public TextField cipher_txt;
    @FXML
    public Button encrypt_btn;
    @FXML
    public Button decrypt_btn;

    private static String alphabet = " abcdefghijklmnopqrstuvwxyz";


    private ElGamal elGamal = new ElGamal(BigInteger.valueOf(2357), BigInteger.valueOf(2));


    public void encrypt() {

        if (validatePlainTxt(plain_txt.getText().split(""))) {

            StringBuilder encryptedText = new StringBuilder();

            ArrayList<Integer> plaintext = new ArrayList<>();

            for (char c : plain_txt.getText().toCharArray()) {

                plaintext.add(alphabet.indexOf(c));

            }

            for (int m : plaintext) {
                Pair<BigInteger, BigInteger> pair = elGamal.encrypt(BigInteger.valueOf(m));
                int alphabetLength = 27;
                int l = 3;
                ArrayList<Integer> alphaSplit = splitInfo(pair.getKey(), alphabetLength, l);
                ArrayList<Integer> betaSplit = splitInfo(pair.getValue(), alphabetLength, l);

                for (int value : alphaSplit) {
                    encryptedText.append(alphabet.charAt(value));
                }
                for (int value : betaSplit) {
                    encryptedText.append(alphabet.charAt(value));
                }
            }
            cipher_txt.setText(encryptedText.toString().toUpperCase());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong plain text! LETTERS MUST BE lowercase");
            alert.showAndWait();
        }
    }

    private Boolean validatePlainTxt(String[] txt) {

        for (String e : txt) {
            if (!e.matches("[a-z ]")) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<Integer> splitInfo(BigInteger key, int alphabetLength, int l) {
        ArrayList<Integer> result = new ArrayList<>();
        int number = key.intValue();
        int power = (int) Math.pow(alphabetLength, l - 1);
        for (int i = 0; i < l; i++) {
            result.add(number / power);
            number = number % power;
            power = power / alphabetLength;
        }
        return result;
    }


    public void decrypt() {
        if (validateCipherTxt(cipher_txt.getText().split(""))) {
            StringBuilder decryptedText = new StringBuilder();
            String cipherTxt = cipher_txt.getText();
            if (cipherTxt.length() % 6 == 0) {
                int i = 0;
                int c1;
                int c2;
                int c3;
                int c4;
                int c5;
                int c6;
                while (i < cipherTxt.length()) {
                    String upperAlphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    c1 = upperAlphabet.indexOf(cipherTxt.charAt(i));
                    c2 = upperAlphabet.indexOf(cipherTxt.charAt(i + 1));
                    c3 = upperAlphabet.indexOf(cipherTxt.charAt(i + 2));
                    c4 = upperAlphabet.indexOf(cipherTxt.charAt(i + 3));
                    c5 = upperAlphabet.indexOf(cipherTxt.charAt(i + 4));
                    c6 = upperAlphabet.indexOf(cipherTxt.charAt(i + 5));

                    BigInteger alpha = BigInteger.valueOf(c1 * 27 * 27 + c2 * 27 + c3);
                    BigInteger beta = BigInteger.valueOf(c4 * 27 * 27 + c5 * 27 + c6);

                    int message = elGamal.decrypt(new Pair<>(alpha, beta)).intValue();

                    decryptedText.append(alphabet.charAt(message));

                    i += 6;
                }
                plain_txt.setText(decryptedText.toString());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong cipher length!!!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong cipher text! Letters must be UPPERCASE");
            alert.showAndWait();
        }
    }


    private Boolean validateCipherTxt(String[] txt) {

        for (String e : txt) {
            if (!e.matches("[A-Z ]")) {
                return false;
            }
        }

        return true;
    }


}
