package org.kiptoo.models;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;


public class Transaction implements  Serializable{
   private  byte[] from;
   private String fromFX;
   private byte[] to;
   private String toFX;
   private Integer value;
   private  String timestamp;
   private byte[] signature;
   private String signatureFX;
   private Integer ledgerId;

    public Transaction(byte[] from, String fromFX, byte[] to, String toFX, Integer value, String timestamp, byte[] signature, String signatureFX, Integer ledgerId) {
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
}

