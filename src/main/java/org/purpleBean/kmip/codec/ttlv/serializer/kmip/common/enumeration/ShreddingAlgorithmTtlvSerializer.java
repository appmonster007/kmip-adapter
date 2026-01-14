package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ShreddingAlgorithm, Integer> {

    public ShreddingAlgorithmTtlvSerializer() {
        super(ShreddingAlgorithm::getValue);
    }
}