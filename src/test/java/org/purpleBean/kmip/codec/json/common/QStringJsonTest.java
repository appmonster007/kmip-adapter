package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("QString JSON Serialization Tests")
class QStringJsonTest extends AbstractJsonSerializationSuite<QString> {

    @Override
    protected Class<QString> type() {
        return QString.class;
    }

    @Override
    protected QString createDefault() {
        return QString.of("test-qstring".getBytes());
    }

    @Override
    protected QString createVariant() {
        return QString.of("another-qstring".getBytes());
    }
}