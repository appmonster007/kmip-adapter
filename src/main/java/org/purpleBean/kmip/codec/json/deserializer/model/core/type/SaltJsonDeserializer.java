package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Salt;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SaltJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Salt, Salt.SaltBuilder> {

    public SaltJsonDeserializer() {
        super(Salt.kmipTag, Salt.encodingType);
    }

    @Override
    protected Salt.SaltBuilder createBuilder() {
        return Salt.builder();
    }

    @Override
    protected void setValue(Salt.SaltBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected Salt build(Salt.SaltBuilder builder) {
        return builder.build();
    }
}
