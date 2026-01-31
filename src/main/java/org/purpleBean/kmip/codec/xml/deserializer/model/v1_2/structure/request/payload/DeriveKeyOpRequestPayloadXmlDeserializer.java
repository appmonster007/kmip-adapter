package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DeriveKeyOpRequestPayload;

import java.io.IOException;

public class DeriveKeyOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DeriveKeyOpRequestPayload, DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder> {

    public DeriveKeyOpRequestPayloadXmlDeserializer() {
        super(DeriveKeyOpRequestPayload.kmipTag, DeriveKeyOpRequestPayload.encodingType);
    }

    @Override
    protected DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder createBuilder() {
        return DeriveKeyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.DERIVATION_METHOD ->
                    builder.derivationMethod(ctxt.readValue(p, DerivationMethod.class));
            case KmipTag.Standard.DERIVATION_PARAMETERS ->
                    builder.derivationParameters(ctxt.readValue(p, DerivationParameters.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeriveKeyOpRequestPayload build(DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}