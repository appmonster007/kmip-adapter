package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CertificateType;

public class CertificateTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateType, String> {

    public CertificateTypeXmlDeserializer() {
        super(CertificateType.kmipTag, CertificateType.encodingType, String.class, value -> new CertificateType(CertificateType.fromName(value)));
    }
}