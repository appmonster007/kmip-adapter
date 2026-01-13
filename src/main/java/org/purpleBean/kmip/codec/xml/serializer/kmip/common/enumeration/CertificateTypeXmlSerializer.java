package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.CertificateType;

public class CertificateTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateType, String> {

    public CertificateTypeXmlSerializer() {
        super(CertificateType::getDescription);
    }
}