package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CertificateLength;

public class CertificateLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateLength, Integer> {

    public CertificateLengthJsonSerializer() {
        super(CertificateLength::getValue);
    }
}