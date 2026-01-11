package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.AttributeValue;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;

import java.io.IOException;

public class AttributeValueJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValue> {

    @Override
    public AttributeValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }
}