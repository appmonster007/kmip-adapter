package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RngAlgorithm, String> {

    public RngAlgorithmXmlDeserializer() {
        super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType, String.class, value -> new RngAlgorithm(RngAlgorithm.fromName(value)));
    }
}