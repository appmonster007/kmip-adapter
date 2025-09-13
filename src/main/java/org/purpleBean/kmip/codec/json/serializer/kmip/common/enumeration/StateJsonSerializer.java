package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.State;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class StateJsonSerializer extends KmipDataTypeJsonSerializer<State> {

    @Override
    public void serialize(State state, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (state == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!state.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("State '%s' is not supported for KMIP spec %s",
                            state.getDescription(), spec)
            );
        }

        if (state.getDescription() == null || state.getDescription().trim().isEmpty()) {
            throw new IllegalStateException("State must have a valid description");
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(state.getKmipTag());
        jsonGenerator.writeStringField("type", state.getEncodingType().getDescription());
        jsonGenerator.writeStringField("value", state.getDescription());
        jsonGenerator.writeEndObject();
    }
}
