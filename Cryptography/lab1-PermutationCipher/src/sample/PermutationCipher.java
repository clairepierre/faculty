package sample;

import java.util.ArrayList;

class PermutationCipher {
    private ArrayList<Integer> permutation;
    private ArrayList<Integer> invPermutation;
    private String plaintxt;
    private String ciphertxt;
    private Integer length;


    PermutationCipher() {
        this.permutation = new ArrayList<>();
        this.invPermutation = new ArrayList<>();
        this.plaintxt = null;
        this.ciphertxt = null;
    }

    void createInvPerm(ArrayList<Integer> perm) {
        ArrayList<Integer> invp = new ArrayList<>();
        for (int j = 0; j < length; j++) {
            invp.add(0);
        }
        for (int i = 0; i < length; i++) {
            invp.set(perm.get(i) - 1, i + 1);
        }
        this.setInvPermutation(invp);
    }

    private StringBuilder splitText(ArrayList<Integer> perm, String txt, int n) {
        StringBuilder newTxt = new StringBuilder();
        String[] arrTxt = txt.split("");
        for (int i = 0; i < n; i++) {
            newTxt.append(arrTxt[perm.get(i) - 1]);
        }
        return newTxt;
    }

    StringBuilder encrypt() {
        return this.modify(this.permutation, this.plaintxt);
    }

    StringBuilder decrypt() {
        return this.modify(this.invPermutation, this.ciphertxt);
    }

    private StringBuilder modify(ArrayList<Integer> perm, String txt) {
        StringBuilder newTxt = new StringBuilder();
        int i = 0;

        while (i < txt.length()) {
            String smalltxt = txt.substring(i, i + length);
            newTxt.append(splitText(perm, smalltxt, length));
            i = i + length;

        }
        return newTxt;
    }


    void setPermutation(ArrayList<Integer> permutation) {
        this.permutation = permutation;
    }

    private void setInvPermutation(ArrayList<Integer> invPermutation) {
        this.invPermutation = invPermutation;
    }

    void setPlainTxt(String plaintxt) {
        this.plaintxt = plaintxt;
    }

    void setCipherTxt(String ciphertxt) {
        this.ciphertxt = ciphertxt;
    }

    void setLength(Integer length) {
        this.length = length;
    }


}

