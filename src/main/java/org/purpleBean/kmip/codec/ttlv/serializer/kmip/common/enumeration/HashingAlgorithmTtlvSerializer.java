package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<HashingAlgorithm, Integer> {

    public HashingAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}