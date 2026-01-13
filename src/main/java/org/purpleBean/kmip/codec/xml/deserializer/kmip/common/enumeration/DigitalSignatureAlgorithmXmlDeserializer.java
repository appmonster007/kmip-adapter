package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmXmlDeserializer() {
        super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType, String.class, value -> new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.fromName(value)));
    }
}