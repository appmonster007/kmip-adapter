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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ReProvisionOpRequestPayload;

import java.io.IOException;

public class ReProvisionOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ReProvisionOpRequestPayload, ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder> {

    public ReProvisionOpRequestPayloadXmlDeserializer() {
        super(ReProvisionOpRequestPayload.kmipTag, ReProvisionOpRequestPayload.encodingType);
    }

    @Override
    protected ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder createBuilder() {
        return ReProvisionOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        // TODO: Implement setting values on the builder based on the tag
        // KmipTag.Value nodeTag = KmipTag.fromName(tag);
        // switch (nodeTag) {
        //     case KmipTag.Standard.FIELD_1 -> builder.field1(ctxt.readValue(p, Field1.class));
        //     case KmipTag.Standard.FIELD_2 -> builder.field2(ctxt.readValue(p, Field2.class));
        //     default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        // }
    }

    @Override
    protected ReProvisionOpRequestPayload build(ReProvisionOpRequestPayload.ReProvisionOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}