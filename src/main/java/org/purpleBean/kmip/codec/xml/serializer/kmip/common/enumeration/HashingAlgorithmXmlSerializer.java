package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<HashingAlgorithm, String> {

    public HashingAlgorithmXmlSerializer() {
        super(HashingAlgorithm::getDescription);
    }
}