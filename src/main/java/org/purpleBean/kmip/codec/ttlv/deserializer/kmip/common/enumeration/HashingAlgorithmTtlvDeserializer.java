package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmTtlvDeserializer extends AbstractKmipTtlvDeserializer<HashingAlgorithm, Integer> {

    public HashingAlgorithmTtlvDeserializer() {
        super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType, Integer.class, value -> new HashingAlgorithm(HashingAlgorithm.fromValue(value)));
    }
}