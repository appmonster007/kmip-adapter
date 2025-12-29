package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MaximumItems;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("MaximumItems TTLV Serialization Tests")
class MaximumItemsTtlvTest extends AbstractTtlvSerializationSuite<MaximumItems> {

    @Override
    protected Class<MaximumItems> type() {
        return MaximumItems.class;
    }

    @Override
    protected MaximumItems createDefault() {
        return MaximumItems.builder().value(100).build();
    }

    @Override
    protected MaximumItems createVariant() {
        return MaximumItems.builder().value(200).build();
    }
}