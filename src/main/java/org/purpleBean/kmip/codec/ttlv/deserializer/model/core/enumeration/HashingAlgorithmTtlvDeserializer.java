package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

public class HashingAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<HashingAlgorithm, Integer> {

    public HashingAlgorithmTtlvDeserializer() {
        super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType, Integer.class, value -> HashingAlgorithm.fromValue(value).inst());
    }
}