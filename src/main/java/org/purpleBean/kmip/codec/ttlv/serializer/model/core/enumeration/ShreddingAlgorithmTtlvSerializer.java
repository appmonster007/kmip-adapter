package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ShreddingAlgorithm, Integer> {

    public ShreddingAlgorithmTtlvSerializer() {
        super(ShreddingAlgorithm::getIntValue);
    }
}