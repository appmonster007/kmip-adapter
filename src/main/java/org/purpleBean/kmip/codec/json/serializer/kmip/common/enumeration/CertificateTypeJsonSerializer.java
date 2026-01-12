package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.CertificateType;

public class CertificateTypeJsonSerializer extends AbstractKmipJsonSerializer<CertificateType, String> {

    public CertificateTypeJsonSerializer() {
        super(CertificateType::getDescription);
    }
}