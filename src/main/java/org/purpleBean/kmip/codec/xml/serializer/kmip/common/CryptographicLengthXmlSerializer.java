package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CryptographicLength, Integer> {

    public CryptographicLengthXmlSerializer() {
        super(CryptographicLength::getValue);
    }
}