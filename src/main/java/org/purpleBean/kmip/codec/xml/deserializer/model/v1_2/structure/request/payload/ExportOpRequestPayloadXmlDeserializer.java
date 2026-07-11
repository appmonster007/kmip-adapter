package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

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
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ExportOpRequestPayload;

import java.io.IOException;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class ExportOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ExportOpRequestPayload, ExportOpRequestPayload.ExportOpRequestPayloadBuilder> {

    public ExportOpRequestPayloadXmlDeserializer() {
        super(ExportOpRequestPayload.kmipTag, ExportOpRequestPayload.encodingType);
    }

    @Override
    protected ExportOpRequestPayload.ExportOpRequestPayloadBuilder createBuilder() {
        return ExportOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ExportOpRequestPayload.ExportOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION -> builder.keyWrappingSpecification(ctxt.readValue(p, KeyWrappingSpecification.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ExportOpRequestPayload build(ExportOpRequestPayload.ExportOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}