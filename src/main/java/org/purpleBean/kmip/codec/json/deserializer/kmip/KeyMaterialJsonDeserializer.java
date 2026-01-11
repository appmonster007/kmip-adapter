package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KeyMaterial;

import java.io.IOException;

public class KeyMaterialJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyMaterial> {

    @Override
    public KeyMaterial deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return (KeyMaterial) super.deserialize(p, ctxt);
    }
}