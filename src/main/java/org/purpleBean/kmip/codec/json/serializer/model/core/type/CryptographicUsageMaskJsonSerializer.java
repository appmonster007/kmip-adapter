package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;

public class CryptographicUsageMaskJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CryptographicUsageMask, Integer> {

    public CryptographicUsageMaskJsonSerializer() {
        super(CryptographicUsageMask::getValue);
    }
}