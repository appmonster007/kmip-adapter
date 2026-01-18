package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateIssuerAlternativeName, String> {

    public CertificateIssuerAlternativeNameJsonSerializer() {
        super(CertificateIssuerAlternativeName::getValue);
    }
}