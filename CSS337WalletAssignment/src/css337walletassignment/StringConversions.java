import java.math.BigInteger;

public class StringConversions {
	public static byte[] hexStringToByteArray(String inputStr) {
		byte[] val = new byte[inputStr.length() / 2];
	    for (int i = 0; i < val.length; i++) {
	       int index = i * 2;
	       int j = Integer.parseInt(inputStr.substring(index, index + 2), 16);
	       val[i] = (byte) j;
	    }
	    
	    return val;
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static byte[] hexStringToByte(String hexstr) 
	{
	    byte[] retVal = new BigInteger(hexstr, 16).toByteArray();
	    if (retVal[0] == 0) 
	    {
	    	byte[] newArray = new byte[retVal.length - 1];
	    	System.arraycopy(retVal, 1, newArray, 0, newArray.length);
	    	return newArray;
	    }
	    return retVal;
	}
}
