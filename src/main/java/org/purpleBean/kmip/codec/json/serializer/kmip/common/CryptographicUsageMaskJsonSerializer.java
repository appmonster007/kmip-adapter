package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

public class CryptographicUsageMaskJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskJsonSerializer() {
        super(CryptographicUsageMask::getValue);
    }
}