package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

public class CertificateRequestTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateRequestType, Integer> {

    public CertificateRequestTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}