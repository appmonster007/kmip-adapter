package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmXmlSerializer extends AbstractKmipXmlSerializer<OtpAlgorithm, String> {

    public OtpAlgorithmXmlSerializer() {
        super(OtpAlgorithm::getDescription);
    }
}