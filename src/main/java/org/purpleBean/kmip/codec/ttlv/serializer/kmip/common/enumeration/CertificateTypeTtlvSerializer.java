package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CertificateType;

public class CertificateTypeTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateType, Integer> {

    public CertificateTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}