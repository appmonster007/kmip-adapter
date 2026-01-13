package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CryptographicAlgorithm, Integer> {

    public CryptographicAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}