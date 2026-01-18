package org.purpleBean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KeyMaterial;

import java.io.IOException;

public class KeyMaterialXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyMaterial> {

    @Override
    public KeyMaterial deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }
}