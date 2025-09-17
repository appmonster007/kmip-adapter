package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

import java.io.IOException;

public class CredentialTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<CredentialType> {
    
    @Override
    public CredentialType deserialize(JsonParser p, DeserializationContext ctxt) 
            throws IOException, JsonProcessingException {
        
        JsonNode node = p.getCodec().readTree(p);
        String value = node.get("value").asText();
        KmipSpec spec = KmipContext.getSpec();
        
        try {
            return new CredentialType(CredentialType.fromName(spec, value));
        } catch (Exception e) {
            throw new IOException(
                String.format("Failed to deserialize CredentialType from value '%s' for KMIP spec %s", 
                    value, spec), e);
        }
    }
}
