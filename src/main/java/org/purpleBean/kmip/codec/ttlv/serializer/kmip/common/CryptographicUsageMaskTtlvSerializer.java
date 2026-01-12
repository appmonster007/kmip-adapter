package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

public class CryptographicUsageMaskTtlvSerializer extends AbstractKmipTtlvSerializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskTtlvSerializer() {
        super(CryptographicUsageMask::getValue);
    }
}