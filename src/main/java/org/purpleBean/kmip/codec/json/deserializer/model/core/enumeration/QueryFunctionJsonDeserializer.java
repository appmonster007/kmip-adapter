package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;

public class QueryFunctionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<QueryFunction, String> {

    public QueryFunctionJsonDeserializer() {
        super(QueryFunction.kmipTag, QueryFunction.encodingType, String.class, value -> new QueryFunction(QueryFunction.fromName(value)));
    }
}