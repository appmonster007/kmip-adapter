package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.PutOpRequestPayload;

import java.io.IOException;

public class PutOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<PutOpRequestPayload, PutOpRequestPayload.PutOpRequestPayloadBuilder> {

    public PutOpRequestPayloadXmlDeserializer() {
        super(PutOpRequestPayload.kmipTag);
    }

    @Override
    protected PutOpRequestPayload.PutOpRequestPayloadBuilder createBuilder() {
        return PutOpRequestPayload.builder();
    }

    @Override
    protected void setValue(PutOpRequestPayload.PutOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.PUT_FUNCTION -> builder.putFunction(ctxt.readValue(p, PutFunction.class));
            case KmipTag.Standard.REPLACED_UNIQUE_IDENTIFIER ->
                    builder.replacedUniqueIdentifier(ctxt.readValue(p, ReplacedUniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> {
                if (ManagedObject.isManagedObject(nodeTag)) {
                    builder.object(ctxt.readValue(p, ManagedObject.class));
                } else {
                    throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
                }
            }
        }
    }

    @Override
    protected PutOpRequestPayload build(PutOpRequestPayload.PutOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
