package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationTtlvSerializer extends AbstractKmipTtlvSerializer<DataEnumeration, Integer> {

    public DataEnumerationTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}