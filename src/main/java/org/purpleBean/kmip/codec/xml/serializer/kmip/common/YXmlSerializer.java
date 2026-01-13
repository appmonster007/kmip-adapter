package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.Y;

import java.math.BigInteger;

public class YXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Y, BigInteger> {

    public YXmlSerializer() {
        super(Y::getValue);
    }
}