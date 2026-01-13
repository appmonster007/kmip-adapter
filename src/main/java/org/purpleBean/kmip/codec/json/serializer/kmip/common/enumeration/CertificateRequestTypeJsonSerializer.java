package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

public class CertificateRequestTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateRequestType, String> {

    public CertificateRequestTypeJsonSerializer() {
        super(CertificateRequestType::getDescription);
    }
}