package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateRequestType, Integer> {

    public CertificateRequestTypeTtlvSerializer() {
        super(CertificateRequestType::getValue);
    }
}