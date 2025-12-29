package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SerialNumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SerialNumberJsonSerializer extends KmipDataTypeJsonSerializer<SerialNumber> {

    @Override
    public void serialize(SerialNumber serialNumber, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (serialNumber == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!serialNumber.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", serialNumber.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(serialNumber.getKmipTag());
        gen.writeStringField("type", serialNumber.getEncodingType().getDescription());
        gen.writeObjectField("value", serialNumber.getValue());
        gen.writeEndObject();
    }
}