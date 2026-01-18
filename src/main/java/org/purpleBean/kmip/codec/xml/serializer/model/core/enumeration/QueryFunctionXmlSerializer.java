package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;

public class QueryFunctionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<QueryFunction, String> {

    public QueryFunctionXmlSerializer() {
        super(QueryFunction::getDescription);
    }
}