package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthJsonSerializer extends AbstractKmipJsonSerializer<CryptographicLength, Integer> {

    public CryptographicLengthJsonSerializer() {
        super(CryptographicLength::getValue);
    }
}