package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationJsonDeserializer extends AbstractKmipJsonDeserializer<DataEnumeration, String> {

    public DataEnumerationJsonDeserializer() {
        super(DataEnumeration.kmipTag, DataEnumeration.encodingType, String.class, value -> new DataEnumeration(DataEnumeration.fromName(value)));
    }
}