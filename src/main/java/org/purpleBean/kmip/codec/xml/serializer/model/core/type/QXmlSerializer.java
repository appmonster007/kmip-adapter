package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Q;

import java.math.BigInteger;

public class QXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Q, BigInteger> {

    public QXmlSerializer() {
        super(Q::getValue);
    }
}