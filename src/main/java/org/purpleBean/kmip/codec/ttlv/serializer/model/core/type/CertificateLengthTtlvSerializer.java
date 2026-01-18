package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CertificateLength;

public class CertificateLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateLength, Integer> {

    public CertificateLengthTtlvSerializer() {
        super(CertificateLength::getValue);
    }
}