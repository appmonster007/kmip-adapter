package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthXmlSerializer extends AbstractKmipXmlSerializer<CryptographicLength, Integer> {

    public CryptographicLengthXmlSerializer() {
        super(CryptographicLength::getValue);
    }
}