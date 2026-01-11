package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

public class CryptographicUsageMaskJsonDeserializer extends AbstractKmipJsonDeserializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskJsonDeserializer() {
        super(CryptographicUsageMask.kmipTag, CryptographicUsageMask.encodingType, Integer.class, value -> CryptographicUsageMask.builder().value(value).build());
    }
}