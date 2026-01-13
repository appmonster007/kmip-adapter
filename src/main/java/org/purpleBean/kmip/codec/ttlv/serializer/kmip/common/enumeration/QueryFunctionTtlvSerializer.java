package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<QueryFunction, Integer> {

    public QueryFunctionTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}