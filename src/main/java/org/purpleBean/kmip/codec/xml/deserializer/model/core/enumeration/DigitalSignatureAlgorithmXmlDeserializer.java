package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmXmlDeserializer() {
        super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType, String.class, value -> DigitalSignatureAlgorithm.fromName(value).inst());
    }
}