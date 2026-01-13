package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

public class CryptographicUsageMaskTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskTtlvDeserializer() {
        super(CryptographicUsageMask.kmipTag, CryptographicUsageMask.encodingType, Integer.class, value -> CryptographicUsageMask.builder().value(value).build());
    }
}