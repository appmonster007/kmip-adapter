package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

public class CryptographicUsageMaskXmlDeserializer extends AbstractKmipXmlDeserializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskXmlDeserializer() {
        super(CryptographicUsageMask.kmipTag, CryptographicUsageMask.encodingType, Integer.class, value -> CryptographicUsageMask.builder().value(value).build());
    }
}