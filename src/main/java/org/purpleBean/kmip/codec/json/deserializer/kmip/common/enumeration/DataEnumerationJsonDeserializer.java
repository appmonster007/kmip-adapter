package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DataEnumeration, String> {

    public DataEnumerationJsonDeserializer() {
        super(DataEnumeration.kmipTag, DataEnumeration.encodingType, String.class, value -> new DataEnumeration(DataEnumeration.fromName(value)));
    }
}