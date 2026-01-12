package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class HashingAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<HashingAlgorithm, String> {

    public HashingAlgorithmXmlSerializer() {
        super(HashingAlgorithm::getDescription);
    }
}