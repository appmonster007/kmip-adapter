package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

public class HashingAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<HashingAlgorithm, String> {

    public HashingAlgorithmXmlSerializer() {
        super(HashingAlgorithm::getDescription);
    }
}