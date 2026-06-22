package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.CertificateLink;

import java.io.IOException;

public class CertificateLinkJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateLink, CertificateLink.CertificateLinkBuilder> {

    public CertificateLinkJsonDeserializer() {
        super(CertificateLink.kmipTag, CertificateLink.encodingType);
    }

    @Override
    protected CertificateLink.CertificateLinkBuilder createBuilder() {
        return CertificateLink.builder();
    }

    @Override
    protected void setValue(CertificateLink.CertificateLinkBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertificateLink build(CertificateLink.CertificateLinkBuilder builder) {
        return builder.build();
    }
}
