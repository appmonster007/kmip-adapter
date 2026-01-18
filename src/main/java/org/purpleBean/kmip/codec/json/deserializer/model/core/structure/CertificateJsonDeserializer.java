package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateValue;

import java.io.IOException;

public class CertificateJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Certificate, Certificate.CertificateBuilder> {

    public CertificateJsonDeserializer() {
        super(Certificate.kmipTag, Certificate.encodingType);
    }

    @Override
    protected Certificate.CertificateBuilder createBuilder() {
        return Certificate.builder();
    }

    @Override
    protected void setValue(Certificate.CertificateBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_TYPE -> builder.certificateType(ctxt.readValue(p, CertificateType.class));
            case KmipTag.Standard.CERTIFICATE_VALUE ->
                    builder.certificateValue(ctxt.readValue(p, CertificateValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Certificate build(Certificate.CertificateBuilder builder) {
        return builder.build();
    }
}