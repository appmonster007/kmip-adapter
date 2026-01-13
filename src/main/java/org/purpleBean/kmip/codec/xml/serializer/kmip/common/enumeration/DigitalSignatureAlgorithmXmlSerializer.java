package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmXmlSerializer() {
        super(DigitalSignatureAlgorithm::getDescription);
    }
}