package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DerivationData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DerivationDataJsonSerializer extends KmipDataTypeJsonSerializer<DerivationData> {

    @Override
    public void serialize(DerivationData derivationData, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (derivationData == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!derivationData.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", derivationData.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(derivationData.getKmipTag());
        gen.writeStringField("type", derivationData.getEncodingType().getDescription());
        gen.writeObjectField("value", derivationData.getValue());
        gen.writeEndObject();
    }
}