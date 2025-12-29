package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MACSignatureKeyInformationJsonSerializer extends KmipDataTypeJsonSerializer<MACSignatureKeyInformation> {

    @Override
    public void serialize(MACSignatureKeyInformation mACSignatureKeyInformation, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (mACSignatureKeyInformation == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!mACSignatureKeyInformation.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", mACSignatureKeyInformation.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(mACSignatureKeyInformation.getKmipTag());
        gen.writeStringField("type", mACSignatureKeyInformation.getEncodingType().getDescription());
        gen.writeObjectField("value", mACSignatureKeyInformation.getValue());
        gen.writeEndObject();
    }
}