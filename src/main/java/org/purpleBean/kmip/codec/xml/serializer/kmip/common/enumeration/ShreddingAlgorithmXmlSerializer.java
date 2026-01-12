package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<ShreddingAlgorithm, String> {

    public ShreddingAlgorithmXmlSerializer() {
        super(ShreddingAlgorithm::getDescription);
    }
}