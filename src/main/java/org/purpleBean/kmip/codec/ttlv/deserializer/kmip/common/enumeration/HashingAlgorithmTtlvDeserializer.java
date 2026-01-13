package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<HashingAlgorithm, Integer> {

    public HashingAlgorithmTtlvDeserializer() {
        super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType, Integer.class, value -> new HashingAlgorithm(HashingAlgorithm.fromValue(value)));
    }
}