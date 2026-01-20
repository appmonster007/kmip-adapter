package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;

public class CertificateTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateType, String> {

    public CertificateTypeXmlDeserializer() {
        super(CertificateType.kmipTag, CertificateType.encodingType, String.class, value -> CertificateType.fromName(value).inst());
    }
}