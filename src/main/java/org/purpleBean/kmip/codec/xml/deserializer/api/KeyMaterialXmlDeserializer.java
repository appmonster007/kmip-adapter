package org.purpleBean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KeyMaterial;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

import java.io.IOException;

/**
 * XML deserializer for {@link KeyMaterial} objects.
 * <p>
 * This class extends {@link KmipDataTypeXmlDeserializer} to handle the specific logic required
 * for deserializing KMIP Key Material from XML. It uses the {@code keyFormatType} attribute
 * from the deserialization context to determine the concrete class to instantiate.
 */
public class KeyMaterialXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyMaterial> {

    @Override
    public KeyMaterial deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        String ctxtKeyFormatType = (String) ctxt.getAttribute("keyFormatType");
        KeyFormatType.Value keyFormatTypeValue;
        if (ctxtKeyFormatType == null) {
            keyFormatTypeValue = null;
        } else {
            keyFormatTypeValue = KeyFormatType.fromName(ctxtKeyFormatType);
        }
        return KeyMaterial.getClassFromRegistry(encodingType, keyFormatTypeValue);
    }
}
