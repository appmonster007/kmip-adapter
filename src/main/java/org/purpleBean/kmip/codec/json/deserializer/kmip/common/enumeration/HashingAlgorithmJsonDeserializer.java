package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmJsonDeserializer extends AbstractKmipJsonDeserializer<HashingAlgorithm, String> {

    public HashingAlgorithmJsonDeserializer() {
        super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType, String.class, value -> new HashingAlgorithm(HashingAlgorithm.fromName(value)));
    }
}