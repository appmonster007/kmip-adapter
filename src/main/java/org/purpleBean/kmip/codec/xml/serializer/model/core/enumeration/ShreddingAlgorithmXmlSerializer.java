package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ShreddingAlgorithm, String> {

    public ShreddingAlgorithmXmlSerializer() {
        super(ShreddingAlgorithm::getDescription);
    }
}