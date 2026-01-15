package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

public class DataEnumerationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DataEnumeration, Integer> {

    public DataEnumerationTtlvSerializer() {
        super(DataEnumeration::getValue);
    }
}