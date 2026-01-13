package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<QueryFunction, String> {

    public QueryFunctionXmlSerializer() {
        super(QueryFunction::getDescription);
    }
}