package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateSplitKeyOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

import java.io.IOException;

public class CreateSplitKeyOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CreateSplitKeyOpRequestPayload, CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder> {

    public CreateSplitKeyOpRequestPayloadXmlDeserializer() {
        super(CreateSplitKeyOpRequestPayload.kmipTag, CreateSplitKeyOpRequestPayload.encodingType);
    }

    @Override
    protected CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder createBuilder() {
        return CreateSplitKeyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.SPLIT_KEY_PARTS -> builder.splitKeyParts(ctxt.readValue(p, SplitKeyParts.class));
            case KmipTag.Standard.SPLIT_KEY_THRESHOLD -> builder.splitKeyThreshold(ctxt.readValue(p, SplitKeyThreshold.class));
            case KmipTag.Standard.SPLIT_KEY_METHOD -> builder.splitKeyMethod(ctxt.readValue(p, SplitKeyMethod.class));
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateSplitKeyOpRequestPayload build(CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}