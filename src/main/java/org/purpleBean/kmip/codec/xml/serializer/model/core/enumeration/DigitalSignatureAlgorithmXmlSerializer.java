package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmXmlSerializer() {
        super(DigitalSignatureAlgorithm::getDescription);
    }
}