package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

public class CertificateRequestTypeTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateRequestType, Integer> {

    public CertificateRequestTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}