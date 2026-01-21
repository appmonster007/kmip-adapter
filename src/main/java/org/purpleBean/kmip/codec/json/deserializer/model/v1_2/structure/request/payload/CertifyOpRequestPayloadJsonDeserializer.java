package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CertifyOpRequestPayload;

import java.io.IOException;

public class CertifyOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CertifyOpRequestPayload, CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder> {

    public CertifyOpRequestPayloadJsonDeserializer() {
        super(CertifyOpRequestPayload.kmipTag, CertifyOpRequestPayload.encodingType);
    }

    @Override
    protected CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder createBuilder() {
        return CertifyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CERTIFICATE_REQUEST_TYPE ->
                    builder.certificateRequestType(ctxt.readValue(p, CertificateRequestType.class));
            case KmipTag.Standard.CERTIFICATE_REQUEST ->
                    builder.certificateRequest(ctxt.readValue(p, CertificateRequest.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertifyOpRequestPayload build(CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}