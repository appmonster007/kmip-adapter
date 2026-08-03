package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.v2_1.structure.CommonAttributes;
import org.purpleBean.kmip.model.v2_1.structure.PrivateKeyAttributes;
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.ReKeyKeyPairOpRequestPayload;
import org.purpleBean.kmip.model.v3_0.type.PrivateKeyUniqueIdentifier;

import java.io.IOException;

public class ReKeyKeyPairOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ReKeyKeyPairOpRequestPayload, ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder> {

    public ReKeyKeyPairOpRequestPayloadXmlDeserializer() {
        super(ReKeyKeyPairOpRequestPayload.kmipTag, ReKeyKeyPairOpRequestPayload.encodingType);
    }

    @Override
    protected ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder createBuilder() {
        return ReKeyKeyPairOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
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
            case KmipTag.Standard.COMMON_ATTRIBUTES ->
                    builder.commonAttributes(ctxt.readValue(p, CommonAttributes.class));
            case KmipTag.Standard.PRIVATE_KEY_ATTRIBUTES ->
                    builder.privateKeyAttributes(ctxt.readValue(p, PrivateKeyAttributes.class));
            case KmipTag.Standard.PUBLIC_KEY_ATTRIBUTES ->
                    builder.publicKeyAttributes(ctxt.readValue(p, PublicKeyAttributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ReKeyKeyPairOpRequestPayload build(ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
