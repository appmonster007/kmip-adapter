package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

public class HashingAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<HashingAlgorithm, String> {

    public HashingAlgorithmJsonDeserializer() {
        super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType, String.class, value -> new HashingAlgorithm(HashingAlgorithm.fromName(value)));
    }
}