package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ContactInformation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for ContactInformation.
 */
public class ContactInformationJsonSerializer extends KmipDataTypeJsonSerializer<ContactInformation> {

    @Override
    public void serialize(ContactInformation contactInformation, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (contactInformation == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!contactInformation.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", contactInformation.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(contactInformation.getKmipTag());
        gen.writeStringField("type", contactInformation.getEncodingType().getDescription());
        gen.writeObjectField("value", contactInformation.getValue());
        gen.writeEndObject();
    }
}