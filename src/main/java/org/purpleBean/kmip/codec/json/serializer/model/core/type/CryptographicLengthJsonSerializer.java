package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

public class CryptographicLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CryptographicLength, Integer> {

    public CryptographicLengthJsonSerializer() {
        super(CryptographicLength::getValue);
    }
}