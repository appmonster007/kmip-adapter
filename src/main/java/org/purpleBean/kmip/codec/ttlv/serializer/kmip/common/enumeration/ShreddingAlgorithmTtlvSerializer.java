package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmTtlvSerializer extends AbstractKmipTtlvSerializer<ShreddingAlgorithm, Integer> {

    public ShreddingAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}