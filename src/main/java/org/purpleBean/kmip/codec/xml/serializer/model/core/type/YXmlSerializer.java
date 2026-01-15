package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Y;

import java.math.BigInteger;

public class YXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Y, BigInteger> {

    public YXmlSerializer() {
        super(Y::getValue);
    }
}