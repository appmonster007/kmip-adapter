package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.X;

import java.math.BigInteger;

public class XXmlSerializer extends AbstractKmipDataTypeXmlSerializer<X, BigInteger> {

    public XXmlSerializer() {
        super(X::getValue);
    }
}