package org.kiptoo.models;
import java.io.Serializable;
import java.security.*;

import static sun.security.krb5.internal.ktab.KeyTabConstants.keySize;

public class Wallet implements  Serializable{
    private KeyPair keyPair;

    public Wallet() throws  NoSuchAlgorithmException {
        this(2048, KeyPairGenerator.getInstance("DSA"));
    }
    public  Wallet(Integer KeySize, KeyPairGenerator keyPairGenerator){
        keyPairGenerator.initialize(keySize);
        this.keyPair = keyPairGenerator.generateKeyPair();
    }

    public Wallet(PublicKey publicKey, PrivateKey privateKey){
        this.keyPair = new KeyPair(publicKey,privateKey);
    }

    public  KeyPair getKeyPair(){
        return keyPair;
    }
    public  PublicKey getPublicKey(){
        return keyPair.getPublic();
    }

    public PrivateKey getPrivateKey(){
        return keyPair.getPrivate();
    }
}
