package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;

public class QueryFunctionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<QueryFunction, String> {

    public QueryFunctionJsonSerializer() {
        super(QueryFunction::getDescription);
    }
}