package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;

public class QueryFunctionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<QueryFunction, Integer> {

    public QueryFunctionTtlvDeserializer() {
        super(QueryFunction.kmipTag, QueryFunction.encodingType, Integer.class, value -> new QueryFunction(QueryFunction.fromValue(value)));
    }
}