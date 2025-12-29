package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ResultMessage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ResultMessageJsonSerializer extends KmipDataTypeJsonSerializer<ResultMessage> {

    @Override
    public void serialize(ResultMessage resultMessage, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (resultMessage == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!resultMessage.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", resultMessage.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(resultMessage.getKmipTag());
        gen.writeStringField("type", resultMessage.getEncodingType().getDescription());
        gen.writeObjectField("value", resultMessage.getValue());
        gen.writeEndObject();
    }
}