package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CertifyOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertifyOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CertifyOpRequestPayload, CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder> {

    public CertifyOpRequestPayloadTtlvDeserializer() {
        super(CertifyOpRequestPayload.kmipTag);
    }

    @Override
    protected CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder createBuilder() {
        return CertifyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CERTIFICATE_REQUEST_TYPE ->
                    builder.certificateRequestType(mapper.readValue(p, CertificateRequestType.class));
            case KmipTag.Standard.CERTIFICATE_REQUEST ->
                    builder.certificateRequest(mapper.readValue(p, CertificateRequest.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertifyOpRequestPayload build(CertifyOpRequestPayload.CertifyOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CertifyOpRequestPayload.encodingType;
    }
}