package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateLength, Integer> {

    public CertificateLengthTtlvSerializer() {
        super(CertificateLength::getValue);
    }
}