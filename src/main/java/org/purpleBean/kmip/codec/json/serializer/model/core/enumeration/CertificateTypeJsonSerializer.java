package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;

public class CertificateTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateType, String> {

    public CertificateTypeJsonSerializer() {
        super(CertificateType::getDescription);
    }
}