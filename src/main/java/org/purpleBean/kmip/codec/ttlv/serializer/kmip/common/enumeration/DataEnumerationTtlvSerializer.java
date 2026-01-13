package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DataEnumeration, Integer> {

    public DataEnumerationTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}