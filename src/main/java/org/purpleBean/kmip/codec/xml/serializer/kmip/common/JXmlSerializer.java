package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JXmlSerializer extends AbstractKmipDataTypeXmlSerializer<J, BigInteger> {

    public JXmlSerializer() {
        super(J::getValue);
    }
}