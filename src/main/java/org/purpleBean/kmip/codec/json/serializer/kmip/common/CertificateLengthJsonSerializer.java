package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthJsonSerializer extends AbstractKmipJsonSerializer<CertificateLength, Integer> {

    public CertificateLengthJsonSerializer() {
        super(CertificateLength::getValue);
    }
}