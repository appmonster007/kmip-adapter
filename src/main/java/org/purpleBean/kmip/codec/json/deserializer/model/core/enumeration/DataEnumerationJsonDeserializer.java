package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

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
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

import java.io.IOException;

public class DataEnumerationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DataEnumeration, DataEnumeration.DataEnumerationBuilder> {

    public DataEnumerationJsonDeserializer() {
        super(DataEnumeration.kmipTag, DataEnumeration.encodingType);
    }

    @Override
    protected DataEnumeration.DataEnumerationBuilder createBuilder() {
        return DataEnumeration.builder();
    }

    @Override
    protected void setValue(DataEnumeration.DataEnumerationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(DataEnumeration.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected DataEnumeration build(DataEnumeration.DataEnumerationBuilder builder) {
        return builder.build();
    }
}