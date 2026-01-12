package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionTtlvSerializer extends AbstractKmipTtlvSerializer<QueryFunction, Integer> {

    public QueryFunctionTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}