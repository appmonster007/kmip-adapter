package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmXmlDeserializer extends AbstractKmipXmlDeserializer<ShreddingAlgorithm, String> {

    public ShreddingAlgorithmXmlDeserializer() {
        super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType, String.class, value -> new ShreddingAlgorithm(ShreddingAlgorithm.fromName(value)));
    }
}