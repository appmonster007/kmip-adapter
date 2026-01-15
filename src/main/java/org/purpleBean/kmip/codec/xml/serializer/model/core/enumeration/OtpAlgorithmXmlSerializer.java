package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;

public class OtpAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OtpAlgorithm, String> {

    public OtpAlgorithmXmlSerializer() {
        super(OtpAlgorithm::getDescription);
    }
}