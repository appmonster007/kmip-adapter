package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

public class DataEnumerationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DataEnumeration, String> {

    public DataEnumerationJsonDeserializer() {
        super(DataEnumeration.kmipTag, DataEnumeration.encodingType, String.class, value -> new DataEnumeration(DataEnumeration.fromName(value)));
    }
}