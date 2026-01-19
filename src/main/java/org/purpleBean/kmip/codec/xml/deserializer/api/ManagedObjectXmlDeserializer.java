package org.purpleBean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

import java.io.IOException;

public class ManagedObjectXmlDeserializer extends KmipDataTypeXmlDeserializer<ManagedObject> {

    @Override
    public ManagedObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        String ctxtObjectType = (String) ctxt.getAttribute("objectType");
        ObjectType.Value objectTypeValue;
        if (ctxtObjectType == null) {
            objectTypeValue = null;
        } else {
            objectTypeValue = ObjectType.fromName(ctxtObjectType);
        }
        return ManagedObject.getClassFromRegistry(encodingType, objectTypeValue);
    }
}
