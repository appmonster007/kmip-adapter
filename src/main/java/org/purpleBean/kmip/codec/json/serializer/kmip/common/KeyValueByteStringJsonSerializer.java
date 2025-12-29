package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class KeyValueByteStringJsonSerializer extends KmipDataTypeJsonSerializer<KeyValue.ByteString> {

    @Override
    public void serialize(KeyValue.ByteString keyValueByteString, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (keyValueByteString == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!keyValueByteString.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", keyValueByteString.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(keyValueByteString.getKmipTag());
        gen.writeStringField("type", keyValueByteString.getEncodingType().getDescription());
        gen.writeObjectField("value", keyValueByteString.getValue());
        gen.writeEndObject();
    }
}