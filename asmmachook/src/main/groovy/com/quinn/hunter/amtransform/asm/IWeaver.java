package com.quinn.hunter.amtransform.asm;

import com.android.build.api.transform.QualifiedContent;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by quinn on 06/09/2018
 */
public interface IWeaver {

    /**
     * Check a certain file is weavable
     *
     * @param input    input
     * @param filePath filePath
     * @return isWeavableClass
     * @throws IOException ex
     */
    public boolean isWeavableClass(QualifiedContent input, String filePath) throws IOException;

    /**
     * Weave single class to byte array
     *
     * @param inputStream inputStream
     * @return byte
     * @throws IOException ex
     */
    public byte[] weaveSingleClassToByteArray(InputStream inputStream) throws IOException;


}