package org.purpleBean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.AttributeValue;

import java.io.IOException;

/**
 * XML deserializer for {@link AttributeValue} objects.
 * <p>
 * This class extends {@link KmipDataTypeXmlDeserializer} to handle the deserialization
 * of KMIP Attribute Values from XML. It relies on the base class logic to identify
 * the correct concrete type based on the tag and encoding type.
 */
public class AttributeValueXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue> {

    @Override
    public AttributeValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }
}
