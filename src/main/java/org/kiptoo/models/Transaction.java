package org.kiptoo.models;
import sun.security.provider.DSAPublicKeyImpl;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;


public class Transaction implements  Serializable {
    private byte[] from;
    private String fromFX;
    private byte[] to;
    private String toFX;
    private Integer value;
    private String timestamp;
    private byte[] signature;
    private String signatureFX;
    private Integer ledgerId;

    public Transaction(byte[] from, byte[] to, Integer value, byte[] signature, Integer ledgerId, String timestamp) {
        Base64.Encoder encoder = Base64.getEncoder();
        this.from = from;
        this.fromFX = fromFX;
        this.to = to;
        this.toFX = toFX;
        this.value = value;
        this.timestamp = timestamp;
        this.signature = signature;
        this.signatureFX = signatureFX;
        this.ledgerId = ledgerId;
    }

    public Transaction(Wallet fromWallet, byte[] toAddresss, Integer value, Integer ledgerId, Signature signing)
            throws InvalidKeyException, SignatureException {
        Base64.Encoder encoder = Base64.getEncoder();
        this.from = fromWallet.getPublicKey().getEncoded();
        this.fromFX = encoder.encodeToString(
                fromWallet.getPublicKey().getEncoded()
        );
        this.to = toAddresss;
        this.toFX = encoder.encodeToString(toAddresss);
        this.value = value;
        this.timestamp = LocalDateTime.now().toString();
        signing.initSign(fromWallet.getPrivateKey());
        String sr = this.toString();
        signing.update(sr.getBytes());
        this.signature = signing.sign();
        this.signatureFX = encoder.encodeToString(this.signature);
        this.ledgerId = ledgerId;
    }

    public Boolean isVerified(Signature signining)
            throws InvalidKeyException, SignatureException {
        signining.initVerify(new DSAPublicKeyImpl(
                this.getFrom()
        ));
        signining.update(this.toString().getBytes());
        return signining.verify(this.signature);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + Arrays.toString(from) +
                ", to=" + Arrays.toString(to) +
                ", value=" + value +
                ", timestamp='" + timestamp + '\'' +
                ", ledgerId=" + ledgerId +
                '}';
    }

    public byte[] getFrom() {
        return from;
    }

    public void setFrom(byte[] from) {
        this.from = from;
    }

    public String getFromFX() {
        return fromFX;
    }

    public void setFromFX(String fromFX) {
        this.fromFX = fromFX;
    }

    public byte[] getTo() {
        return to;
    }

    public void setTo(byte[] to) {
        this.to = to;
    }

    public String getToFX() {
        return toFX;
    }

    public void setToFX(String toFX) {
        this.toFX = toFX;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getSignatureFX() {
        return signatureFX;
    }

    public void setSignatureFX(String signatureFX) {
        this.signatureFX = signatureFX;
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof  Transaction)) return  false;
        Transaction that = (Transaction) o;
        return  Arrays.equals(getSignature(), that.getSignature());


    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getSignature());
    }
}

