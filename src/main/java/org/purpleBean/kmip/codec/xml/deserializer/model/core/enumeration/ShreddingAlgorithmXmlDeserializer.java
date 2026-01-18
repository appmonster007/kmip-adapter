package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ShreddingAlgorithm, String> {

    public ShreddingAlgorithmXmlDeserializer() {
        super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType, String.class, value -> new ShreddingAlgorithm(ShreddingAlgorithm.fromName(value)));
    }
}