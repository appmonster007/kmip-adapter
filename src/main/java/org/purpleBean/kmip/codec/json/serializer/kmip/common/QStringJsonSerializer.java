package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringJsonSerializer extends AbstractKmipJsonSerializer<QString, ByteBuffer> {

    public QStringJsonSerializer() {
        super(QString::getValue);
    }
}