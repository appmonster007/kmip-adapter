package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationTtlvDeserializer extends AbstractKmipTtlvDeserializer<DataEnumeration, Integer> {

    public DataEnumerationTtlvDeserializer() {
        super(DataEnumeration.kmipTag, DataEnumeration.encodingType, Integer.class, value -> new DataEnumeration(DataEnumeration.fromValue(value)));
    }
}