package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

public class HashingAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<HashingAlgorithm, String> {

    public HashingAlgorithmXmlDeserializer() {
        super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType, String.class, value -> HashingAlgorithm.fromName(value).inst());
    }
}