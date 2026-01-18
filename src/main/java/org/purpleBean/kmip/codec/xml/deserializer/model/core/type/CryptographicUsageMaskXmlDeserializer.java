package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;

public class CryptographicUsageMaskXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskXmlDeserializer() {
        super(CryptographicUsageMask.kmipTag, CryptographicUsageMask.encodingType, Integer.class, value -> CryptographicUsageMask.builder().value(value).build());
    }
}