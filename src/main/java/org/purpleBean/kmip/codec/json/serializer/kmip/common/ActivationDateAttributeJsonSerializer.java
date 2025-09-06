package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ActivationDateAttributeJsonSerializer extends KmipDataTypeJsonSerializer<ActivationDateAttribute> {

    @Override
    public void serialize(ActivationDateAttribute activationDateAttribute, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (activationDateAttribute == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!activationDateAttribute.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", activationDateAttribute.getKmipTag().getDescription(), spec)
            );
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(activationDateAttribute.getKmipTag());
        jsonGenerator.writeStringField("type", activationDateAttribute.getEncodingType().getDescription());
        jsonGenerator.writeStringField("value", activationDateAttribute.getDateTime().toString());
        jsonGenerator.writeEndObject();
    }
}
