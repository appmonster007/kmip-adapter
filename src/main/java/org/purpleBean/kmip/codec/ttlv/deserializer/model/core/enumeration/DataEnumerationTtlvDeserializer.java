package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

public class DataEnumerationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DataEnumeration, Integer> {

    public DataEnumerationTtlvDeserializer() {
        super(DataEnumeration.kmipTag, DataEnumeration.encodingType, Integer.class, value -> new DataEnumeration(DataEnumeration.fromValue(value)));
    }
}