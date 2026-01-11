package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringXmlDeserializer extends AbstractKmipXmlDeserializer<QString, ByteBuffer> {

    public QStringXmlDeserializer() {
        super(QString.kmipTag, QString.encodingType, ByteBuffer.class, value -> QString.builder().value(value).build());
    }
}