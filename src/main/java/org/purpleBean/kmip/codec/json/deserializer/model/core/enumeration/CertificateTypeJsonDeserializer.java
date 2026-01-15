package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;

public class CertificateTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateType, String> {

    public CertificateTypeJsonDeserializer() {
        super(CertificateType.kmipTag, CertificateType.encodingType, String.class, value -> new CertificateType(CertificateType.fromName(value)));
    }
}