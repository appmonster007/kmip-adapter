package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;

public class CertificateTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateType, String> {

    public CertificateTypeXmlSerializer() {
        super(CertificateType::getDescription);
    }
}