/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package css337walletassignment;

import Cryptography.SHA256Hasher;

/**
 *
 * @author Michael
 */
public class User {
    private final static String PERSONAL_FULL_ID = "1775386";
    public final static String PERSONAL_WALLET_ID = "368";
    public final static String PERSONAL_WALLET_KEY = SHA256Hasher.sha256(PERSONAL_FULL_ID);
    public final static Balance PERSONAL_BALANCE = new Balance();
    //private final static WalletInteractionMap PERSONAL_INTERACTIONS = new WaletInteractionMap();
}
