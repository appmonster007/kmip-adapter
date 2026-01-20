package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ReKeyKeyPairOpRequestPayload;

import java.io.IOException;

public class ReKeyKeyPairOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<ReKeyKeyPairOpRequestPayload, ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder> {

    public ReKeyKeyPairOpRequestPayloadJsonDeserializer() {
        super(ReKeyKeyPairOpRequestPayload.kmipTag, ReKeyKeyPairOpRequestPayload.encodingType);
    }

    @Override
    protected ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder createBuilder() {
        return ReKeyKeyPairOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER ->
                    builder.privateKeyUniqueIdentifier(ctxt.readValue(p, PrivateKeyUniqueIdentifier.class));
            case KmipTag.Standard.OFFSET -> builder.offset(ctxt.readValue(p, Offset.class));
            case KmipTag.Standard.COMMON_TEMPLATE_ATTRIBUTE ->
                    builder.commonTemplateAttribute(ctxt.readValue(p, CommonTemplateAttribute.class));
            case KmipTag.Standard.PRIVATE_KEY_TEMPLATE_ATTRIBUTE ->
                    builder.privateKeyTemplateAttribute(ctxt.readValue(p, PrivateKeyTemplateAttribute.class));
            case KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE ->
                    builder.publicKeyTemplateAttribute(ctxt.readValue(p, PublicKeyTemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ReKeyKeyPairOpRequestPayload build(ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}