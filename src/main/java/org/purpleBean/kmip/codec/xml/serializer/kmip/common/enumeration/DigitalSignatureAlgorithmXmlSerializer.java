package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class DigitalSignatureAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmXmlSerializer() {
        super(DigitalSignatureAlgorithm::getDescription);
    }
}