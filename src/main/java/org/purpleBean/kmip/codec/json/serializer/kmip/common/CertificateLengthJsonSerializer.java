package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateLength, Integer> {

    public CertificateLengthJsonSerializer() {
        super(CertificateLength::getValue);
    }
}