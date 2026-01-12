package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CryptographicLength;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class CryptographicLengthXmlSerializer extends AbstractKmipXmlSerializer<CryptographicLength, Integer> {

    public CryptographicLengthXmlSerializer() {
        super(CryptographicLength::getValue);
    }
}