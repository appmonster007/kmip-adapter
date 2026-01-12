package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateLength, Integer> {

    public CertificateLengthTtlvSerializer() {
        super(CertificateLength::getValue);
    }
}