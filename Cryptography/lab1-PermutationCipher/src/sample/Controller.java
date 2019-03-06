package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    @FXML
    public TextField plain_txt;
    @FXML
    public TextField cipher_txt;
    @FXML
    public Button encrypt_btn;
    @FXML
    public TextField key;
    @FXML
    public TextField length;
    @FXML
    public Button decrypt_btn;

    private PermutationCipher permCipher = new PermutationCipher();

    public void encrypt() {
        if (this.validateLengthKey() && this.validatePlainTextInput()) {
            try {
                int l = Integer.parseInt(length.getText());
                int k = Integer.parseInt(key.getText());
                StringBuilder s = new StringBuilder(plain_txt.getText());
                while (s.length() % l != 0) {
                    s.append(" ");
                }

                ArrayList<Integer> arr_key = new ArrayList<>();
                for (int j = 0; j < l; j++) {
                    arr_key.add(j, 0);
                }
                for (int i = l - 1; i >= 0; i--) {
                    arr_key.set(i, k % 10);
                    k = k / 10;
                }
                permCipher.setLength(l);
                permCipher.setPermutation(arr_key);
                permCipher.setPlainTxt(s.toString().toUpperCase());
                cipher_txt.setText(permCipher.encrypt().toString());
            } catch (Exception e) {
                System.out.println("Could not encrypt" + e);
            }

        } else {
            this.validateLengthKey();
        }
    }

    public void decrypt() {
        if (this.validateLengthKey() && this.validateCipherTextInput()) try {
            int l = Integer.parseInt(length.getText());
            int k = Integer.parseInt(key.getText());
            StringBuilder s = new StringBuilder(cipher_txt.getText());
            while (s.length() % l != 0) {
                s.append(" ");
            }

            ArrayList<Integer> arr_key = new ArrayList<>();
            for (int j = 0; j < l; j++) {
                arr_key.add(j, 0);
            }
            for (int i = l - 1; i >= 0; i--) {
                arr_key.set(i, k % 10);
                k = k / 10;
            }
            permCipher.setLength(l);
            permCipher.setCipherTxt(s.toString());
            permCipher.createInvPerm(arr_key);
            plain_txt.setText(permCipher.decrypt().toString().toLowerCase());
        } catch (Exception e) {
            System.out.println("Could not decrypt" + e);
        }
    }

    private Boolean validateLengthKey() {
        if (!validateLength(length.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong length input!(Length must be greater than 0)");
            alert.showAndWait();
            return false;
        } else if (!validateKey(key.getText(), Integer.parseInt(length.getText()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong key input! Key must be have" + this.length.getText() + " distinct digits, each digit must >0 and <=  " + this.length.getText());
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private Boolean validateLength(String length) {
        try {
            return Integer.parseInt(length) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean validateKey(String key, int m) {

        if (key.length() == m) {
            try {
                int num = Integer.parseInt(key);
                Boolean[] visited = new Boolean[m + 1];
                Arrays.fill(visited, Boolean.FALSE);
                while (num > 0) {
                    if (visited[num % 10] | num % 10 > m | num % 10 == 0)
                        return false;
                    visited[num % 10] = true;
                    num = num / 10;
                }
                return true;

            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private Boolean validatePlainTextInput() {

        if (!validatePlainTxt(plain_txt.getText().split(""))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong plain text input!(all letter must be lowercase");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private Boolean validatePlainTxt(String[] txt) {

        for (String e : txt) {
            if (!e.matches("[a-z ]")) {
                return false;
            }
        }

        return true;
    }

    private Boolean validateCipherTextInput() {

        if (!validateCipherTxt(cipher_txt.getText().split(""))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong cipher text input!(all letter must be uppercase");
            alert.showAndWait();
            return false;
        }
        return true;
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

