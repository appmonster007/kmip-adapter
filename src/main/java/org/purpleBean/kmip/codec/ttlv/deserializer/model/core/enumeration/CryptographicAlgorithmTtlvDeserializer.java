package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CryptographicAlgorithm, Integer> {

    public CryptographicAlgorithmTtlvDeserializer() {
        super(CryptographicAlgorithm.kmipTag, CryptographicAlgorithm.encodingType, Integer.class, value -> CryptographicAlgorithm.fromValue(value).inst());
    }
}