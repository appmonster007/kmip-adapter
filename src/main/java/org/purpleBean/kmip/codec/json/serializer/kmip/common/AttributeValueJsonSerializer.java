package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for AttributeValue.
 */
public class AttributeValueJsonSerializer extends KmipDataTypeJsonSerializer<Attribute.AttributeValue> {

    @Override
    public void serialize(Attribute.AttributeValue attributeValue, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (attributeValue == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValue.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();

        gen.writeStringField("tag", attributeValue.getKmipTag().getDescription());

        // Use ObjectMapper from the provider to serialize attributeValue.getValue() into a tree
        ObjectMapper mapper = (ObjectMapper) gen.getCodec();
        JsonNode inner = mapper.valueToTree(attributeValue.getValue());

        // Extract "type" field if present, otherwise fallback to full serialization
        JsonNode extractedType = inner.has("type") ? inner.get("type") : inner;

        gen.writeFieldName("type");
        mapper.writeTree(gen, extractedType);

        // Extract "value" field if present, otherwise fallback to full serialization
        JsonNode extractedValue = inner.has("value") ? inner.get("value") : inner;

        gen.writeFieldName("value");
        mapper.writeTree(gen, extractedValue);

        gen.writeEndObject();
    }
}
