package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmXmlDeserializer extends AbstractKmipXmlDeserializer<OtpAlgorithm, String> {

    public OtpAlgorithmXmlDeserializer() {
        super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType, String.class, value -> new OtpAlgorithm(OtpAlgorithm.fromName(value)));
    }
}