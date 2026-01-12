package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JXmlSerializer extends AbstractKmipXmlSerializer<J, BigInteger> {

    public JXmlSerializer() {
        super(J::getValue);
    }
}