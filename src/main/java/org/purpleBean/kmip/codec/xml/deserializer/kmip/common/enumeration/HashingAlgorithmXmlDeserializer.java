package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmXmlDeserializer extends AbstractKmipXmlDeserializer<HashingAlgorithm, String> {

    public HashingAlgorithmXmlDeserializer() {
        super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType, String.class, value -> new HashingAlgorithm(HashingAlgorithm.fromName(value)));
    }
}