/**
*
* IOUtils 
* Created by @minht on Oct 15, 2018
*/

package jnp.tmg.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;

public class IOUtils {
    public static byte[] toByteArray(InputStream stream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.close();
        return buffer.toByteArray();

    }
    
    public static byte[] toByteArray(String str,Charset charset) throws IOException {
        return str.getBytes(charset);
    }
    
    
    public static Object toObject(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(in);
        return ois.readObject();
    }
}
