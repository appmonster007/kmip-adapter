package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ShreddingAlgorithm, String> {

    public ShreddingAlgorithmJsonDeserializer() {
        super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType, String.class, value -> new ShreddingAlgorithm(ShreddingAlgorithm.fromName(value)));
    }
}