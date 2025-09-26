package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for CustomAttribute.
 */
public class CustomAttributeJsonSerializer extends KmipDataTypeJsonSerializer<Attribute.CustomAttribute> {

    @Override
    public void serialize(Attribute.CustomAttribute value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", value.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeObjectField("value", value.getValue());
        gen.writeEndObject();
    }
}
