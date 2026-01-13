package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<QueryFunction, Integer> {

    public QueryFunctionTtlvDeserializer() {
        super(QueryFunction.kmipTag, QueryFunction.encodingType, Integer.class, value -> new QueryFunction(QueryFunction.fromValue(value)));
    }
}