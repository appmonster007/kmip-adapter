package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OtpAlgorithm, String> {

    public OtpAlgorithmXmlSerializer() {
        super(OtpAlgorithm::getDescription);
    }
}