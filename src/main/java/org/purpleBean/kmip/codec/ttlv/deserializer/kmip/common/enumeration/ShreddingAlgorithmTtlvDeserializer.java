package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ShreddingAlgorithm, Integer> {

    public ShreddingAlgorithmTtlvDeserializer() {
        super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType, Integer.class, value -> new ShreddingAlgorithm(ShreddingAlgorithm.fromValue(value)));
    }
}