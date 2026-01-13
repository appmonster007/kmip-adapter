package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CryptographicLength;

public class CryptographicLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CryptographicLength, Integer> {

    public CryptographicLengthTtlvSerializer() {
        super(CryptographicLength::getValue);
    }
}