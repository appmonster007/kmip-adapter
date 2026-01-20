package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;

public class OtpAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<OtpAlgorithm, String> {

    public OtpAlgorithmXmlDeserializer() {
        super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType, String.class, value -> OtpAlgorithm.fromName(value).inst());
    }
}