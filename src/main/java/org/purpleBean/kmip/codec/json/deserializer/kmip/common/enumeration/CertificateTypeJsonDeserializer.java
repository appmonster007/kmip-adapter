package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CertificateType;

public class CertificateTypeJsonDeserializer extends AbstractKmipJsonDeserializer<CertificateType, String> {

    public CertificateTypeJsonDeserializer() {
        super(CertificateType.kmipTag, CertificateType.encodingType, String.class, value -> new CertificateType(CertificateType.fromName(value)));
    }
}