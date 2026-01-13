package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

public class CryptographicUsageMaskTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskTtlvSerializer() {
        super(CryptographicUsageMask::getValue);
    }
}