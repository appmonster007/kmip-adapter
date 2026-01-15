package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.X;

import java.math.BigInteger;

public class XXmlSerializer extends AbstractKmipDataTypeXmlSerializer<X, BigInteger> {

    public XXmlSerializer() {
        super(X::getValue);
    }
}