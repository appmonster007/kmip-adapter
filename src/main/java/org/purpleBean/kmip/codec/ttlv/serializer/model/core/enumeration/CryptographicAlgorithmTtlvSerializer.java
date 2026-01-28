package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CryptographicAlgorithm, Integer> {

    public CryptographicAlgorithmTtlvSerializer() {
        super(CryptographicAlgorithm::getIntValue);
    }
}