package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateLength, Integer> {

    public CertificateLengthXmlDeserializer() {
        super(CertificateLength.kmipTag, CertificateLength.encodingType, Integer.class, value -> CertificateLength.builder().value(value).build());
    }
}