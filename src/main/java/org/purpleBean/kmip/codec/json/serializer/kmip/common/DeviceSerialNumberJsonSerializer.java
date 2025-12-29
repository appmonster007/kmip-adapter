package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DeviceSerialNumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DeviceSerialNumberJsonSerializer extends KmipDataTypeJsonSerializer<DeviceSerialNumber> {

    @Override
    public void serialize(DeviceSerialNumber deviceSerialNumber, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (deviceSerialNumber == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!deviceSerialNumber.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", deviceSerialNumber.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(deviceSerialNumber.getKmipTag());
        gen.writeStringField("type", deviceSerialNumber.getEncodingType().getDescription());
        gen.writeObjectField("value", deviceSerialNumber.getValue());
        gen.writeEndObject();
    }
}