package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UniqueBatchItemIDJsonSerializer extends KmipDataTypeJsonSerializer<UniqueBatchItemID> {

    @Override
    public void serialize(UniqueBatchItemID uniqueBatchItemID, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (uniqueBatchItemID == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!uniqueBatchItemID.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", uniqueBatchItemID.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(uniqueBatchItemID.getKmipTag());
        gen.writeStringField("type", uniqueBatchItemID.getEncodingType().getDescription());
        gen.writeObjectField("value", uniqueBatchItemID.getValue());
        gen.writeEndObject();
    }
}