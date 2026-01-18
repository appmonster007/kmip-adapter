package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ShreddingAlgorithm, String> {

    public ShreddingAlgorithmJsonDeserializer() {
        super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType, String.class, value -> new ShreddingAlgorithm(ShreddingAlgorithm.fromName(value)));
    }
}