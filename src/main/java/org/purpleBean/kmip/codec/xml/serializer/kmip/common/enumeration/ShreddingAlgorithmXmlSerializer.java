package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ShreddingAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<ShreddingAlgorithm, String> {

    public ShreddingAlgorithmXmlSerializer() {
        super(ShreddingAlgorithm::getDescription);
    }
}