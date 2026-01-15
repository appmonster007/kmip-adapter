package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

public class CryptographicLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CryptographicLength, Integer> {

    public CryptographicLengthXmlSerializer() {
        super(CryptographicLength::getValue);
    }
}