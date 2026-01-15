package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;

public class CryptographicUsageMaskTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskTtlvSerializer() {
        super(CryptographicUsageMask::getValue);
    }
}