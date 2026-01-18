package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ShreddingAlgorithm, Integer> {

    public ShreddingAlgorithmTtlvDeserializer() {
        super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType, Integer.class, value -> new ShreddingAlgorithm(ShreddingAlgorithm.fromValue(value)));
    }
}