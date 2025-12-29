package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DeviceIdentifierJsonSerializer extends KmipDataTypeJsonSerializer<DeviceIdentifier> {

    @Override
    public void serialize(DeviceIdentifier deviceIdentifier, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (deviceIdentifier == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!deviceIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", deviceIdentifier.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(deviceIdentifier.getKmipTag());
        gen.writeStringField("type", deviceIdentifier.getEncodingType().getDescription());
        gen.writeObjectField("value", deviceIdentifier.getValue());
        gen.writeEndObject();
    }
}