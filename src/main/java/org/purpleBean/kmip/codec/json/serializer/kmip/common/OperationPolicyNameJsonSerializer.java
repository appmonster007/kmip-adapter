package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.OperationPolicyName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.List;

public class OperationPolicyNameJsonSerializer extends KmipDataTypeJsonSerializer<OperationPolicyName> {

    @Override
    public void serialize(OperationPolicyName operationPolicyName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (operationPolicyName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!operationPolicyName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", operationPolicyName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(operationPolicyName.getKmipTag());
        gen.writeStringField("type", operationPolicyName.getEncodingType().getDescription());
        gen.writeObjectField("value", operationPolicyName.getValue());
        gen.writeEndObject();
    }
}
