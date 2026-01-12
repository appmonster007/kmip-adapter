package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmTtlvDeserializer extends AbstractKmipTtlvDeserializer<CryptographicAlgorithm, Integer> {

    public CryptographicAlgorithmTtlvDeserializer() {
        super(CryptographicAlgorithm.kmipTag, CryptographicAlgorithm.encodingType, Integer.class, value -> new CryptographicAlgorithm(CryptographicAlgorithm.fromValue(value)));
    }
}