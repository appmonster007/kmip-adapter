package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ImportOpResponsePayload;

import java.io.IOException;

public class ImportOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ImportOpResponsePayload, ImportOpResponsePayload.ImportOpResponsePayloadBuilder> {

    public ImportOpResponsePayloadXmlDeserializer() {
        super(ImportOpResponsePayload.kmipTag, ImportOpResponsePayload.encodingType);
    }

    @Override
    protected ImportOpResponsePayload.ImportOpResponsePayloadBuilder createBuilder() {
        return ImportOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ImportOpResponsePayload.ImportOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ImportOpResponsePayload build(ImportOpResponsePayload.ImportOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}