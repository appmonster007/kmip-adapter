package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DataByteString;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DataByteStringJsonSerializer extends KmipDataTypeJsonSerializer<DataByteString> {

    @Override
    public void serialize(DataByteString dataByteString, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (dataByteString == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!dataByteString.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", dataByteString.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(dataByteString.getKmipTag());
        gen.writeStringField("type", dataByteString.getEncodingType().getDescription());
        gen.writeObjectField("value", dataByteString.getValue());
        gen.writeEndObject();
    }
}
