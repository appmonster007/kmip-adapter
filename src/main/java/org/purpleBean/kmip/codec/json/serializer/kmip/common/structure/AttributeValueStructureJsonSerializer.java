package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.AttributeValueStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueStructureJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValueStructure> {

    @Override
    public void serialize(AttributeValueStructure attributeValueStructure, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (attributeValueStructure == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueStructure.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValueStructure.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValueStructure.getKmipTag());
        gen.writeStringField("type", attributeValueStructure.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValueStructure.getValues());
        gen.writeEndObject();
    }
}
