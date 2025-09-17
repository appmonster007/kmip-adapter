package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

import java.io.IOException;

public class KeyCompressionTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyCompressionType> {
    
    @Override
    public KeyCompressionType deserialize(JsonParser p, DeserializationContext ctxt) 
            throws IOException, JsonProcessingException {
        
        String value = p.getValueAsString();
        KmipSpec spec = KmipContext.getSpec();
        
        try {
            return new KeyCompressionType(KeyCompressionType.fromName(spec, value));
        } catch (Exception e) {
            throw new IOException(
                String.format("Failed to deserialize KeyCompressionType from value '%s' for KMIP spec %s", 
                    value, spec), e);
        }
    }
}
