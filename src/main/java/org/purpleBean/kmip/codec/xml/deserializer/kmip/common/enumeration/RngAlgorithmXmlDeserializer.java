package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

public class RngAlgorithmXmlDeserializer extends AbstractKmipXmlDeserializer<RngAlgorithm, String> {

    public RngAlgorithmXmlDeserializer() {
        super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType, String.class, value -> new RngAlgorithm(RngAlgorithm.fromName(value)));
    }
}