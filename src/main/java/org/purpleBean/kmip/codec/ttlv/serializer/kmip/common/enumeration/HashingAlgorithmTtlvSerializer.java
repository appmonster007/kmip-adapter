package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmTtlvSerializer extends AbstractKmipTtlvSerializer<HashingAlgorithm, Integer> {

    public HashingAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}