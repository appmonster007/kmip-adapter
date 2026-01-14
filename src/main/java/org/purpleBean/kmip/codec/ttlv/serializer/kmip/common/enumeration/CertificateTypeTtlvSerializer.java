package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CertificateType;

public class CertificateTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateType, Integer> {

    public CertificateTypeTtlvSerializer() {
        super(CertificateType::getValue);
    }
}