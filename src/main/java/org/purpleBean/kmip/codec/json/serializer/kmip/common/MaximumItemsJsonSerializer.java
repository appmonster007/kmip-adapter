package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MaximumItems;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MaximumItemsJsonSerializer extends KmipDataTypeJsonSerializer<MaximumItems> {

    @Override
    public void serialize(MaximumItems maximumItems, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (maximumItems == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!maximumItems.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", maximumItems.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(maximumItems.getKmipTag());
        gen.writeStringField("type", maximumItems.getEncodingType().getDescription());
        gen.writeObjectField("value", maximumItems.getValue());
        gen.writeEndObject();
    }
}